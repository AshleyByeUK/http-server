#!/usr/bin/env bash

chmod +x gradlew
./gradlew check
./gradlew test
./gradlew jar
java -jar build/http-tcp.jar &
cd acceptance-tests
bundle install
bundle exec spinach
cd ..
