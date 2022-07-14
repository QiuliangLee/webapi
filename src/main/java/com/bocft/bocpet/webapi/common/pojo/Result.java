package com.bocft.bocpet.webapi.common.pojo;

import com.bocft.bocpet.webapi.common.enums.ResultCodeEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result implements Serializable {

    private static final long serialVersionUID = -1802122468331526708L;
    private String status;
    private Integer code = 0;
    private String message = "";
    private Map<String, Object> data = new HashMap<>();

    private Result() {
    }

    private Result(String status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    private Result(String status, Integer code, String message, Map<String, Object> data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result suc() {
        return new Result("suc", 0, "");
    }

    public static Result suc(String message) {
        message = message == null ? "" : message;
        return new Result("suc", 0, message);
    }

    public static Result suc(String message, Map<String, Object> data) {
        message = message == null ? "" : message;
        return new Result("suc", 0, message, data);
    }

    public static Result err(Integer code, String message) {
        message = message == null ? "" : message;
        return new Result("err", code, message);
    }

    public static Result err(ResultCodeEnum resultCodeEnum) {
        return err(resultCodeEnum.getCode(), resultCodeEnum.getMessage());
    }

    public static Result err(ResultCodeEnum resultCodeEnum, Map<String, Object> data) {
        return err(resultCodeEnum.getCode(), resultCodeEnum.getMessage(), data);
    }

    public static Result err(Integer code, String message, Map<String, Object> data) {
        message = message == null ? "" : message;
        return new Result("err", code, message, data);
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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void removeData(String key) {
        data.remove(key);
    }

    public Result putData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status='" + status + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}