package com.tangzhong.sample.framework.security.service;

import com.tangzhong.sample.framework.common.service.UserInfo;

import java.util.List;

/**
 * @author tangzhong
 * @date   2026-06-04 17:05
 * @since  V1.0.0
 */
public interface UserInfoProvider {

    UserInfo getUser(String username);

    List<String> getPermissions(Long userId);

}
