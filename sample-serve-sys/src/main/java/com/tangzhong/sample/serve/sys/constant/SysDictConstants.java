package com.tangzhong.sample.serve.sys.constant;

import com.tangzhong.sample.common.constant.CommonDictConstants;

/**
 *
 * @author tangzhong
 * @date   2026-06-05 11:01
 * @since  V1.0.0
 */
public interface SysDictConstants extends CommonDictConstants {

    /** 用户类型 */
    String USER_TYPE = "1011";
    /** 用户类型：管理员 */
    String USER_TYPE_SYS_ADMIN = "10110001";
    /** 用户类型：普通用户 */
    String USER_TYPE_ORDINARY = "10110002";

    /** 用户状态 */
    String USER_STATUS = "1012";
    /** 用户状态：正常 */
    String USER_STATUS_NORMAL = "10120001";
    /** 用户状态：冻结 */
    String USER_STATUS_FREEZE = "10120002";

    /** 角色状态 */
    String ROLE_STATUS = "1021";
    /** 角色状态：正常 */
    String ROLE_STATUS_NORMAL = "10210001";
    /** 角色状态：禁用 */
    String ROLE_STATUS_DISABLE = "10210002";

    /** 菜单类型 */
    String MENU_TYPE = "1031";
    /** 菜单类型：目录 */
    String MENU_TYPE_DIRECTORY = "10310001";
    /** 菜单类型：菜单 */
    String MENU_TYPE_MENU = "10310002";
    /** 菜单类型：按钮 */
    String MENU_TYPE_BUTTON = "10310003";

    /** 菜单状态 */
    String MENU_STATUS = "1032";
    /** 菜单状态：正常 */
    String MENU_STATUS_NORMAL = "10320001";
    /** 菜单状态：禁用 */
    String MENU_STATUS_DISABLE = "10320002";
    /** 菜单状态：隐藏 */
    String MENU_STATUS_HIDE = "10320003";

}
