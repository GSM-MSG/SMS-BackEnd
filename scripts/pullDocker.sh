#!/bin/bash

aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin ${REPO_URL}

docker pull ${REPO_URL}
