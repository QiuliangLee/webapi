package com.bocft.bocpet.webapi.module.sysmgt.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bocft.bocpet.webapi.common.exception.BocpetBizException;
import com.bocft.bocpet.webapi.common.pojo.PageParam;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Perm;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Role;
import com.bocft.bocpet.webapi.module.sysmgt.entity.RolePerm;
import com.bocft.bocpet.webapi.module.sysmgt.entity.UserRole;
import com.bocft.bocpet.webapi.module.sysmgt.mapper.PermMapper;
import com.bocft.bocpet.webapi.module.sysmgt.mapper.RoleMapper;
import com.bocft.bocpet.webapi.module.sysmgt.mapper.RolePermMapper;
import com.bocft.bocpet.webapi.module.sysmgt.mapper.UserRoleMapper;
import com.bocft.bocpet.webapi.module.sysmgt.service.IRolePermService;
import com.bocft.bocpet.webapi.module.sysmgt.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * created by liuzhe at 2018/4/18 16:08<br>
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermMapper permMapper;

    @Autowired
    RolePermMapper rolePermMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    IRolePermService rolePermService;

    public Set<String> getRoleValuesByUserId(Integer uid) {
        List<Role> roleList = roleMapper.selectByUid(uid);
        Set<String> roles = new HashSet<>();
        roleList.forEach(item -> roles.add(item.getRval()));
        return roles;
    }

    @Override
    public Page<Role> getRoleList(Role role, Integer pageNum, Integer pageSize) {
        return roleMapper.selectPage(PageParam.page(pageNum, pageSize), new QueryWrapper<>(role));
    }

    @Override
    @Transactional
    public void createRole(Role role, Integer[] perms) {
        try {
            roleMapper.insert(role);
        } catch (DuplicateKeyException dke) {
            throw new BocpetBizException("角色已经存在");
        }
        if (ArrayUtil.isNotEmpty(perms)) {
            Set<Integer> permSet = new HashSet<>(Arrays.asList(perms));
            List<RolePerm> rolePermList = new ArrayList<>();
            permSet.forEach(item -> rolePermList.add(new RolePerm(role.getRid(), item)));
            rolePermService.saveBatch(rolePermList);
        }
    }

    @Override
    @Transactional
    public boolean deleteRole(Integer rid) {
        Role role = roleMapper.selectById(rid);
        if (role != null && 1 != role.getDeletable()) {
            throw new BocpetBizException("内置角色,不可删除");
        }
        int count = userRoleMapper.selectCount(new LambdaQueryWrapper<UserRole>().eq(UserRole::getRoleId, rid));
        if (count > 0) {
            throw new BocpetBizException("该角色下尚有用户,不可删除");
        }
        rolePermMapper.delete(new QueryWrapper<>(new RolePerm(rid, null)));
        return roleMapper.deleteById(rid) == 1;
    }

    @Override
    @Transactional
    public void updateRolePerm(Integer rid, List<RolePerm> newPerms, Set<Integer> delPerms) {
        if (!CollectionUtils.isEmpty(newPerms)) {
            rolePermService.saveBatch(newPerms);
        }
        if (!CollectionUtils.isEmpty(delPerms)) {
            rolePermMapper.deleteByPermSet(rid, delPerms);
        }
    }

    @Override
    public Page<Perm> getRolePerm(Integer rid, Integer pageNum, Integer pageSize) {
        return permMapper.selectByRid(PageParam.page(pageNum, pageSize), rid);
    }
}
