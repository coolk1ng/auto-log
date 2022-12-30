package com.coolk1ng.aop;

/**
 * @author coolk1ng
 * @since 2022/12/30 22:17
 */
public interface Convert<T> {
    OperateLogDO convert(T t);
}
