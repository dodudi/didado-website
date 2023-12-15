#!/bin/sh
docker image build --tag didadu/lostark-armory-image:v1.0.0 ./lostark-armory
docker image build --tag didadu/lostark-auction-image:v1.0.0 ./lostark-auction
docker image build --tag didadu/lostark-character-image:v1.0.0 ./lostark-character
docker image build --tag didadu/lostark-content-image:v1.0.0 ./lostark-content

docker push didadu/lostark-armory-image:v1.0.0
docker push didadu/lostark-auction-image:v1.0.0
docker push didadu/lostark-character-image:v1.0.0
docker push didadu/lostark-content-image:v1.0.0