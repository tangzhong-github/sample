package com.tangzhong.sample.framework.mybatis.service.impl;

import com.tangzhong.sample.common.api.dto.BaseDTO;
import com.tangzhong.sample.common.api.request.PageBaseRequest;
import com.tangzhong.sample.common.api.response.PageObject;
import com.tangzhong.sample.common.api.vo.BaseVO;
import com.tangzhong.sample.framework.core.BaseConverter;
import com.tangzhong.sample.framework.mybatis.entity.BaseEntity;
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
public abstract class BaseManageServiceImpl<T extends BaseEntity, D extends BaseDTO, C extends BaseConverter<T, ? extends BaseVO>, M extends BaseMapper<T>> extends BaseServiceImpl<T, M> implements IBaseManageService<T, D> {

    @Autowired
    protected C entityConverter;

    @Override
    public <S extends BaseVO, P extends PageBaseRequest> PageObject<S> pageList(Page<P, S> page) {
        List<S> list = getBaseMapper().pageList(page);
        return PageObject.of(page.getTotal(), list);
    }

    @Override
    public <V extends BaseVO> V detail(Long id) {
        T entity = getById(id);
        return (V) entityConverter.toVO(entity);
    }

}