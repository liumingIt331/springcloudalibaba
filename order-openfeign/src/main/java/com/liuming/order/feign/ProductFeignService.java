package com.liuming.order.feign;

import com.liuming.order.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "product-service", path = "/product", configuration = FeignConfig.class)
public interface ProductFeignService {

    @RequestMapping("/{id}")
    String query(@PathVariable("id") String id);
}
