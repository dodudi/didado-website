#!/bin/sh

#도커 스웜 으로 변경
docker swarm init

#도커 스웜 스택 초기화
docker stack rm lostark-dev
#모든 이미지 삭제
docker image rm $(docker image ls)

#서브 모듈 모두 빌드
cd ..
./gradlew clean :lostark-armory:build :lostark-auction:build :lostark-character:build :lostark-content:build

#도커 컴포즈 실행에 필요한 이미지 빌드
docker image build --tag lostark-armory-image ./lostark-armory
docker image build --tag lostark-auction-image ./lostark-auction
docker image build --tag lostark-character-image ./lostark-character
docker image build --tag lostark-content-image ./lostark-content

#도커 스웜에 필요한 네트워크 생성, 도커 스웜으로 스택 실행
cd docker
docker network rm lostark-dev-net
docker network create --driver=overlay lostark-dev-net
docker stack deploy -c docker-compose.yml -c docker-compose-dev.yml lostark-dev

#도커 스웜 종료
docker swarm leave --force
