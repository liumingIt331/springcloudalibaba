package com.liuming.seata.order.service;

import com.liuming.seata.order.feign.StockFeignClient;
import com.liuming.seata.order.model.Order;
import com.liuming.seata.order.repository.OrderDAO;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class OrderService {

    @Resource
    private StockFeignClient stockFeignClient;

    @Resource
    private OrderDAO orderDAO;

    /**
     * 下单：创建订单、减库存，涉及到两个服务
     *
     * @param userId
     * @param commodityCode
     * @param count
     */
    @GlobalTransactional
//    @Transactional(rollbackFor = Exception.class)
    public void placeOrder(String userId, String commodityCode, Integer count) {
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));
        Order order = new Order().setUserId(userId).setCommodityCode(commodityCode).setCount(count).setMoney(
                orderMoney);
        orderDAO.insert(order);

        stockFeignClient.deduct(commodityCode, count);

        if (commodityCode.equals("product-2")) {
            throw new RuntimeException("异常:模拟业务异常:stock branch exception");
        }

    }
}
