package com.liangtf.eurekaclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tengfeiliang
 * @Date: 2021/11/17 19:18
 */
@RestController
public class IndexController {

    @GetMapping("/client/sayHi")
    public String sayHi(@RequestParam(defaultValue = "Eureka") String username) {
        return "Hi：" + username;
    }
}
