package com.tangzhong.sample.framework.mybatis.util;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.tangzhong.sample.framework.mybatis.entity.MybatisEntity;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 18:00
 * @since  V1.0.0
 */
public class WrapperUtil {

    private WrapperUtil(){}

    public static <T extends MybatisEntity> LambdaQueryWrapper<T> of(Class<T> clazz){
        return new LambdaQueryWrapper<>(clazz);
    }

    public static <T extends MybatisEntity> LambdaQueryWrapper<T> of(SFunction<T, ?> function, Object value){
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(function, value);
        return wrapper;
    }

}