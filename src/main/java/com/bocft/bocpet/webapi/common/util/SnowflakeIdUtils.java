package com.bocft.bocpet.webapi.common.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SnowflakeIdUtils {
    private static final Integer workerId;
    private static final Snowflake snowflake;

    static {
        workerId = Math.abs(getHostName().hashCode()) % 32;
        snowflake = IdUtil.getSnowflake(workerId, 1);
    }

    public static Long nextId() {
        return snowflake.nextId();
    }

    public static String nextIdStr() {
        return snowflake.nextIdStr();
    }

    private static String getHostName() {
        if (System.getenv("COMPUTERNAME") != null) {
            return System.getenv("COMPUTERNAME");
        } else {
            return getHostNameForLiunx();
        }
    }

    private static String getHostNameForLiunx() {
        try {
            return (InetAddress.getLocalHost()).getHostName();
        } catch (UnknownHostException uhe) {
            String host = uhe.getMessage(); // host = "hostname: hostname"
            if (host != null) {
                int colon = host.indexOf(':');
                if (colon > 0) {
                    return host.substring(0, colon);
                }
            }
            return "UnknownHost";
        }
    }
}
