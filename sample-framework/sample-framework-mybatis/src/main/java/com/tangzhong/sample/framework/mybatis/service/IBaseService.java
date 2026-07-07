package com.tangzhong.sample.framework.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangzhong.sample.framework.mybatis.entity.MybatisEntity;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 11:16
 * @since  V1.0.0
 */
public interface IBaseService<T extends MybatisEntity> extends IService<T> {



}
