package com.tangzhong.sample.serve.sys.api.vo;

import com.tangzhong.sample.common.api.vo.FullAuditInfoBaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * @author tangzhong
 * @date   2026-06-12 14:12
 * @since  V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserListVO extends FullAuditInfoBaseVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 用户类型 {@link com.tangzhong.sample.serve.sys.constant.SysDictConstants#USER_TYPE} */
    private String type;

    /** 用户名 */
    private String username;

    /** 用户状态：{@link com.tangzhong.sample.serve.sys.constant.SysDictConstants#USER_STATUS} */
    private String status;

}