#!/usr/bin/env bash

cd ./board-webservices/; ./mvnw spring-boot:run -Drun.jvmArguments='-Dserver.port=8083';