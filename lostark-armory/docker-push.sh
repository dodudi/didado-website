../gradlew clean :lostark-armory:build
docker image build --tag didadu/lostark-armory-image:v1.0.0 .
docker push didadu/lostark-armory-image:v1.0.0