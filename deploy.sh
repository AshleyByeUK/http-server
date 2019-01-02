#!/usr/bin/env bash

ssh-keygen -b 2048 -t rsa -N '' -f /home/travis/.ssh/deploy_id_rsa

export TF_IN_AUTOMATION=true
cd infrastructure
bin/terraform init -input=false -get-plugins=false
bin/terraform plan -input=false -out=tfplan
bin/terraform apply -input=false -auto-approve "tfplan"
SERVER_IP_ADDRESS=$(eval "bin/terraform output http-server_public_dns")

echo ""
echo "Wait for server to come up..."
sleep 1m
echo "Try to setup server..."

scp -i /home/travis/.ssh/deploy_id_rsa -oStrictHostKeyChecking=no setup.sh ubuntu@${SERVER_IP_ADDRESS}:~/setup.sh
ssh -i /home/travis/.ssh/deploy_id_rsa -oStrictHostKeyChecking=no ubuntu@${SERVER_IP_ADDRESS} << EOF
chmod +x setup.sh
./setup.sh
rm setup.sh
exit
EOF

echo ""
echo "Deployed http-server to: $SERVER_IP_ADDRESS"
echo ""
cd ..
