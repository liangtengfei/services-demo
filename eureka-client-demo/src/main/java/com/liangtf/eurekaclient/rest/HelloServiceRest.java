package com.liangtf.eurekaclient.rest;

import com.liangtf.springcloud.api.HelloServiceApi;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tengfeiliang
 * @Date: 2021/11/18 10:04
 */
@RestController
public class HelloServiceRest implements HelloServiceApi {
    // 通过Feign访问
    @Override
    public String sayHi(String username) {
        return "Feign Response：" + username;
    }
}
