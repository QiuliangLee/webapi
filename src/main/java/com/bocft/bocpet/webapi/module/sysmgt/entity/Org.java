package com.bocft.bocpet.webapi.module.sysmgt.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.bocft.bocpet.webapi.module.sysmgt.param.ParamValidateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author liuzhe
 * @since 2020-10-13
 */
@Data
public class Org implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT)
    @NotBlank(groups = {ParamValidateGroup.create.class, ParamValidateGroup.update.class})
    private String orgId;

    @NotBlank(groups = {ParamValidateGroup.create.class})
    @TableField(condition = SqlCondition.LIKE)
    private String orgName;

    /**
     * 机构类型,1-监管,2-机构,3-个人,4-协会
     */
    @NotNull(groups = {ParamValidateGroup.create.class})
    private Integer orgType;

    /**
     * 父机构id
     */
    @NotBlank(groups = {ParamValidateGroup.create.class})
    private String parentId;

    @TableField(exist = false)
    private String parentName;

    private String regionCode;

    private String orgAddr;

    private String orgContacts;

    private String orgContactDetail;

    private String orgSecretKey;

    private Long picid;

    /**
     * 银行类型,1-大中型银行;2-中小银行
     */
    private String bankFlag;

    @TableLogic(value = "0", delval = "1")
    private String delFlag;

    @TableField(exist = false)
    private List<Org> children = new ArrayList<>();   //子菜单

}
