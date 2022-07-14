package com.bocft.bocpet.webapi.common.pojo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class PageParam {
    private static final long DEFAULT_PAGE_NO = 1;
    private static final long DEFAULT_PAGE_SIZE = 10;
    private static final long UNLIMITED_PAGE_SIZE = -1;

    public static <T> Page<T> defaultPage() {
        return new Page<>(DEFAULT_PAGE_NO, DEFAULT_PAGE_SIZE);
    }

    public static <T> Page<T> unlimitedPage() {
        return new Page<>(DEFAULT_PAGE_NO, UNLIMITED_PAGE_SIZE);
    }

    public static <T> Page<T> page(long pageNo, long pageSize) {
        return new Page<>(pageNo, pageSize);
    }
}
