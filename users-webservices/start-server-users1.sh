#!/usr/bin/env bash

cd ./users-webservices/; ./mvnw spring-boot:run -Drun.jvmArguments='-Dserver.port=8080';
