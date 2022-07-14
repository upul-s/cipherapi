#!/bin/bash

mvn clean
mvn install

docker build -t cipherapi:latest .

docker run -p 8080:8080 -t cipherapi
