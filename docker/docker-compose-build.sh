#!/bin/sh

#프로젝트 빌드
cd ..
./gradlew clean build
cp build/libs/website-0.0.1-SNAPSHOT.jar docker/app.jar

#도커 이미지, 컨테이너 초기화
cd docker/
docker-compose -f docker-compose.yml -f docker-compose-dev.yml -p didado-website-dev down
docker-compose -f docker-compose.yml -f docker-compose-prod.yml -p didado-website-prod down
docker container rm $(docker container ls)
docker image rm didado-website

#도커 이미지 생성, 컴포즈 실행
docker image build --tag didado-website .

docker-compose -f docker-compose.yml -f docker-compose-dev.yml -p didado-website-dev up -d
docker-compose -f docker-compose.yml -f docker-compose-prod.yml -p didado-website-prod up -d

