package com.tangzhong.sample.framework.security.util;

import com.tangzhong.sample.framework.security.entity.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 *
 * @author tangzhong
 * @date   2026-06-05 15:32
 * @since  V1.0.0
 */
public class SecurityUtil {

    public static Long getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(Objects.nonNull(authentication) && authentication.getPrincipal() instanceof UserInfo userInfo){
            return userInfo.getUserId();
        }
        return null;
    }

}