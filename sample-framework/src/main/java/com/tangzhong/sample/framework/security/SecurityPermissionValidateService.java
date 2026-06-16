package com.tangzhong.sample.framework.security;

import com.tangzhong.sample.common.constant.PermissionKeyConstants;
import com.tangzhong.sample.framework.security.entity.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

/**
 * 用户权限验证服务
 * @author tangzhong
 * @date   2026-06-15 17:03
 * @since  V1.0.0
 */
@Component("ss")
public class SecurityPermissionValidateService {

    public boolean hasPermission(String permission) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            return false;
        }
        if(authentication.getPrincipal() instanceof UserInfo userInfo){
            Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                String userPermission = authority.getAuthority();
                //if (Objects.equals(userPermission, PermissionKeyConstants.ALL)) {
                    //return true;
                //}
                if (Objects.equals(userPermission, permission)) {
                    return true;
                }
            }
        }
        return false;
    }

}