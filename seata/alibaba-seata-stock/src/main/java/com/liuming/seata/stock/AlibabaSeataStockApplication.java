package com.liuming.seata.stock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liuming.seata.stock.repository")
public class AlibabaSeataStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaSeataStockApplication.class, args);
    }
}
