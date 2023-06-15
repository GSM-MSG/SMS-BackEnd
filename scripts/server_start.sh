#!/bin/bash


EXIST_BLUE=$(docker compose ps blue | grep Up)


if [ -z "$EXIST_BLUE" ]; then
    echo "blue up"
    docker compose up -d blue
    BEFORE_COMPOSE_COLOR="green"
    AFTER_COMPOSE_COLOR="blue"
else
    echo "green up"
    docker compose -d green
    BEFORE_COMPOSE_COLOR="blue"
    AFTER_COMPOSE_COLOR="green"
fi

sleep 10

EXIST_AFTER=$(docker compose ps ${AFTER_COMPOSE_COLOR}| grep Up)
if [ -n "$EXIST_AFTER" ]; then
    docker compose ps ${BEFORE_COMPOSE_COLOR} down
    echo "$BEFORE_COMPOSE_COLOR down"
fi
