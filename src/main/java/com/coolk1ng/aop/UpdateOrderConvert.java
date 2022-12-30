package com.coolk1ng.aop;

/**
 * @author coolk1ng
 * @since 2022/12/30 22:23
 */
public class UpdateOrderConvert implements Convert<UpdateOrder>{
    @Override
    public OperateLogDO convert(UpdateOrder updateOrder) {
        OperateLogDO operateLogDO = new OperateLogDO();
        operateLogDO.setOrderId(updateOrder.getUpdateOrderId());
        return operateLogDO;
    }
}
