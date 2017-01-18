#!/usr/bin/env bash

eurekaservice=$(ps -ef | grep eurekaservice.jar | grep -v grep | awk '{print $2}')
if [ -n "$eurekaservice" ]; then
    echo "want to kill "${eurekaservice}""
    kill "$eurekaservice"
else
    echo "no eurekaservice to kill"
fi

configservice=$(ps -ef | grep configservice.jar | grep -v grep | awk '{print $2}')
if [ -n "$configservice" ]; then
    echo "want to kill "${configservice}""
    kill "$configservice"
else
    echo "no configservice to kill"
fi

userservice=$(ps -ef | grep userservice.jar | grep -v grep | awk '{print $2}')
if [ -n "$userservice" ]; then
    echo "want to kill "${userservice}""
    kill "$userservice"
else
    echo "no userservice to kill"
fi

bankservice=$(ps -ef | grep bankservice.jar | grep -v grep | awk '{print $2}')
if [ -n "$bankservice" ]; then
    echo "want to kill "${bankservice}""
    kill "$bankservice"
else
    echo "no bankservice to kill"
fi

dashboard=$(ps -ef | grep hystrix-dashboard.jar | grep -v grep | awk '{print $2}')
if [ -n "$dashboard" ]; then
    echo "want to kill "${dashboard}""
    kill "$dashboard"
else
    echo "no dashboard to kill"
fi

apigateway=$(ps -ef | grep apigateway.jar | grep -v grep | awk '{print $2}')
if [ -n "$apigateway" ]; then
    echo "want to kill "${apigateway}""
    kill "$apigateway"
else
    echo "no apigateway to kill"
fi

nohup java -jar ./bin/eurekaservice.jar >/dev/null 2>&1 &
sleep 10s
nohup java -jar ./bin/configservice.jar >/dev/null 2>&1 &
sleep 20s
#nohup java -jar ./bin/hystrix-dashboard.jar >/dev/null 2>&1 &
#sleep 10s
nohup java -jar ./bin/bankservice.jar >/dev/null 2>&1 &
sleep 10s
nohup java -jar ./bin/userservice.jar >/dev/null 2>&1 &
sleep 10s
#nohup java -jar ./bin/apigateway.jar >/dev/null 2>&1 &
#sleep 10s

echo "service is start now ..."