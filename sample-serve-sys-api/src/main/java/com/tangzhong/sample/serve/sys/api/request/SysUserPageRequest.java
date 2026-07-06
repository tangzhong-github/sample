package com.tangzhong.sample.serve.sys.api.request;

import com.tangzhong.sample.common.api.request.PageBaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author tangzhong
 * @date   2026-06-12 14:18
 * @since  V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserPageRequest extends PageBaseRequest {

    /** 用户名 */
    private String username;

    /** 用户状态：{@link com.tangzhong.sample.serve.sys.constant.SysDictConstants#USER_STATUS} */
    private String status;

}