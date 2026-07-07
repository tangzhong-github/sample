package com.tangzhong.sample.framework.common.util;

import com.tangzhong.sample.framework.common.exception.BizException;

import java.util.function.Predicate;

/**
 *
 * @author tangzhong
 * @date   2026-06-16 10:05
 * @since  V1.0.0
 */
public class AssertUtil {

    /**
     * 断言失败时，抛出业务异常
     * @param predicate 断言条件
     * @param t         断言对象
     * @param message   断言失败信息
     */
    public static <T> void predict(Predicate<T> predicate, T t, String message){
        if(!predicate.test(t)){
            throw new BizException(message);
        }
    }

}