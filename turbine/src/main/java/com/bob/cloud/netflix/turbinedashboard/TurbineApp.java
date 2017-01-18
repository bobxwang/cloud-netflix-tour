package com.bob.cloud.netflix.turbinedashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableTurbine
@SpringBootApplication
@Controller
public class TurbineApp {

    public static void main(String[] args) {

        SpringApplication.run(TurbineApp.class, args);

        /**
         * 通过在每个app本身暴露/hystrix.stream，然后hystrix.dashboard监控
         * 但一般每个app都会布置多台进行集群，此时我们就可以通过turbine来将每个app的数据进行统一收集，
         * hystrix.dashboard就只要监控这个turbine即可。
         *
         * turbineapp.host.url/turbine.stream?cluster=appname
         */
    }

    @RequestMapping("/")
    public String home() {
        return "forward:/turbine.stream";
    }
}