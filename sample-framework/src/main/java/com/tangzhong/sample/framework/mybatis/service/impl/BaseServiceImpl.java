package com.tangzhong.sample.framework.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangzhong.sample.framework.mybatis.entity.BaseEntity;
import com.tangzhong.sample.framework.mybatis.service.IBaseService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 11:17
 * @since  V1.0.0
 */
public class BaseServiceImpl<T extends BaseEntity> extends ServiceImpl<BaseMapper<T>, T> implements IBaseService<T> {

    protected LambdaQueryWrapper<T> lambdaWrapper(){
        return new LambdaQueryWrapper<>(getEntityClass());
    }

    protected T getOne(SFunction<T, ?> function, @NotNull(message = "mybatis.lambda.wrapper.condition.value.not.be.null") Object value){
        return getOne(lambdaWrapper().eq(function, value));
    }

    protected <S> List<T> list(SFunction<T, S> function, @NotEmpty(message = "mybatis.lambda.wrapper.condition.values.not.be.empty") Collection<S> values){
        return list(lambdaWrapper().in(function, values));
    }

}