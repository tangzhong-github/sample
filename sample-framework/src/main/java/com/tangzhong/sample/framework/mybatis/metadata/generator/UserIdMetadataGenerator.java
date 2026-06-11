package com.tangzhong.sample.framework.mybatis.metadata.generator;

import com.tangzhong.sample.framework.mybatis.metadata.MetadataGenerator;
import com.tangzhong.sample.framework.mybatis.metadata.MetadataTypeConstants;
import com.tangzhong.sample.framework.security.util.SecurityUtil;
import org.springframework.stereotype.Component;

/**
 *
 * @author tangzhong
 * @date   2026-06-05 16:29
 * @since  V1.0.0
 */
@Component
public class UserIdMetadataGenerator implements MetadataGenerator<Long> {

    @Override
    public String metaDataType() {
        return MetadataTypeConstants.USER_ID;
    }

    @Override
    public Long generate() {
        return SecurityUtil.getUserId();
    }

}
