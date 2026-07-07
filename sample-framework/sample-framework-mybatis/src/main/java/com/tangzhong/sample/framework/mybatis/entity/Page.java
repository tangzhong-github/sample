package com.tangzhong.sample.framework.mybatis.entity;

import com.tangzhong.sample.framework.common.base.BasePageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tangzhong
 * @date   2026-06-12 14:33
 * @since  V1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Page<P extends BasePageRequest, T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> {

    private P params;

    private String dateScope;

    public static <P extends BasePageRequest, T> Page<P, T> of(P params) {
        Page<P, T> page = new Page<>();
        page.setParams(params);
        page.setCurrent(params.getPageNum());
        page.setSize(params.getPageSize());
        return page;
    }

}