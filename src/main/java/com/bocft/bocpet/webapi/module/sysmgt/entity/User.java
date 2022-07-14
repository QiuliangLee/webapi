package com.bocft.bocpet.webapi.module.sysmgt.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * created by liuzhe at 2018/4/17 14:55<br>
 */
public class User implements Serializable {

    @TableId
    protected Integer uid;       // 用户id
    protected String orgId;     //所属机构号
    protected String uname;   // 登录名，不可改
    protected String nick;    // 用户昵称，可改
    protected String contactName;//企业/机构联系人姓名
    protected String workNo;   //工号
    protected String deptId;  //部门id
    protected String position;   //职级
    @JsonIgnore
    protected String pwd;     // 已加密的登录密码
    @JsonIgnore
    protected String salt;    // 加密盐值
    protected String mobile;    // 移动电话
    protected String telephone;    // 固定电话
    protected String email;    // 邮箱
    protected String idType;   //证件类型
    protected String idNo;    //证件号码
    protected String address;   //地址
    protected String type;     //用户类型,1-内部用户,2-外部用户
    protected Date created;   // 创建时间
    protected Date updated;   // 修改时间
    protected Date pwdUpdated;   //最后修改密码时间
    @TableLogic(value = "0", delval = "1")
    protected String delFlag;
    @JsonIgnore
    @TableField(exist = false)
    protected Set<String> roles = new HashSet<>();    //用户所有角色值，用于shiro做角色权限的判断
    @JsonIgnore
    @TableField(exist = false)
    protected Set<String> perms = new HashSet<>();    //用户所有权限值，用于shiro做资源权限的判断

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPerms() {
        return perms;
    }

    public void setPerms(Set<String> perms) {
        this.perms = perms;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public boolean hasRole(String... roleVals) {
        return this.roles.containsAll(Arrays.asList(roleVals));
    }

    public boolean isPermitted(String... permVals) {
        return this.perms.containsAll(Arrays.asList(permVals));
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Date getPwdUpdated() {
        return pwdUpdated;
    }

    public void setPwdUpdated(Date pwdUpdated) {
        this.pwdUpdated = pwdUpdated;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", orgId='" + orgId + '\'' +
                ", uname='" + uname + '\'' +
                ", nick='" + nick + '\'' +
                ", contactName='" + contactName + '\'' +
                ", workNo='" + workNo + '\'' +
                ", deptId='" + deptId + '\'' +
                ", position='" + position + '\'' +
                ", pwd='" + pwd + '\'' +
                ", salt='" + salt + '\'' +
                ", mobile='" + mobile + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", idType='" + idType + '\'' +
                ", idNo='" + idNo + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", delFlag='" + delFlag + '\'' +
                ", roles=" + roles +
                ", perms=" + perms +
                '}';
    }
}
