package com.liuming.order.controller;

import com.liuming.order.feign.ProductFeignService;
import com.liuming.order.feign.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ProductFeignService productFeignService;

    @Autowired
    private StockFeignService stockFeignService;

    @RequestMapping("/add")
    public String add() {
        System.out.println("下单成功！");

        String product = productFeignService.query("124");

        String deduce = stockFeignService.deduce();

        return "Hello Feign! " + product + "," + deduce;
    }
}
