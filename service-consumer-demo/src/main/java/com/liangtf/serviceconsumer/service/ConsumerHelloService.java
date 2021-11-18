package com.liangtf.serviceconsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: tengfeiliang
 * @Date: 2021/11/18 11:10
 */
@FeignClient(value = "service-consumer", path = "/")
public interface ConsumerHelloService {
    @GetMapping(path = "sayHi2")
    String sayHi(@RequestParam(value = "username") String username);
}
