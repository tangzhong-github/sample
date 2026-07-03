package com.tangzhong.sample.serve.sys.pojo.dto;

import com.tangzhong.sample.common.api.dto.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author tangzhong
 * @date   2026-06-05 17:11
 * @since  V1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
public class SysOperateLogDTO extends BaseDTO {

    /** traceId */
    private String traceId;

    /** IP */
    private String ip;

    /** 操作名称 */
    private String operationName;

    /** 请求方式 */
    private String requestMethod;

    /** 请求URL */
    private String requestUrl;

    /** 方法签名 */
    private String methodSignature;

    /** 请求参数 */
    private String requestParams;

    /** 返回结果 */
    private String responseData;

    /** 操作异常信息 */
    private String exceptionInfo;

    /** 耗时(ms) */
    private Long costTime;

    /** 是否成功：{@link com.tangzhong.sample.common.constant.CommonDictConstants#BOOLEAN} */
    private String isSuccess;

}