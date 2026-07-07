package com.tangzhong.sample.framework.mybatis.metadata;

import jakarta.annotation.PostConstruct;

/**
 *
 * @author tangzhong
 * @date   2026-06-05 16:29
 * @since  V1.0.0
 */
public interface MetadataGenerator<T> {

    @PostConstruct
    default void register(){
        MetadataGeneratorFactory.register(this);
    }

    String metaDataType();

    T generate();

}
