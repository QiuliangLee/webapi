package com.bocft.bocpet.webapi.common.constant;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ObjectUtil;

public class SysConfigContext {

    private static final Dict CONSTANTS_HOLDER = Dict.create();

    private SysConfigContext() {
    }

    public static Dict me() {
        return CONSTANTS_HOLDER;
    }

    public static void putConstant(String key, Object value) {
        if (ObjectUtil.hasEmpty(key, value)) {
            return;
        }
        CONSTANTS_HOLDER.put(key, value);
    }
}
