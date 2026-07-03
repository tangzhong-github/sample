package com.tangzhong.sample.framework.security.util;

import com.tangzhong.sample.framework.security.entity.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author tangzhong
 * @date   2026-06-05 15:32
 * @since  V1.0.0
 */
public class SecurityUtil {

    public static UserInfo getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(Objects.nonNull(authentication) && authentication.getPrincipal() instanceof UserInfo userInfo){
            return userInfo;
        }
        return null;
    }

    public static Long getUserId(){
        return Optional.ofNullable(getUser()).map(UserInfo::getUserId).orElse(null);
    }

    public static String getUsername(){
        return Optional.ofNullable(getUser()).map(UserInfo::getUsername).orElse(null);
    }

}