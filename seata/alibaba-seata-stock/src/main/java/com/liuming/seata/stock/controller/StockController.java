package com.liuming.seata.stock.controller;

import com.liuming.seata.stock.service.StockService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Resource
    private StockService stockService;

    /**
     * 减库存
     * <p>
     * product-2
     *
     * @param commodityCode 商品代码
     * @param count         数量
     * @return
     */
    @RequestMapping(path = "/deduct")
    public Boolean deduct(String commodityCode, Integer count) {
        stockService.deduct(commodityCode, count);
        return true;
    }

    /**
     * 新增测试
     * http://localhost:8102/stock/insert?commodityCode=product-2&count=100
     *
     * @param commodityCode
     * @param count
     * @return
     */
    @RequestMapping(path = "/insert")
    public Boolean insert(String commodityCode, Integer count) {
        stockService.insert(commodityCode, count);
        return true;
    }
}
