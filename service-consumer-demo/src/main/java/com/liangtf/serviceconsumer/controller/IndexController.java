package com.liangtf.serviceconsumer.controller;

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
public class IndexController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/")
    public String sayHi(@RequestParam(defaultValue = "Eureka") String username) {
        List<ServiceInstance> instanceList = discoveryClient.getInstances("eureka-client1");
        if (instanceList == null || instanceList.size() == 0) {
            return "未注册服务";
        }
        ServiceInstance instance = instanceList.get(0);

        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/client/sayHi";
        return restTemplate.getForObject(url, String.class);
    }
}
