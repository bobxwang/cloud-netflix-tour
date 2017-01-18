# Description
a tourist using spring cloud

# Prerequisite
* basic knowledge of [springboot](http://projects.spring.io/spring-boot/)
* basic knowledge of [eureka](https://github.com/Netflix/eureka)
* basic knowledeg of [microservice](http://microservices.io/patterns/microservices.html)
* basic knowledge of [maven](http://maven.apache.org/)

# Structure
* eureka-service, is a service registry 
* config-service, is a config service, all config info is store here
* user-service, is a service about account
* bank-service, is a service about bank
* hystrix-dashboard, a web ui to check all service condition
* turbine, a web ui to check all instances about one service
* api-gateway, like facade, this is a service to invoke other service

# Todo 
* add docker 
 
