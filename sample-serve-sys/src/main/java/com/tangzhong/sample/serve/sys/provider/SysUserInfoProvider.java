package com.tangzhong.sample.serve.sys.provider;

import com.tangzhong.sample.common.constant.PermissionKeyConstants;
import com.tangzhong.sample.framework.security.provider.UserInfoProvider;
import com.tangzhong.sample.serve.sys.constant.SysDictConstants;
import com.tangzhong.sample.serve.sys.entity.SysMenu;
import com.tangzhong.sample.serve.sys.entity.SysUser;
import com.tangzhong.sample.serve.sys.service.ISysMenuService;
import com.tangzhong.sample.serve.sys.service.ISysRoleMenuService;
import com.tangzhong.sample.serve.sys.service.ISysRoleService;
import com.tangzhong.sample.serve.sys.service.ISysUserRoleService;
import com.tangzhong.sample.serve.sys.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author tangzhong
 * @date   2026-06-05 11:58
 * @since  V1.0.0
 */
@Component
@RequiredArgsConstructor
public class SysUserInfoProvider implements UserInfoProvider<SysUser> {

    private final ISysUserService sysUserService;
    private final ISysMenuService sysMenuService;
    private final ISysRoleService sysRoleService;
    private final ISysUserRoleService sysUserRoleService;
    private final ISysRoleMenuService sysRoleMenuService;

    @Override
    public SysUser getUser(String username) {
        return sysUserService.getByUsername(username);
    }

    @Override
    public void checkUser(SysUser userEntity) {
        if(Objects.isNull(userEntity)){
            throw new UsernameNotFoundException("用户不存在");
        }
        if(Objects.equals(userEntity.getStatus(), SysDictConstants.USER_STATUS_FREEZE)){
            throw new UsernameNotFoundException("当前用户已被冻结，请联系管理员");
        }
    }

    @Override
    public String getPassword(SysUser userEntity) {
        return userEntity.getPassword();
    }

    @Override
    public List<String> getPermissions(Long userId) {
        //查询用户角色列表
        List<Long> userRoleIds = sysUserRoleService.getUserRoleIds(userId);
        if(CollectionUtils.isEmpty(userRoleIds)){
            return Collections.emptyList();
        }
        //判断是否是管理员角色，若是管理员角色则返回所有权限
        if(sysRoleService.existsAdminRole(userRoleIds)){
            return Collections.singletonList(PermissionKeyConstants.ALL);
        }
        //通过用户角色查询用户所有菜单
        List<Long> menuIds = sysRoleMenuService.getMenuIds(userRoleIds);
        if(CollectionUtils.isEmpty(menuIds)){
            return Collections.emptyList();
        }
        List<SysMenu> sysMenus = sysMenuService.listByIds(menuIds);
        //返回权限列表
        return sysMenus.stream().map(SysMenu::getPermissionKey).filter(StringUtils::hasText).distinct().toList();
    }

}