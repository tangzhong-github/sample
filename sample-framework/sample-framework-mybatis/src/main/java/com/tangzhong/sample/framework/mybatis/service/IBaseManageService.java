package com.tangzhong.sample.framework.mybatis.service;

import com.tangzhong.sample.framework.common.api.PageObject;
import com.tangzhong.sample.framework.common.base.BaseDTO;
import com.tangzhong.sample.framework.common.base.BasePageRequest;
import com.tangzhong.sample.framework.common.base.BaseVO;
import com.tangzhong.sample.framework.mybatis.entity.MybatisEntity;
import com.tangzhong.sample.framework.mybatis.entity.Page;

/**
 *
 * @author tangzhong
 * @date   2026-06-12 15:44
 * @since  V1.0.0
 */
public interface IBaseManageService<T extends MybatisEntity, D extends BaseDTO> extends IBaseService<T>{

    <S extends BaseVO, P extends BasePageRequest> PageObject<S> pageList(Page<P, S> page);

    <V extends BaseVO> V detail(Long id);

    default boolean add(D dto){
        throw new UnsupportedOperationException("该服务暂不支持新增！");
    }

    default boolean edit(D dto){
        throw new UnsupportedOperationException("该服务暂不支持修改！");
    }

    default boolean delete(Long id){
        throw new UnsupportedOperationException("该服务暂不支持删除！");
    }

}
