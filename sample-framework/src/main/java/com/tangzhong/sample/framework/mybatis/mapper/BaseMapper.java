package com.tangzhong.sample.framework.mybatis.mapper;

import com.tangzhong.sample.common.api.request.PageBaseRequest;
import com.tangzhong.sample.common.api.vo.BaseVO;
import com.tangzhong.sample.framework.mybatis.entity.BaseEntity;
import com.tangzhong.sample.framework.mybatis.entity.Page;

import java.util.List;

/**
 * @author tangzhong
 * @date   2026-06-12 15:24
 * @since  V1.0.0
 */
public interface BaseMapper<T extends BaseEntity> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

    <S extends BaseVO, P extends PageBaseRequest> List<S> pageList(Page<P, S> page);

}
