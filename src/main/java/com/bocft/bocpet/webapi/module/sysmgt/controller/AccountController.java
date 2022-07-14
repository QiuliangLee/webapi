package com.bocft.bocpet.webapi.module.sysmgt.controller;

import com.bocft.bocpet.webapi.common.annotation.OperLog;
import com.bocft.bocpet.webapi.common.enums.ResultCodeEnum;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.common.util.UserUtils;
import com.bocft.bocpet.webapi.module.sysmgt.entity.User;
import com.bocft.bocpet.webapi.module.sysmgt.param.ParamValidateGroup;
import com.bocft.bocpet.webapi.module.sysmgt.param.ResetPwdParam;
import com.bocft.bocpet.webapi.module.sysmgt.param.UserCreateParam;
import com.bocft.bocpet.webapi.module.sysmgt.service.IUserService;
import com.bocft.bocpet.webapi.module.sysmgt.util.AESUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/acct")
public class AccountController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    IUserService userService;

    @Value("${aes.secret.key}")
    private String secretKey;

    @Value("${aes.secret.iv}")
    private String secretIv;

    @PostMapping("/signup")
    @OperLog(operModul = "用户管理", operType = "新增", operDesc = "用户注册")
    Result userSignup(@RequestBody @Validated(ParamValidateGroup.signup.class) UserCreateParam userCreateParam) {
        //TODO
        return Result.suc();
    }

    @PutMapping("/resetpwd")
    @RequiresRoles("超级管理员")
    Result resetPwd(@RequestBody @Validated ResetPwdParam param) {
        User dbUser = userService.findUserByUid(param.getUid());
        String newPwd = AESUtil.desEncrypt(param.getNewPwd(), secretKey, secretIv);
        String encryptedNewPwd = UserUtils.encryptPwd(dbUser.getSalt(), newPwd);
        userService.lambdaUpdate().set(User::getPwd, encryptedNewPwd).set(User::getPwdUpdated, null).eq(User::getUid, param.getUid()).update();
        return Result.suc();
    }

    @GetMapping("/checkUname")
    Result checkUname(String uname) {
        if (!UserUtils.isUnameLegal(uname)) {
            return Result.err(ResultCodeEnum.ILLEGAL_UNAME);
        }
        if (userService.isUnameExisted(uname)) {
            return Result.err(ResultCodeEnum.EXISTED_UNAME);
        }
        return Result.suc("没有被占用");
    }
}
