#!/bin/bash
mvn clean install
docker build -t code-challenge-app .
docker run -it -d -p 8081:8081 code-challenge-app
