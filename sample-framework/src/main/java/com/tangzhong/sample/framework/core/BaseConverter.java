package com.tangzhong.sample.framework.core;

import com.tangzhong.sample.common.api.vo.BaseVO;
import com.tangzhong.sample.framework.mybatis.entity.BaseEntity;
import org.mapstruct.Mapping;


/**
 *
 * @author tangzhong
 * @date   2026-06-12 16:35
 * @since  V1.0.0
 */
public interface BaseConverter<E extends BaseEntity, DETAIL extends BaseVO> {

    @Mapping(source = "id", target = "id")
    DETAIL toVO(E entity);

}

