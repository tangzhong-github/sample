package com.tangzhong.sample.framework.common.service;

/**
 *
 * @author tangzhong
 * @date   2026-07-06 11:37
 * @since  V1.0.0
 */
public interface UserInfo {

    default Long getUserId(){throw new UnsupportedOperationException("unsupported to get user id");}

    default String getUserPassword(){throw new UnsupportedOperationException("unsupported to get user password");}

}
