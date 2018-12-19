provider "aws" {
  version = "~> 1.52"
  region  = "eu-west-2"
}

resource "aws_instance" "http-server" {
  ami                         = "ami-06328f1e652dc7605"                   # Ubuntu 18.04 in eu-west-2
  instance_type               = "t2.micro"
  associate_public_ip_address = true
  key_name                    = "${aws_key_pair.public-ssh-key.key_name}"

  security_groups = [
    "${aws_security_group.allow_ssh.name}",
    "${aws_security_group.allow_http_server.name}",
    "${aws_security_group.allow_all_outbound.name}",
  ]
}

resource "aws_key_pair" "public-ssh-key" {
  key_name   = "public-ssh-key"
  public_key = "${file("/home/travis/.ssh/deploy_id_rsa.pub")}"
}

resource "aws_security_group" "allow_ssh" {
  name = "allow_ssh"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_security_group" "allow_http_server" {
  name = "allow_http_server"

  ingress {
    from_port   = 5000
    to_port     = 5000
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_security_group" "allow_all_outbound" {
  name = "allow_all_outbound"

  egress {
    from_port   = 0
    protocol    = "-1"
    to_port     = 0
    cidr_blocks = ["0.0.0.0/0"]
  }
}

output "http-server_public_dns" {
  value = "${aws_instance.http-server.public_dns}"
}
