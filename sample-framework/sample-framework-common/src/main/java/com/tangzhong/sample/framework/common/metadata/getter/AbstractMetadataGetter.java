package com.tangzhong.sample.framework.common.metadata.getter;

import com.tangzhong.sample.framework.common.metadata.MetadataGetter;
import com.tangzhong.sample.framework.common.metadata.MetadataGetterFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 *
 * @author tangzhong
 * @date   2026-07-06 14:44
 * @since  V1.0.0
 */
public abstract class AbstractMetadataGetter<T> implements MetadataGetter<T>, InitializingBean {

    private final String metadataType;

    protected AbstractMetadataGetter(String metadataType){
        this.metadataType = metadataType;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        MetadataGetterFactory.register(this);
    }

    @Override
    public String metadataType() {
        return this.metadataType;
    }

}