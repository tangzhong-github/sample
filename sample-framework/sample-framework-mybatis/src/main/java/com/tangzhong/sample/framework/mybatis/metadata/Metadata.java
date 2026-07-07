package com.tangzhong.sample.framework.mybatis.metadata;

import org.apache.ibatis.mapping.SqlCommandType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author tangzhong
 * @date   2026-06-05 16:18
 * @since  V1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Metadata {

    /** 元数据类型 */
    String type() default MetadataFieldConstants.SNOWFLAKE_ID;

    /** 云数据的处理操作 */
    SqlCommandType[] executionCommands() default {SqlCommandType.INSERT};

}
