package com.bocft.bocpet.webapi.module.sysmgt.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bocft.bocpet.webapi.common.constant.SysConfigContextHolder;
import com.bocft.bocpet.webapi.common.enums.ResultCodeEnum;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.common.util.CaptchaUtils;
import com.bocft.bocpet.webapi.common.util.UserUtils;
import com.bocft.bocpet.webapi.module.sms.constant.SmsTypeEnum;
import com.bocft.bocpet.webapi.module.sysmgt.constant.SysMgtConstants;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Org;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Perm;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Role;
import com.bocft.bocpet.webapi.module.sysmgt.entity.User;
import com.bocft.bocpet.webapi.module.sysmgt.service.IOrgService;
import com.bocft.bocpet.webapi.module.sysmgt.service.IPermService;
import com.bocft.bocpet.webapi.module.sysmgt.service.IRoleService;
import com.bocft.bocpet.webapi.module.sysmgt.util.AESUtil;
import com.bocft.bocpet.webapi.shiro.token.PhoneToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private IPermService permService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IOrgService orgService;

    @Value("${aes.secret.key}")
    private String secretKey;

    @Value("${aes.secret.iv}")
    private String secretIv;

    @PostMapping("/login")
    public Result login(@RequestBody JSONObject request) {
        Subject currentUser = SecurityUtils.getSubject();
        String username = request.getStr("authId");
        //密码解密
        String password = AESUtil.desEncrypt(request.getStr("credential"), secretKey, secretIv);
        try {
            //登录
            currentUser.login(new UsernamePasswordToken(username, password));
            //从session取出用户信息
            User user = (User) currentUser.getPrincipal();
            if (user == null) {
                throw new AuthenticationException();
            }
            Date pwdUpdated = user.getPwdUpdated();
            if (pwdUpdated == null) {
                return Result.err(ResultCodeEnum.FIRST_LOGIN).putData("uid", user.getUid());
            }
            Long pwdExpireDays = SysConfigContextHolder.getSysConfig("PASSWORD_EXPIRE_DAYS", Long.class);
            if (pwdExpireDays == null) {
                pwdExpireDays = 90L;
            }
            if (DateUtil.betweenDay(pwdUpdated, new Date(), true) > pwdExpireDays) {
                return Result.err(ResultCodeEnum.PASSWORD_EXPIRED).putData("uid", user.getUid());
            }
            List<Perm> perms = permService.getPermsByUserId(UserUtils.getCurrentUserId(), SysMgtConstants.PERM_ROOT);
            List<Role> roleNames = roleService.list(new QueryWrapper<Role>().in("rval", user.getRoles()));
            Org org = orgService.getById(user.getOrgId());
            //返回登录用户的信息给前台，含用户的所有角色和权限
            return Result.suc()
                    .putData("uid", user.getUid())
                    .putData("username", user.getUname())
                    .putData("orgId", user.getOrgId())
                    .putData("nick", user.getNick())
                    .putData("roles", user.getRoles())
                    .putData("perms", user.getPerms())
                    .putData("permList", perms)
                    .putData("type", user.getType())
                    .putData("roleNames", roleNames.stream().map(Role::getRname).collect(Collectors.toList()));
        } catch (UnknownAccountException uae) {
            log.warn("用户帐号不存在,账号：{}", username);
            return Result.err(ResultCodeEnum.INCORRECT_CREDENTIALS);
        } catch (IncorrectCredentialsException ice) {
            log.warn("用户密码不正确,账号：{}", username);
            return Result.err(ResultCodeEnum.INCORRECT_CREDENTIALS);
        } catch (LockedAccountException lae) {
            log.warn("用户帐号被锁定,账号：{}", username);
            return Result.err(ResultCodeEnum.LOCKED_ACCOUNT);
        } catch (AuthenticationException ae) {
            log.warn("登录出错,账号：{}, exception:{}", username, ae.getMessage());
            return Result.err(ResultCodeEnum.INTERNAL_ERR);
        }
    }

    @PostMapping("/loginByPhone")
    public Result loginByPhone(@RequestBody JSONObject request) {
        Subject currentUser = SecurityUtils.getSubject();
        String phone = request.getStr("authId");
        String code = request.getStr("credential");
        String userType = request.getStr("userType");
        try {
            CaptchaUtils.checkCode(SmsTypeEnum.SIGN_IN.sessionPrefix + phone, code);
        } catch (Exception e) {
            return Result.err(-1, "验证码错误");
        } finally {
            CaptchaUtils.invalidateCode(SmsTypeEnum.SIGN_IN.sessionPrefix + phone);
        }
        try {
            //登录
            currentUser.login(new PhoneToken(phone, userType));
            //从session取出用户信息
            User user = (User) currentUser.getPrincipal();
            if (user == null) {
                throw new AuthenticationException();
            }
            List<Perm> perms = permService.getPermsByUserId(UserUtils.getCurrentUserId(), SysMgtConstants.PERM_MGT_ROOT);
            List<Role> roleNames = roleService.list(new QueryWrapper<Role>().in("rval", user.getRoles()));
            //返回登录用户的信息给前台，含用户的所有角色和权限
            return Result.suc()
                    .putData("uid", user.getUid())
                    .putData("username", user.getUname())
                    .putData("orgId", user.getOrgId())
                    .putData("nick", user.getNick())
                    .putData("roles", user.getRoles())
                    .putData("perms", user.getPerms())
                    .putData("permList", perms)
                    .putData("type", user.getType())
                    .putData("roleNames", roleNames.stream().map(Role::getRname).collect(Collectors.toList()));
        } catch (UnknownAccountException uae) {
            log.warn("用户帐号不存在,手机号：{}", phone);
            return Result.err(ResultCodeEnum.UNREGISTERED_PHONE);
        } catch (LockedAccountException lae) {
            log.warn("用户帐号被锁定,手机号：{}", phone);
            return Result.err(ResultCodeEnum.LOCKED_ACCOUNT);
        } catch (AuthenticationException ae) {
            log.warn("登录出错,手机号：{}, exception:{}", phone, ae.getMessage());
            return Result.err(ResultCodeEnum.INTERNAL_ERR);
        }
    }


    @GetMapping("/logout")
    Result logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return Result.suc("登出成功");
    }

}
