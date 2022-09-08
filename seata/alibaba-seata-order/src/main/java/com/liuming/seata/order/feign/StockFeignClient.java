package com.liuming.seata.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "alibaba-seata-stock", path = "/stock")
public interface StockFeignClient {

    @GetMapping("/deduct")
    Boolean deduct(@RequestParam("commodityCode") String commodityCode, @RequestParam("count") Integer count);
}
