package com.bocft.bocpet.webapi.shiro.token;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

import java.io.Serializable;

public class PhoneToken implements HostAuthenticationToken, RememberMeAuthenticationToken, Serializable {

    // 手机号码
    private String phone;
    private String userType;
    private boolean rememberMe;
    private String host;

    /**
     * 重写getPrincipal方法
     */
    @Override
    public Object getPrincipal() {
        return phone;
    }

    /**
     * 重写getCredentials方法
     */
    @Override
    public Object getCredentials() {
        return phone;
    }

    public PhoneToken() {
        this.rememberMe = false;
    }

    public PhoneToken(String phone, String userType) {
        this(phone, userType, false, null);
    }

    public PhoneToken(String phone, String userType, boolean rememberMe) {
        this(phone, userType, rememberMe, null);
    }

    public PhoneToken(String phone, String userType, boolean rememberMe, String host) {
        this.phone = phone;
        this.userType = userType;
        this.rememberMe = rememberMe;
        this.host = host;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public boolean isRememberMe() {
        return rememberMe;
    }
}
