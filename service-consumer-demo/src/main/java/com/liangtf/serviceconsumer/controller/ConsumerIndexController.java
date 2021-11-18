package com.liangtf.serviceconsumer.controller;

import com.liangtf.serviceconsumer.service.ConsumerHelloService;
import com.liangtf.springcloud.api.HelloServiceApi;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author: tengfeiliang
 * @Date: 2021/11/17 19:47
 */
@RestController
public class ConsumerIndexController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private HelloServiceApi helloServiceApi;
    @Autowired
    private ConsumerHelloService consumerHelloService;

    // 通过RestTemplate访问
    @HystrixCommand(fallbackMethod = "backError")
    @GetMapping("/")
    public String sayHi(@RequestParam(defaultValue = "Eureka") String username) {
        List<ServiceInstance> instanceList = discoveryClient.getInstances("eureka-client1");
        if (instanceList == null || instanceList.size() == 0) {
            return "未注册服务";
        }
        ServiceInstance instance = instanceList.get(0);

        // 使用了LoadBalanced 时 就需要使用 服务名 不能使用host
        String url = "http://" + instance.getServiceId() + ":" + instance.getPort() + "/sayHi?username=" + username;
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

    public String backError(String username) {
        return "hi, " + username + " sorry error";
    }

    // 调用consumer的服务 自己调用自己
    // 通过Feign 自建API访问
    @GetMapping("/hi2")
    public String sayHi2(@RequestParam(defaultValue = "Eureka Feign2") String username) {
        return consumerHelloService.sayHi(username);
    }

    // 通过Feign 公用API访问
    @GetMapping("/hi3")
    public String sayHi3(@RequestParam(defaultValue = "Eureka Feign3") String username) {
        return helloServiceApi.sayHi(username);
    }

    // 调用consumer的服务 自己调用自己
    @GetMapping("/sayHi2")
    public String sayHi22(@RequestParam(defaultValue = "Eureka", value = "username") String username) {
        return "Consumer say hi：" + username;
    }
}
