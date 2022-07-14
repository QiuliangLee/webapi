package com.bocft.bocpet.webapi.module.sms.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.bocft.bocpet.webapi.common.constant.SysConfigContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class SmsUtils {
    private static final Logger log = LoggerFactory.getLogger(SmsUtils.class);

    @Value("${bocpet.aliyun.access.key.id}")
    private String ALIYUN_ACCESS_KEY_ID;
    @Value("${bocpet.aliyun.access.key.secret}")
    private String ALIYUN_ACCESS_KEY_SECRET;

    private final static String PRODUCT = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
    private final static String DOMAIN = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）

    private static IAcsClient I_ACS_CLIENT = null;

    @PostConstruct
    private void postConstruct() {
//        final String accessKeyId = SysConfigContextHolder.getSysConfig("ALIYUN_ACCESS_KEY_ID", String.class, true);
//        final String accessKeySecret = SysConfigContextHolder.getSysConfig("ALIYUN_ACCESS_KEY_SECRET", String.class, true);

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ALIYUN_ACCESS_KEY_ID, ALIYUN_ACCESS_KEY_SECRET);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
        } catch (ClientException ce) {
            log.error("SmsUtils初始化失败!", ce);
            throw new RuntimeException("SmsUtils初始化失败!", ce);
        }
        I_ACS_CLIENT = new DefaultAcsClient(profile);
        log.info("SmsUtils初始化成功");
    }

    public static boolean send(String targetPhone, String templateCode, Map<String, Object> param) {
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers(targetPhone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(SysConfigContextHolder.getSysConfig("ALIYUN_SMS_SIGN_NAME", String.class));
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam(JSONUtil.toJsonStr(param));
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        //request.setOutId("yourOutId");
        try {
            SendSmsResponse sendSmsResponse = I_ACS_CLIENT.getAcsResponse(request);
            if (sendSmsResponse != null && StrUtil.equals("OK", sendSmsResponse.getCode())) {
                return true;
            } else {
                log.error("发送短信失败,目标手机: {},短信模板: {},参数: {}, 返回码: {} ,返回信息: {}", targetPhone, templateCode, param, sendSmsResponse.getCode(), sendSmsResponse.getMessage());
                return false;
            }
        } catch (Exception e) {
            log.error("发送短信失败,目标手机: {},短信模板: {},参数: {}", targetPhone, templateCode, param, e);
            return false;
        }
    }
}
