package com.bocft.bocpet.webapi.common.constant;

import java.util.HashMap;

public class SysDictSingleton {
    private static final HashMap<String, String> sysDict = new HashMap<>();

    private SysDictSingleton() {
    }

    public static HashMap<String, String> me() {
        return sysDict;
    }

    public static String getValue(String type, String key) {
        String value = sysDict.get(type + "-" + key);
        return value == null ? "" : value;
    }
}
