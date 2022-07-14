package com.bocft.bocpet.webapi.module.sysmgt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author liuzhe
 * @since 2020-09-28
 */
public class ExceptionLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "exc_id", type = IdType.AUTO)
    private Integer excId;

    private String excRequParam;

    private String excName;

    private String excMessage;

    private Integer operUserId;

    private String operUserName;

    private String operMethod;

    private String operUri;

    private String operIp;

    private LocalDateTime operCreateTime;

    public Integer getExcId() {
        return excId;
    }

    public void setExcId(Integer excId) {
        this.excId = excId;
    }

    public String getExcRequParam() {
        return excRequParam;
    }

    public void setExcRequParam(String excRequParam) {
        this.excRequParam = excRequParam;
    }

    public String getExcName() {
        return excName;
    }

    public void setExcName(String excName) {
        this.excName = excName;
    }

    public String getExcMessage() {
        return excMessage;
    }

    public void setExcMessage(String excMessage) {
        this.excMessage = excMessage;
    }

    public Integer getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(Integer operUserId) {
        this.operUserId = operUserId;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }

    public String getOperMethod() {
        return operMethod;
    }

    public void setOperMethod(String operMethod) {
        this.operMethod = operMethod;
    }

    public String getOperUri() {
        return operUri;
    }

    public void setOperUri(String operUri) {
        this.operUri = operUri;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public LocalDateTime getOperCreateTime() {
        return operCreateTime;
    }

    public void setOperCreateTime(LocalDateTime operCreateTime) {
        this.operCreateTime = operCreateTime;
    }

    @Override
    public String toString() {
        return "ExceptionLog{" +
                "excId=" + excId +
                ", excRequParam=" + excRequParam +
                ", excName=" + excName +
                ", excMessage=" + excMessage +
                ", operUserId=" + operUserId +
                ", operUserName=" + operUserName +
                ", operMethod=" + operMethod +
                ", operUri=" + operUri +
                ", operIp=" + operIp +
                ", operCreateTime=" + operCreateTime +
                "}";
    }
}
