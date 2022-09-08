package com.liuming.seata.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@MapperScan("com.liuming.seata.order.repository")
public class AlibabaSeataOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaSeataOrderApplication.class, args);
    }
}
