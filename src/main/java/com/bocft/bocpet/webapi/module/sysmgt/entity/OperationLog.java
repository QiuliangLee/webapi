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
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "oper_id", type = IdType.AUTO)
    private Integer operId;

    private String operModul;

    private String operType;

    private String operDesc;

    private String operRequParam;

    private String operRespParam;

    private Integer operUserId;

    private String operUserName;

    private String operMethod;

    private String operUri;

    private String operIp;

    private LocalDateTime operCreateTime;

    public Integer getOperId() {
        return operId;
    }

    public void setOperId(Integer operId) {
        this.operId = operId;
    }

    public String getOperModul() {
        return operModul;
    }

    public void setOperModul(String operModul) {
        this.operModul = operModul;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getOperDesc() {
        return operDesc;
    }

    public void setOperDesc(String operDesc) {
        this.operDesc = operDesc;
    }

    public String getOperRequParam() {
        return operRequParam;
    }

    public void setOperRequParam(String operRequParam) {
        this.operRequParam = operRequParam;
    }

    public String getOperRespParam() {
        return operRespParam;
    }

    public void setOperRespParam(String operRespParam) {
        this.operRespParam = operRespParam;
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
        return "OperationLog{" +
                "operId=" + operId +
                ", operModul=" + operModul +
                ", operType=" + operType +
                ", operDesc=" + operDesc +
                ", operRequParam=" + operRequParam +
                ", operRespParam=" + operRespParam +
                ", operUserId=" + operUserId +
                ", operUserName=" + operUserName +
                ", operMethod=" + operMethod +
                ", operUri=" + operUri +
                ", operIp=" + operIp +
                ", operCreateTime=" + operCreateTime +
                "}";
    }
}
