package com.bocft.bocpet.webapi.common.pojo;

import com.bocft.bocpet.webapi.common.enums.ResultCodeEnum;

import java.io.Serializable;

public class GenericResult<T> implements Serializable {

    private static final long serialVersionUID = -1102324467836576209L;
    private String status;
    private Integer code = 0;
    private String message = "";
    private T data;

    protected GenericResult() {
    }

    private GenericResult(String status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    private GenericResult(String status, Integer code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> GenericResult<T> suc(String message) {
        message = message == null ? "" : message;
        return new GenericResult<T>("suc", ResultCodeEnum.SUCCESS.getCode(), message);
    }

    public static <T> GenericResult<T> suc(String message, T data) {
        message = message == null ? "" : message;
        return new GenericResult<T>("suc", ResultCodeEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> GenericResult<T> suc() {
        return new GenericResult<T>("suc", ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
    }

    public static <T> GenericResult<T> suc(T data) {
        return new GenericResult<T>("suc", ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
    }

    public static <T> GenericResult<T> err(Integer code, String message) {
        message = message == null ? "" : message;
        return new GenericResult<T>("err", code, message);
    }

    public static <T> GenericResult<T> err(ResultCodeEnum resultCodeEnum) {
        return err(resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }

    public static <T> GenericResult<T> err(ResultCodeEnum resultCodeEnum, T data) {
        return err(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), data);
    }

    public static <T> GenericResult<T> err(Integer code, String message, T data) {
        message = message == null ? "" : message;
        return new GenericResult<T>("err", code, message, data);
    }

    public static <T> GenericResult<T> err(ResultCodeEnum resultCodeEnum, String additionalMessage) {
        if (additionalMessage == null) {
            additionalMessage = "";
        }
        return err(resultCodeEnum.getCode(), resultCodeEnum.getMessage() + ", " + additionalMessage);
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
