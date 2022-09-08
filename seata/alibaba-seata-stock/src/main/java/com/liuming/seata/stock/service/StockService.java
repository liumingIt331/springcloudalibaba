package com.liuming.seata.stock.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuming.seata.stock.entity.Stock;
import com.liuming.seata.stock.repository.StockDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class StockService {

    @Resource
    private StockDAO stockDAO;

    /**
     * 减库存
     *
     * @param commodityCode
     * @param count
     */
    @Transactional(rollbackFor = Exception.class)
    public void deduct(String commodityCode, int count) {
        /*if (commodityCode.equals("product-2")) {
            throw new RuntimeException("异常:模拟业务异常:stock branch exception");
        }*/

        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new Stock().setCommodityCode(commodityCode));
        Stock stock = stockDAO.selectOne(wrapper);
        stock.setCount(stock.getCount() - count);

        stockDAO.updateById(stock);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(String commodityCode, int count) {
        Stock stock = new Stock();
        stock.setCommodityCode(commodityCode);
        stock.setCount((long) count);

        stockDAO.insert(stock);
    }
}
