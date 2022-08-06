package com.liuming.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/deduce")
    public String deduce() {
        System.out.println("库存扣减成功！");
        return "库存扣减成功!";
    }
}
