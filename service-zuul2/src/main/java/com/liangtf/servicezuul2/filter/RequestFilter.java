package com.liangtf.servicezuul2.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

/**
 * @author: tengfeiliang
 * @Date: 2021/11/18 14:57
 */
@Component
public class RequestFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("访问时间：" + System.currentTimeMillis());
        return null;
    }
}
