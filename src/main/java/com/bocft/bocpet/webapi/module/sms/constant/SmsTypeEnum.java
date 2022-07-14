package com.bocft.bocpet.webapi.module.sms.constant;

public enum SmsTypeEnum {

    SIGN_UP(1, "SIGN_UP", "SIGNUP_CODE:"),
    SIGN_IN(2, "SIGN_IN", "SIGNIN_CODE:"),
    RESET_PWD(3, "RESET_PWD", "RESETPWD_CODE:");

    public final int code;
    public final String type;
    public final String sessionPrefix;

    SmsTypeEnum(final int code, final String type, String sessionPrefix) {
        this.code = code;
        this.type = type;
        this.sessionPrefix = sessionPrefix;
    }
}
