#!/bin/sh
cd ..
./gradlew clean build
cp build/libs/website-0.0.1-SNAPSHOT.jar docker/app.jar

docker container rm -f didado-website-container
docker image rm -f didado-website

docker image build --tag didado-website docker/.
docker container run -d -p 8081:8081 --network didado-net --name didado-website-container didado-website
