package com.liuming.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*@RibbonClients({
        @RibbonClient(name = "stock-nacos", configuration = NacosRuleRibbonConfig.class)
})*/
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
