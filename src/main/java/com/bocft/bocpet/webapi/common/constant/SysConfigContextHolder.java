package com.bocft.bocpet.webapi.common.constant;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailAccount;
import com.bocft.bocpet.webapi.common.exception.BocpetBizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SysConfigContextHolder {
    private static final Logger log = LoggerFactory.getLogger(SysConfigContextHolder.class);

    public static MailAccount getMailAccount() {
        MailAccount account = new MailAccount();
        account.setHost(getSysConfig("EMAIL_SMTP_HOST", String.class));
        account.setPort(getSysConfig("EMAIL_SMTP_PORT", Integer.class));
        account.setAuth(getSysConfig("EMAIL_SSL", Boolean.class));
        account.setFrom(getSysConfig("EMAIL_FROM", String.class));
        account.setUser(getSysConfig("EMAIL_USERNAME", String.class));
        account.setPass(getSysConfig("EMAIL_PASSWORD", String.class));
        return account;
    }

    public static <T> T getSysConfig(String configCode, Class<T> clazz) {
        return getSysConfig(configCode, clazz, false);
    }

    public static <T> T getSysConfig(String configCode, Class<T> clazz, Boolean nullThrowExp) {
        String configValue = SysConfigContext.me().getStr(configCode);
        if (ObjectUtil.isEmpty(configValue)) {
            if (nullThrowExp) {
                String format = StrUtil.format(">>> 系统配置sys_config表中存在空项，configCode为：{}", configCode);
                log.error(format);
                throw new BocpetBizException(format);
            } else {
                return null;
            }
        } else {
            try {
                return Convert.convert(clazz, configValue);
            } catch (Exception e) {
                if (nullThrowExp) {
                    String format = StrUtil.format(">>> 系统配置sys_config表中存在格式错误的值，configCode={}，configValue={}", configCode, configValue);
                    log.error(format);
                    throw new BocpetBizException(format);
                } else {
                    return null;
                }
            }
        }
    }
}
