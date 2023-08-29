#!/bin/bash

aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin ${REPO_URL}

docker pull ${REPO_URL}/${ECR_REPOSITORY}:latest
docker pull ${NGINX_URl}

docker compose up -d nginx
docker compose up -d redis

EXIST_BLUE=$(docker compose ps blue | grep Up)

if [ -z "$EXIST_BLUE" ]; then
    echo "blue up"
    docker compose up -d blue
    BEFORE_COMPOSE_COLOR="green"
    AFTER_COMPOSE_COLOR="blue"
    docker exec nginx sed -i "s|proxy_pass http://greenServer|proxy_pass http://blueServer|" /etc/nginx/conf.d/default.conf
else
    echo "green up"
    docker compose up -d green
    BEFORE_COMPOSE_COLOR="blue"
    AFTER_COMPOSE_COLOR="green"
    docker exec nginx sed -i "s|proxy_pass http://blueServer|proxy_pass http://greenServer|" /etc/nginx/conf.d/default.conf
fi

docker exec nginx nginx -s reload


sleep 10

EXIST_AFTER=$(docker compose ps ${AFTER_COMPOSE_COLOR}| grep Up)
if [ -n "$EXIST_AFTER" ]; then
    docker compose rm -svf ${BEFORE_COMPOSE_COLOR}
    echo "$BEFORE_COMPOSE_COLOR down"
fi
