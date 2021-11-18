package com.liangtf.springcloud.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: tengfeiliang
 * @Date: 2021/11/18 10:01
 */
@FeignClient(value = "eureka-client1", path = "/")
public interface HelloServiceApi {
    @GetMapping(path = "sayHi3")
    String sayHi(@RequestParam(value = "username") String username);
}
