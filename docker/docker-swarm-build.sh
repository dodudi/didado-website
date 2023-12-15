#!/bin/sh

docker stack rm lostark-dev

docker image rm $(docker image ls)

cd ..
./gradlew clean :lostark-armory:build :lostark-auction:build :lostark-character:build :lostark-content:build

docker image build --tag lostark-armory-image ./lostark-armory
docker image build --tag lostark-auction-image ./lostark-auction
docker image build --tag lostark-character-image ./lostark-character
docker image build --tag lostark-content-image ./lostark-content

cd docker
docker network rm lostark-dev-net
docker network create --driver=overlay lostark-dev-net
docker stack deploy -c docker-compose.yml -c docker-compose-dev.yml lostark-dev

