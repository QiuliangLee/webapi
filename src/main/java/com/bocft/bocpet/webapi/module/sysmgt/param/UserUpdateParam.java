package com.bocft.bocpet.webapi.module.sysmgt.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * created by liuzhe at 2018/4/17 14:55<br>
 */
@Data
public class UserUpdateParam implements Serializable {

    @NotNull
    private Integer uid;       // 用户id

    private String nick;    // 用户昵称，可改

    private String mobile;    // 移动电话

    private String telephone;    // 固定电话

    private String email;    // 邮箱
}
