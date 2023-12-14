#!/bin/sh

docker-compose -f docker-compose.yml -f docker-compose-dev.yml -p lostark-dev down
docker container rm $(docker container ls)
docker image rm $(docker image ls)
docker network rm lostark-dev-net

cd ..
./gradlew clean :lostark-armory:build :lostark-auction:build :lostark-character:build :lostark-content:build

docker image build --tag lostark-armory-image ./lostark-armory
docker image build --tag lostark-auction-image ./lostark-auction
docker image build --tag lostark-character-image ./lostark-character
docker image build --tag lostark-content-image ./lostark-content

cd docker
docker network create lostark-dev-net
docker-compose -f docker-compose.yml -f docker-compose-dev.yml -p lostark-dev up -d