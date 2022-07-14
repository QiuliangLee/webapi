package com.bocft.bocpet.webapi.module.sysmgt.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * created by liuzhe at 2018/4/17 14:55<br>
 */
public class Perm implements Serializable {
    /**
     * 权限控制可以分3类
     * 1. 菜单级别
     * 2. 页面元素级别
     * 3. 数据（接口）级别
     * 1,2两个类别前端控制
     * 3由后端控制
     * /
     * <p>
     * /**
     * 权限类型：菜单
     */
    public static int PTYPE_MENU = 1;
    /**
     * 权限类型：页面元素
     */
    public static int PTYPE_PAGE_EL = 2;
    /**
     * 权限类型：接口
     */
    public static int PTYPE_API = 3;

    @TableId
    private Integer pid;       // 权限id
    private Integer parentId;       // 父权限id
    private String pname;   // 权限名称
    private Integer ptype;  // 权限类型
    private String pval;    // 权限值，shiro的权限控制表达式
    private String url;    //前端url
    @TableField(exist = false)
    private List<Perm> children = new ArrayList<>();   //子菜单
    private Date created;   // 创建时间
    private Date updated;   // 修改时间

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getPtype() {
        return ptype;
    }

    public void setPtype(Integer ptype) {
        this.ptype = ptype;
    }

    public String getPval() {
        return pval;
    }

    public void setPval(String pval) {
        this.pval = pval;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Perm> getChildren() {
        return children;
    }

    public void setChildren(List<Perm> children) {
        this.children = children;
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
}
