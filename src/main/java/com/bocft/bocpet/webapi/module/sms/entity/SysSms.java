package com.bocft.bocpet.webapi.module.sms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author liuzhe
 * @since 2020-10-27
 */
public class SysSms implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 最多支持同时给20个号码发送
     */
    private String phoneNumbers;

    /**
     * 模板号
     */
    private String templateCode;

    /**
     * 参数
     */
    private String templateParam;

    /**
     * 发送状态: 1-发送成功,2-发送失败
     */
    @TableField(value = "`status`")
    private String status;

    /**
     * 发送时间
     */
    @TableField(value = "`create`")
    private LocalDateTime create;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreate() {
        return create;
    }

    public void setCreate(LocalDateTime create) {
        this.create = create;
    }

    @Override
    public String toString() {
        return "SysSms{" +
                "id=" + id +
                ", phoneNumbers=" + phoneNumbers +
                ", templateCode=" + templateCode +
                ", templateParam=" + templateParam +
                ", status=" + status +
                ", create=" + create +
                "}";
    }
}
