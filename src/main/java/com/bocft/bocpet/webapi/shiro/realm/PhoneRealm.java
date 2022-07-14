package com.bocft.bocpet.webapi.shiro.realm;

import cn.hutool.core.util.StrUtil;
import com.bocft.bocpet.webapi.module.sysmgt.entity.User;
import com.bocft.bocpet.webapi.module.sysmgt.service.IPermService;
import com.bocft.bocpet.webapi.module.sysmgt.service.IRoleService;
import com.bocft.bocpet.webapi.module.sysmgt.service.IUserService;
import com.bocft.bocpet.webapi.shiro.token.PhoneToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class PhoneRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermService permService;

    //定义如何获取用户的角色和权限的逻辑，给shiro做权限判断
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        User user = (User) getAvailablePrincipal(principals);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(user.getRoles());
        info.setStringPermissions(user.getPerms());
        return info;
    }

    //定义如何获取用户信息的业务逻辑，给shiro做登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        PhoneToken upToken = (PhoneToken) token;
        String phone = upToken.getPhone();

        // Null username is invalid
        if (StrUtil.isBlank(phone)) {
            throw new AccountException("Blank phoneNumbers are not allowed by this realm.");
        }

        User user = userService.findUserByMobile(phone, upToken.getUserType());

        if (user == null) {
            throw new UnknownAccountException("No account found for phoneNumber [" + phone + "] , usertype: [" + upToken.getUserType() + "]");
        }

        //查询用户的角色和权限存到SimpleAuthenticationInfo中，这样在其它地方
        //SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
        Set<String> roles = roleService.getRoleValuesByUserId(user.getUid());
        Set<String> perms = permService.getPermValuesByUserId(user.getUid());
        user.getRoles().addAll(roles);
        user.getPerms().addAll(perms);

        return new SimpleAuthenticationInfo(user, phone, getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof PhoneToken;
    }
}
