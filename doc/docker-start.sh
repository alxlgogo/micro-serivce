#!/bin/zsh
docker stop mysql
docker rm micro-service-mysql
docker run --name micro-service-mysql -v