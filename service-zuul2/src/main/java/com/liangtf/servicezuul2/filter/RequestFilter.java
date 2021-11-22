package com.liangtf.servicezuul2.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: tengfeiliang
 * @Date: 2021/11/18 14:57
 */
@Component
public class RequestFilter extends ZuulFilter {
    private final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    public String filterType() {
        return "post";
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
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        LocalDateTime now = LocalDateTime.now();
        String nowStr = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String url = request.getRemoteHost() + ":" + request.getRequestURI();
        String resCode = currentContext.getResponseBody();
        logger.info("{} ---- 远程地址：{}, 方法：{}, 结果：{}", nowStr, request.getRequestURI(), request.getMethod(), resCode);
        return null;
    }
}
