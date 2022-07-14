package com.bocft.bocpet.webapi.common.enums;

public enum ResultCodeEnum {

    SUCCESS(0, "成功"),
    INTERNAL_ERR(1, "内部异常"),
    UNAUTHENTICATED(2, "用户未登录"),
    UNAUTHORIZED(3, "用户没有访问权限"),
    INCORRECT_CREDENTIALS(4, "用户名或密码不正确"),
    LOCKED_ACCOUNT(5, "用户账号被锁定"),
    DELETE_FAILED(6, "删除失败"),
    UNAUTHORIZED_OPERATION(7, "没有操作权限"),
    INCORRECT_ORIGIN_PWD(8, "原密码错误"),
    CREATE_FAILED(9, "新增失败"),
    ILLEGAL_UNAME(10, "用户名格式不合法"),
    UNEXISTED_UNAME(11, "用户名不存在"),
    EXISTED_UNAME(12, "用户名已经被使用"),
    EMPTY_EMAIL_ADDR(13, "用户没有预留邮箱"),
    USER_TYPE_ERR(14, "用户类型错误"),
    UNREGISTERED_PHONE(15, "手机号未注册"),
    PARAM_TYPE_ERR(16, "参数类型错误"),
    PARAM_VALIDATE_FAILED(17, "参数校验失败"),
    NO_RECORD(18, "无记录"),
    MODIFY_FAILED(20, "修改失败"),
    PASSWORD_EXPIRED(21, "密码已过期,请修改密码"),
    FIRST_LOGIN(22, "首次登录,请修改密码"),
    DATA_DUPLICATION_EXCEPTION(23, "数据唯一性冲突"),
    ORGNAME_DUPLICATION_EXCEPTION(24, "机构已存在");

    private final int code;
    private final String message;

    ResultCodeEnum(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

}
