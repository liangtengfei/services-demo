package com.liangtf.servicezuul2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class ServiceZuul2Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceZuul2Application.class, args);
        System.out.println("网关服务启动成功");
    }

}
