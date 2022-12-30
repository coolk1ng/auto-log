package com.coolk1ng.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author coolk1ng
 * @since 2022/12/30 21:47
 */
@Service
@Slf4j
public class OrderService {

    @RecordOperate(desc = "保存订单", convert = SaveOrderConvert.class)
    public Boolean saveOrder(SaveOrder saveOrder) {
        log.info("save order , saveOrderId: {}", saveOrder.getSaveOrderId());
        return true;
    }

    @RecordOperate(desc = "更新订单", convert = UpdateOrderConvert.class)
    public Boolean updateOrder(UpdateOrder updateOrder) {
        log.info("save order , updateOrderId: {}", updateOrder.getUpdateOrderId());
        return true;
    }
}
