#!/bin/sh

##########
# ENV
##########

export MEMBER_SERVICE_IMAGE_NAME="cdd/member-service"
export MEMBER_SERVICE_TAG_NAME="0.0.1"

##########
# Build Api Gateway Image
##########

echo "\n🗑 Start Delete Docker Files"

if docker image inspect $MEMBER_SERVICE_IMAGE_NAME:$MEMBER_SERVICE_TAG_NAME &> /dev/null; then
    docker image rm -f $MEMBER_SERVICE_IMAGE_NAME:$MEMBER_SERVICE_TAG_NAME
fi

echo "\n🔨 Start Build Docker Image"

docker build \
-t $MEMBER_SERVICE_IMAGE_NAME:$MEMBER_SERVICE_TAG_NAME .

##########
# Api Gateway Container Start
##########

if [ "$(docker ps -aq -f name=$MEMBER_SERVICE_NAME)" ]; then
    echo "🚫 Stop Docker Container : "
    docker rm -f $MEMBER_SERVICE_NAME
else
    echo "🚫 There is no running container named $MEMBER_SERVICE_NAME"
fi

echo "🚀 Docker $MEMBER_SERVICE_NAME Container Start! : "
docker run -d \
--name $MEMBER_SERVICE_NAME \
-p $MEMBER_SERVICE_PORT:$MEMBER_SERVICE_PORT \
-e PROFILE=$MEMBER_SERVICE_PROFILE \
$MEMBER_SERVICE_IMAGE_NAME:$MEMBER_SERVICE_TAG_NAME