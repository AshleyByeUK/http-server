#!/usr/bin/env bash

choco install openjdk --version 11.0
export JAVA_HOME="C:\Program Files\OpenJDK\jdk-11\bin"
gem install bundle

chmod +x gradlew
./gradlew check
./gradlew test
./gradlew jar
java -jar build/http-tcp.jar &
cd acceptance-tests
bundle install
bundle exec spinach
cd ..
