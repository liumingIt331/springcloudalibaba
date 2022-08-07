package com.liuming.order;

import com.liuming.ribbon.NacosRuleRibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

@SpringBootApplication
/*@RibbonClients({
        @RibbonClient(name = "stock-nacos", configuration = NacosRuleRibbonConfig.class)
})*/
public class OrderNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderNacosApplication.class, args);
    }
}
