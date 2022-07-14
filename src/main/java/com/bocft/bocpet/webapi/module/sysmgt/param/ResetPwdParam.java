package com.bocft.bocpet.webapi.module.sysmgt.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ResetPwdParam {
    @NotNull
    private Integer uid;
    @NotBlank
    private String newPwd;
}
