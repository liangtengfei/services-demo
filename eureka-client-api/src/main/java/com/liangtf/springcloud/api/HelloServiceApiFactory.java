package com.liangtf.springcloud.api;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: tengfeiliang
 * @Date: 2021/11/18 12:23
 */
@Component
public class HelloServiceApiFactory implements FallbackFactory<HelloServiceApi> {
    @Override
    public HelloServiceApi create(Throwable throwable) {
        return new HelloServiceApi() {
            @Override
            public String sayHi(String username) {
                return "服务器繁忙，请稍后再试";
            }
        };
    }
}
