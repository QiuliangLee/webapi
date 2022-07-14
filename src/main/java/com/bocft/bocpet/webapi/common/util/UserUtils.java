package com.bocft.bocpet.webapi.common.util;

import com.bocft.bocpet.webapi.module.sysmgt.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;

public class UserUtils {
    /**
     * 获取当前登录用户对象
     *
     * @return
     */
    public static User getCurrentUser() {
        User user = null;
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            user = (User) subject.getPrincipal();
        }
        return user;
    }

    /**
     * 获取当前登录用户ID
     *
     * @return
     */
    public static Integer getCurrentUserId() {
        Integer userId = null;
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.getPrincipal() != null) {
            userId = ((User) subject.getPrincipal()).getUid();
        }
        return userId;
    }

    /**
     * 获取当前登录用户名称
     *
     * @return
     */
    public static String getCurrentUserName() {
        String userName = null;
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.getPrincipal() != null) {
            userName = ((User) subject.getPrincipal()).getUname();
        }
        return userName;
    }

    public static String getCurrentUserOrgId() {
        String orgId = null;
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.getPrincipal() != null) {
            orgId = ((User) subject.getPrincipal()).getOrgId();
        }
        return orgId;
    }

    public static String getCurrentUserType() {
        String type = null;
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.getPrincipal() != null) {
            type = ((User) subject.getPrincipal()).getType();
        }
        return type;
    }

    /**
     * 获取当前session
     * 此处必须与ShiroConfig中的hashedCredentialsMatcher对应
     *
     * @return
     */
    public static String getCurrentSessionId() {
        return SecurityUtils.getSubject().getSession().getId().toString();
    }

    /**
     * 获取密码加密盐
     *
     * @return
     */
    public static String generateSalt(String uname) {
        return new Md5Hash(uname).toBase64();
    }

    /**
     * 根据算法生成密码
     * 此处必须与ShiroConfig中的hashedCredentialsMatcher对应
     *
     * @return
     */
    public static String encryptPwd(String salt, String plaintext) {
        return new Sha256Hash(plaintext, salt, 1024).toBase64();
    }

    /**
     * 用户名必须是小于30位字母数字
     */
    public static boolean isUnameLegal(String uname) {
        return uname != null && uname.matches("^[A-Za-z0-9]{5,30}$");
    }

    public static boolean hasRole(String... roleValue) {
        User user = null;
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            user = (User) subject.getPrincipal();
        }
        return user != null && user.hasRole(roleValue);
    }

}
