#!/usr/bin/env bash

mvn clean install

docker build . -t jseek

docker run --name jseek -d -p 8080:8080 jseek