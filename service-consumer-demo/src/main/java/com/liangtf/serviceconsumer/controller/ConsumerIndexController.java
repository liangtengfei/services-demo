package com.liangtf.serviceconsumer.controller;

import com.liangtf.serviceconsumer.service.ConsumerHelloService;
import com.liangtf.springcloud.api.HelloServiceApi;
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
    @GetMapping("/")
    public String sayHi(@RequestParam(defaultValue = "Eureka") String username) {
        List<ServiceInstance> instanceList = discoveryClient.getInstances("eureka-client1");
        if (instanceList == null || instanceList.size() == 0) {
            return "未注册服务";
        }
        ServiceInstance instance = instanceList.get(0);

        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/sayHi";
        return restTemplate.getForObject(url, String.class);
    }

    // 通过Feign 公用API访问
    @GetMapping("/hi2")
    public String sayHi2(@RequestParam(defaultValue = "Eureka Feign2") String username) {
        return consumerHelloService.sayHi(username);
    }

    // 通过Feign 自建API访问
    @GetMapping("/hi3")
    public String sayHi3(@RequestParam(defaultValue = "Eureka Feign3") String username) {
        return helloServiceApi.sayHi(username);
    }
}
