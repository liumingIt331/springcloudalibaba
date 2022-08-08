package com.liuming.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.UUID;

public class FeignAuthRequestInterceptor implements RequestInterceptor {

    public void apply(RequestTemplate requestTemplate) {
        // 采用feign的RequestInterceptor 模拟往请求头传入身份验证信息
        // 公共的，每次调用都会执行

        String accessToken = UUID.randomUUID().toString();
        requestTemplate.header("Authorization", accessToken);
    }
}
