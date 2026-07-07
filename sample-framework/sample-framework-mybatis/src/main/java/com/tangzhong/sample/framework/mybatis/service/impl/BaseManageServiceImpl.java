package com.tangzhong.sample.framework.mybatis.service.impl;

import com.tangzhong.sample.framework.common.api.PageObject;
import com.tangzhong.sample.framework.common.base.BaseDTO;
import com.tangzhong.sample.framework.common.base.BasePageRequest;
import com.tangzhong.sample.framework.common.base.BaseVO;
import com.tangzhong.sample.framework.common.service.BaseConverter;
import com.tangzhong.sample.framework.mybatis.entity.MybatisEntity;
import com.tangzhong.sample.framework.mybatis.entity.Page;
import com.tangzhong.sample.framework.mybatis.mapper.BaseMapper;
import com.tangzhong.sample.framework.mybatis.service.IBaseManageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 11:17
 * @since  V1.0.0
 */
@SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "unchecked"})
public abstract class BaseManageServiceImpl<T extends MybatisEntity, D extends BaseDTO, C extends BaseConverter<T, ? extends BaseVO>, M extends BaseMapper<T>> extends BaseServiceImpl<T, M> implements IBaseManageService<T, D> {

    @Autowired
    protected C converter;

    @Override
    public <S extends BaseVO, P extends BasePageRequest> PageObject<S> pageList(Page<P, S> page) {
        List<S> list = getBaseMapper().pageList(page);
        return PageObject.of(page.getTotal(), list);
    }

    @Override
    public <V extends BaseVO> V detail(Long id) {
        T entity = getById(id);
        return (V) converter.toVO(entity);
    }

}