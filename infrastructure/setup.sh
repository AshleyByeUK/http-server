#!/usr/bin/env bash

sudo apt-get update && sudo apt-get upgrade -y

# Install OpenJDK because version 11 from APT is actually version 10!
wget https://download.java.net/java/GA/jdk11/13/GPL/openjdk-11.0.1_linux-x64_bin.tar.gz -O /tmp/openjdk-11.0.1_linux-x64_bin.tar.gz
sudo mkdir /usr/lib/jvm && sudo tar xfvz /tmp/openjdk-11.0.1_linux-x64_bin.tar.gz --directory /usr/lib/jvm
rm -f /tmp/openjdk-11.0.1_linux-x64_bin.tar.gz
sudo sh -c 'for bin in /usr/lib/jvm/jdk-11.0.1/bin/*; do update-alternatives --install /usr/bin/$(basename $bin) $(basename $bin) $bin 100; done'
sudo sh -c 'for bin in /usr/lib/jvm/jdk-11.0.1/bin/*; do update-alternatives --set $(basename $bin) $bin; done'

git clone https://github.com/AshleyByeUK/http-server
cd http-server
./gradlew jar
mv build/http-server.jar ../http-server.jar
cd ..
rm -rf http-server

cat << EOF >> http-server.sh
#!/usr/bin/env bash

/usr/bin/java -jar /home/ubuntu/http-server.jar
EOF

chmod +x http-server.sh

cat << EOF >> http-server.service
[Unit]
Description=HTTP Server
[Service]
ExecStart=/home/ubuntu/http-server.sh
Type=simple
User=ubuntu
[Install]
WantedBy=multi-user.target
EOF

sudo mv http-server.service /etc/systemd/system
sudo systemctl daemon-reload
sudo systemctl start http-server.service
