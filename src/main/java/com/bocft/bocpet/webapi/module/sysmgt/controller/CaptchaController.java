package com.bocft.bocpet.webapi.module.sysmgt.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.io.IoUtil;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.common.util.CaptchaUtils;
import com.bocft.bocpet.webapi.common.util.SnowflakeIdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {
    private static final Logger log = LoggerFactory.getLogger(CaptchaController.class);

    @GetMapping("/get")
    Result getCaptcha(HttpServletResponse response) {
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 5, 40);
        captcha.createCode();
        String CaptchaId = SnowflakeIdUtils.nextIdStr();
        CaptchaUtils.setCode("CaptchaId:" + CaptchaId, captcha.getCode(), 60000L);
        response.setHeader("CaptchaId", CaptchaId);
        try {
            IoUtil.write(response.getOutputStream(), true, captcha.getImageBytes());
        } catch (IOException e) {
            return Result.err(-1, "生成验证码失败");
        }
        return Result.suc();
    }
}
