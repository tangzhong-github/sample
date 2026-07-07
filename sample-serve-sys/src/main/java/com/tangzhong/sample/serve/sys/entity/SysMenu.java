package com.tangzhong.sample.serve.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzhong.sample.framework.mybatis.entity.MybatisEntity;
import com.tangzhong.sample.serve.sys.constant.SysDict;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 11:11
 * @since  V1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@TableName(value ="sys_menu")
public class SysMenu extends MybatisEntity {

    /** 菜单名称 */
    private String menuName;

    /** 菜单类型：{@link SysDict#MENU_TYPE} */
    private String type;

    /** 父级菜单ID */
    private Long parentId;

    /** 权限码 */
    private String permissionKey;

    /** 菜单状态：{@link SysDict#MENU_STATUS} */
    private String status;

}