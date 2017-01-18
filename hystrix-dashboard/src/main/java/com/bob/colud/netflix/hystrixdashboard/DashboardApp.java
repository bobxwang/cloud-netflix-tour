package com.bob.colud.netflix.hystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableHystrixDashboard
@Controller
public class DashboardApp {

    public static void main(String[] args) {
        SpringApplication.run(DashboardApp.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "forward:/hystrix/index.html";
    }

    /**
     * 这个是由Spring封装的，我们也可以使用别人利用vert.x封装的，是个胖jar包，地址如下：
     *
     * https://github.com/kennedyoliveira/standalone-hystrix-dashboard
     */
}