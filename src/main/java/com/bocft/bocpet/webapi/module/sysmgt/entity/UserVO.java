package com.bocft.bocpet.webapi.module.sysmgt.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserVO extends User {
    private String orgName;
    private String deptName;
}
