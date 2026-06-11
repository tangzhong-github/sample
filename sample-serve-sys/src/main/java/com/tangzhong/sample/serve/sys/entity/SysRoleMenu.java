package com.tangzhong.sample.serve.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzhong.sample.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SYS-角色菜单
 * @author tangzhong
 * @date   2026-06-04 11:11
 * @since  V1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@TableName(value ="sys_role_menu")
public class SysRoleMenu extends BaseEntity {

    /** 角色ID */
    private Long roleId;

    /** 菜单ID */
    private Long menuId;

}