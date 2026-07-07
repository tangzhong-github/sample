package com.tangzhong.sample.implementer.common.metadata.getter;

import com.tangzhong.sample.framework.common.metadata.MetadataTypeConstants;
import com.tangzhong.sample.framework.common.metadata.getter.AbstractMetadataGetter;
import com.tangzhong.sample.framework.security.util.SecurityUtil;
import org.springframework.stereotype.Component;

/**
 *
 * @author tangzhong
 * @date   2026-07-06 15:00
 * @since  V1.0.0
 */
@Component
public class UserNameGetter extends AbstractMetadataGetter<String> {

    public UserNameGetter(){
        super(MetadataTypeConstants.USERNAME);
    }

    @Override
    public String get() {
        return SecurityUtil.getUsername();
    }
}