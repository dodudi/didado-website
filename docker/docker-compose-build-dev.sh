#!/bin/sh

#프로젝트 빌드
cd ..
./gradlew clean build
cp build/libs/website-0.0.1-SNAPSHOT.jar docker/app.jar

#도커 이미지, 컨테이너 초기화
docker-compose down
#docker container rm -f didado-website-container
docker image rm -f didado-website

#도커 이미지 생성, 컴포즈 실행
docker image build --tag didado-website docker/.
cd docker/
docker-compose up -d

#docker container run -d -p 8081:8081 --network didado-net --name didado-website-container didado-website
