package com.tangzhong.sample.serve.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.tangzhong.sample.framework.mybatis.entity.MybatisEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author tangzhong
 * @date   2026-07-02 17:18
 * @since  V1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@TableName(value ="sys_operate_log")
public class SysOperateLog extends MybatisEntity {

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

    /** 是否成功：{@link com.tangzhong.sample.common.constant.CommonDictConstants#BOOLEAN} */
    private String isSuccess;

    /** 操作人名称 */
    private String operatorName;

    /** 操作耗时(ms) */
    private Long costTime;


}