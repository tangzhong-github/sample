package com.tangzhong.sample.framework.security.provider;

import com.tangzhong.sample.framework.mybatis.entity.BaseEntity;

import java.util.List;

/**
 * @author tangzhong
 * @date   2026-06-04 17:05
 * @since  V1.0.0
 */
public interface UserInfoProvider<T extends BaseEntity> {

    T getUser(String username);

    void checkUser(T userEntity);

    String getPassword(T userEntity);

    List<String> getPermissions(Long userId);

}
