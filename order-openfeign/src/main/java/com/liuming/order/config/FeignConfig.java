package com.liuming.order.config;

import com.liuming.order.interceptor.FeignAuthRequestInterceptor;
import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration会全局生效，如果需要指定微服务生效，则不能加，在@FeignClient注解中加上配置
@Configuration
public class FeignConfig {

    /**
     * 配置日志级别
     *
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public FeignAuthRequestInterceptor interceptor() {
        return new FeignAuthRequestInterceptor();
    }

    /**
     * 参数1  连接超时时间
     * 参数2  请求处理超时时间
     * @return
     */
//    @Bean
//    public Request.Options options() {
//        return new Request.Options(3000, 6000);
//    }
}
