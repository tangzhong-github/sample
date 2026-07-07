package com.tangzhong.sample.framework.common.api;

import lombok.Data;

import java.util.List;

/**
 *
 * @author tangzhong
 * @date   2026-06-12 14:13
 * @since  V1.0.0
 */
@Data
public class PageObject<T> {

    private long total;

    private List<T> list;

    public static <T> PageObject<T> of(long total, List<T> list) {
        PageObject<T> pageObject = new PageObject<>();
        pageObject.setTotal(total);
        pageObject.setList(list);
        return pageObject;
    }

}