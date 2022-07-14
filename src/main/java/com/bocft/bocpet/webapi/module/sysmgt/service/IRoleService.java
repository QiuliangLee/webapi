package com.bocft.bocpet.webapi.module.sysmgt.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Perm;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Role;
import com.bocft.bocpet.webapi.module.sysmgt.entity.RolePerm;

import java.util.List;
import java.util.Set;

/**
 * created by liuzhe at 2018/4/18 16:08<br>
 */
public interface IRoleService extends IService<Role> {

    Set<String> getRoleValuesByUserId(Integer uid);

    Page<Role> getRoleList(Role role, Integer pageNum, Integer pageSize);

    void createRole(Role role, Integer[] perms);

    boolean deleteRole(Integer rid);

    void updateRolePerm(Integer rid, List<RolePerm> newPerms, Set<Integer> delPerms);

    Page<Perm> getRolePerm(Integer rid, Integer pageNum, Integer pageSize);
}
