#!/usr/bin/env bash

mvn clean install

docker-compose build

docker-compose run -e DISC_BOT_KEY="${DISC_BOT_KEY}" jseek