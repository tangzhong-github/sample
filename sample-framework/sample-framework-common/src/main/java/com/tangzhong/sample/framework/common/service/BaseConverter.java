package com.tangzhong.sample.framework.common.service;

import com.tangzhong.sample.framework.common.base.BaseEntity;
import com.tangzhong.sample.framework.common.base.BaseVO;
import org.mapstruct.Mapping;


/**
 *
 * @author tangzhong
 * @date   2026-06-12 16:35
 * @since  V1.0.0
 */
public interface BaseConverter<E extends BaseEntity, V extends BaseVO> {

    @Mapping(source = "id", target = "id")
    V toVO(E entity);

}

