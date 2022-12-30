package com.coolk1ng.aop;

/**
 * @author coolk1ng
 * @since 2022/12/30 22:20
 */
public class SaveOrderConvert implements Convert<SaveOrder>{
    @Override
    public OperateLogDO convert(SaveOrder saveOrder) {
        OperateLogDO operateLogDO = new OperateLogDO();
        operateLogDO.setOrderId(saveOrder.getSaveOrderId());
        return operateLogDO;
    }
}
