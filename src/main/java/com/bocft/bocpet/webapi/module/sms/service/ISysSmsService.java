package com.bocft.bocpet.webapi.module.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bocft.bocpet.webapi.module.sms.constant.SmsTypeEnum;
import com.bocft.bocpet.webapi.module.sms.entity.SysSms;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author liuzhe
 * @since 2020-10-27
 */
public interface ISysSmsService extends IService<SysSms> {

    boolean sendCodeSms(String mobile, SmsTypeEnum smsTypeEnum);

    String sendCodeSmsTest(String mobile, SmsTypeEnum smsTypeEnum);

}
