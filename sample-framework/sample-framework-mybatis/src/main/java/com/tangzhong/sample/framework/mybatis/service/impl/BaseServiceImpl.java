package com.tangzhong.sample.framework.mybatis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tangzhong.sample.framework.common.util.AssertUtil;
import com.tangzhong.sample.framework.mybatis.entity.MybatisEntity;
import com.tangzhong.sample.framework.mybatis.service.IBaseService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 11:17
 * @since  V1.0.0
 */
@Slf4j
public abstract class BaseServiceImpl<T extends MybatisEntity, M extends BaseMapper<T>> extends ServiceImpl<M, T> implements IBaseService<T> {

    @Override
    public T getById(@NotNull(message = "the id must not be null") Serializable id) {
        T t = super.getById(id);
        AssertUtil.predict(Objects::nonNull, t, "业务信息不存在，请联系管理员！");
        return t;
    }

    protected T getOne(SFunction<T, ?> function, @NotNull(message = "mybatis.lambda.wrapper.condition.value.not.be.null") Object value){
        return getOne(queryWrapper().eq(function, value));
    }

    protected <S> List<T> list(SFunction<T, S> function, @NotEmpty(message = "mybatis.lambda.wrapper.condition.values.not.be.empty") Collection<S> values){
        return list(queryWrapper().in(function, values));
    }

    protected boolean update(Long id, SFunction<T, ?> function, Object value){
        LambdaUpdateWrapper<T> updateWrapper = this.updateWrapper(id);
        updateWrapper.set(function, value);
        return super.update(updateWrapper);
    }

    protected LambdaQueryWrapper<T> queryWrapper(){
        return new LambdaQueryWrapper<>(getEntityClass());
    }

    protected LambdaUpdateWrapper<T> updateWrapper(Long id){
        return new LambdaUpdateWrapper<>(getEntityClass()).eq(MybatisEntity::getId, id);
    }

}