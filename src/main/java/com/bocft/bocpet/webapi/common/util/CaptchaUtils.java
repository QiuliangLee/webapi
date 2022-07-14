package com.bocft.bocpet.webapi.common.util;

import cn.hutool.core.util.StrUtil;
import com.bocft.bocpet.webapi.common.exception.BocpetBizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class CaptchaUtils {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static RedisTemplate<String, String> staticRisTemplate;

    @PostConstruct
    private void postConstruct() {
        staticRisTemplate = this.redisTemplate;
    }

    public static void checkCode(String captchaId, String code) {
        String correctCode = staticRisTemplate.opsForValue().get(captchaId);
        if (correctCode == null || !StrUtil.equalsIgnoreCase(code, correctCode)) {
            throw new BocpetBizException("验证码错误");
        }
    }

    public static void invalidateCode(String captchaId) {
        staticRisTemplate.delete(captchaId);
    }

    public static void setCode(String id, String code, long expireDuration) {
        staticRisTemplate.opsForValue().set(id, code, expireDuration, TimeUnit.MILLISECONDS);

    }
}
