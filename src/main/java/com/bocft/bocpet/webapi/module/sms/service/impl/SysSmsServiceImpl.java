package com.bocft.bocpet.webapi.module.sms.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bocft.bocpet.webapi.common.constant.SysConfigContextHolder;
import com.bocft.bocpet.webapi.common.util.CaptchaUtils;
import com.bocft.bocpet.webapi.module.sms.constant.SmsTypeEnum;
import com.bocft.bocpet.webapi.module.sms.entity.SysSms;
import com.bocft.bocpet.webapi.module.sms.mapper.SysSmsMapper;
import com.bocft.bocpet.webapi.module.sms.service.ISysSmsService;
import com.bocft.bocpet.webapi.module.sms.util.SmsUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liuzhe
 * @since 2020-10-27
 */
@Service
public class SysSmsServiceImpl extends ServiceImpl<SysSmsMapper, SysSms> implements ISysSmsService {

    @Override
    public boolean sendCodeSms(String mobile, SmsTypeEnum smsTypeEnum) {
        String templateCode = SysConfigContextHolder.getSysConfig("ALIYUN_SMS_TEMPLATE_CODE_" + smsTypeEnum.type, String.class);
        String code = RandomUtil.randomNumbers(6);
        CaptchaUtils.setCode(smsTypeEnum.sessionPrefix + mobile, code, 5 * 60 * 1000);
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("code", code);
        SysSms sms = new SysSms();
        sms.setCreate(LocalDateTime.now());
        sms.setPhoneNumbers(mobile);
        sms.setTemplateCode(templateCode);
//        sms.setTemplateParam(paraMap.toString());
        if ((SmsUtils.send(mobile, templateCode, paraMap))) {
            sms.setStatus("1");
            this.save(sms);
            return true;
        } else {
            sms.setStatus("2");
            this.save(sms);
            return false;
        }
    }

    @Override
    public String sendCodeSmsTest(String mobile, SmsTypeEnum smsTypeEnum) {
        String templateCode = SysConfigContextHolder.getSysConfig("ALIYUN_SMS_TEMPLATE_CODE_" + smsTypeEnum.type, String.class);
        String code = RandomUtil.randomNumbers(6);
        CaptchaUtils.setCode(smsTypeEnum.sessionPrefix + mobile, code, 5 * 60 * 1000);
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("code", code);
        SysSms sms = new SysSms();
        sms.setCreate(LocalDateTime.now());
        sms.setPhoneNumbers(mobile);
        sms.setTemplateCode(templateCode);
//        sms.setTemplateParam(paraMap.toString());
        sms.setStatus("1");
        this.save(sms);
        return code;
    }
}
