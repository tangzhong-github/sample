package com.tangzhong.sample.framework.common.metadata;

/**
 *
 * @author tangzhong
 * @date   2026-07-06 14:39
 * @since  V1.0.0
 */
public interface MetadataGetter<T> {

    String metadataType();

    default T get(){
        throw new UnsupportedOperationException(String.format("Unsupported to get the metadata value of [%s]", metadataType()));
    }

}
