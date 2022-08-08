package com.liuming.stock.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/{id}")
    public String query(@PathVariable String id) {

        // 模拟超时
//        try {
//            Thread.sleep(6000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("商品信息查询成功！" + id);
        return "product:" + port;
    }
}
