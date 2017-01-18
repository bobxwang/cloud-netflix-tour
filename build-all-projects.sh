#!/bin/bash

cd ./config-service; mvn clean package; cd ..;
cd ./eureka-service; mvn clean package; cd ..;
cd ./user-service; mvn clean package; cd ..;
cd ./bank-service; mvn clean package; cd ..;
cd ./api-gateway; mvn clean package; cd ..;
cd ./hystrix-dashboard; mvn clean package; cd ..;
cd ./turbine; mvn clean package; cd ..

cp ./config-service/target/configservice.jar ./bin/
cp ./eureka-service/target/eurekaservice.jar ./bin/
cp ./user-service/target/userservice.jar ./bin/
cp ./bank-service/target/bankservice.jar ./bin/
cp ./api-gateway/target/apigateway.jar ./bin/
cp ./hystrix-dashboard/target/hystrix-dashboard.jar ./bin/
cp ./turbine/target/turbine.jar ./bin/