package com.tangzhong.sample.serve.sys.api.vo;

import com.tangzhong.sample.common.api.vo.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *
 * @author tangzhong
 * @date   2026-06-04 11:41
 * @since  V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleDetailVO extends BaseVO implements Serializable {

    /** 角色名 */
    private String roleName;

    /** 角色KEY */
    private String roleKey;

    /** 角色状态：1021 */
    private String status;

}