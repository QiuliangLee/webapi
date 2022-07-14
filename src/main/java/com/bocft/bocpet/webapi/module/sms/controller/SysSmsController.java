package com.bocft.bocpet.webapi.module.sms.controller;

import cn.hutool.core.util.StrUtil;
import com.bocft.bocpet.webapi.common.constant.SysConfigContextHolder;
import com.bocft.bocpet.webapi.common.enums.ResultCodeEnum;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.module.sms.constant.SmsTypeEnum;
import com.bocft.bocpet.webapi.module.sms.service.ISysSmsService;
import com.bocft.bocpet.webapi.module.sysmgt.entity.User;
import com.bocft.bocpet.webapi.module.sysmgt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liuzhe
 * @since 2020-10-27
 */
@RestController
@RequestMapping("/sms")
public class SysSmsController implements EnvironmentAware {
    private static Environment environment;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISysSmsService sysSmsService;

    @Override
    public void setEnvironment(Environment environment) {
        SysSmsController.environment = environment;
    }

    @GetMapping("/sendSigninCode")
    Result sendSigninCode(String mobile, String type) {
        String regexMobile = SysConfigContextHolder.getSysConfig("REGEX_MOBILE_PHONE", String.class);
        if (!mobile.matches(regexMobile)) {
            return Result.err(-1, "手机号格式错误");
        }
        User dbUser = userService.findUserByMobile(mobile, type);
        if (dbUser == null) {
            return Result.err(-1, "手机号不存在");
        }
        if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            String code = sysSmsService.sendCodeSmsTest(mobile, SmsTypeEnum.SIGN_IN);
            return Result.suc().putData("code", code);
        } else {
            if (sysSmsService.sendCodeSms(mobile, SmsTypeEnum.SIGN_IN)) {
                return Result.suc();
            } else {
                return Result.err(-1, "短信发送失败");
            }
        }
    }

    @GetMapping("/sendSignupCode")
    Result sendSignupCode(String mobile, String type) {
        String regexMobile = SysConfigContextHolder.getSysConfig("REGEX_MOBILE_PHONE", String.class);
        if (!mobile.matches(regexMobile)) {
            return Result.err(-1, "手机号格式错误");
        }
        User dbUser = userService.findUserByMobile(mobile, type);
        if (dbUser != null) {
            return Result.err(-1, "该手机号已经被注册");
        }
        if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            String code = sysSmsService.sendCodeSmsTest(mobile, SmsTypeEnum.SIGN_UP);
            return Result.suc().putData("code", code);
        } else {
            if (sysSmsService.sendCodeSms(mobile, SmsTypeEnum.SIGN_UP)) {
                return Result.suc();
            } else {
                return Result.err(-1, "短信发送失败");
            }
        }
    }

    @GetMapping("/sendResetpwdCode")
    Result sendResetpwdCode(String uname) {
        User user = userService.findUserByName(uname);
        if (user == null) {
            return Result.err(ResultCodeEnum.UNEXISTED_UNAME);
        } else if (StrUtil.isBlank(user.getMobile())) {
            return Result.err(-1, "用户没有预留手机号");
        }
        if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            String code = sysSmsService.sendCodeSmsTest(user.getMobile(), SmsTypeEnum.RESET_PWD);
            return Result.suc("已向尾号为" + StrUtil.subSuf(user.getMobile(), 7) + "的手机号码发送验证码，请注意查收").putData("code", code);
        } else {
            if (sysSmsService.sendCodeSms(user.getMobile(), SmsTypeEnum.RESET_PWD)) {
                return Result.suc("已向尾号为" + StrUtil.subSuf(user.getMobile(), 7) + "的手机号码发送验证码，请注意查收");
            } else {
                return Result.err(-1, "短信发送失败");
            }
        }
    }
}
