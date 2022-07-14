package com.bocft.bocpet.webapi.module.sysmgt.param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class UserCreateParam {
    private String orgId;     //所属机构号
    @NotBlank(groups = {ParamValidateGroup.create.class, ParamValidateGroup.signup.class})
    private String uname;   // 登录名，不可改
    @NotBlank(groups = {ParamValidateGroup.create.class, ParamValidateGroup.signup.class})
    private String nick;    // 用户昵称，可改
    private String contactName;    // 企业/机构联系人姓名
    private String workNo;   //工号
    private String deptId;  //部门id
    private String position;   //职级
    @NotBlank(groups = {ParamValidateGroup.create.class, ParamValidateGroup.signup.class})
    private String pwd;     // 已加密的登录密码
    private String mobile;    // 移动电话
    private String telephone;    // 固定电话
    //    @Email(groups = {ParamValidateGroup.create.class, ParamValidateGroup.signup.class})
    private String email;    // 邮箱
    private String idType;   //证件类型
    private String idNo;    //证件号码
    private String address;   //地址
    @NotBlank(groups = {ParamValidateGroup.create.class, ParamValidateGroup.signup.class})
    private String type;
    private String code;
    @NotEmpty(groups = ParamValidateGroup.create.class)
    private int[] newRoleIds;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public int[] getNewRoleIds() {
        return newRoleIds;
    }

    public void setNewRoleIds(int[] newRoleIds) {
        this.newRoleIds = newRoleIds;
    }
}
