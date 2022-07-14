package com.bocft.bocpet.webapi.module.sysmgt.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bocft.bocpet.webapi.common.annotation.OperLog;
import com.bocft.bocpet.webapi.common.enums.ResultCodeEnum;
import com.bocft.bocpet.webapi.common.exception.BocpetBizException;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Perm;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Role;
import com.bocft.bocpet.webapi.module.sysmgt.entity.RolePerm;
import com.bocft.bocpet.webapi.module.sysmgt.entity.User;
import com.bocft.bocpet.webapi.module.sysmgt.service.IRoleService;
import com.bocft.bocpet.webapi.module.sysmgt.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    IRoleService roleService;
    @Autowired
    IUserService userService;

    @GetMapping("/getList")
    Result getRoleList(Integer pageNum, Integer pageSize, Role role) {
        Page<Role> rolePage = roleService.getRoleList(role, pageNum, pageSize);
        return Result.suc().putData("list", rolePage.getRecords())
                .putData("total", rolePage.getTotal());
    }

    @PostMapping("/create")
    @OperLog(operModul = "角色管理", operType = "新增", operDesc = "新增角色")
    Result createRole(Role role, Integer[] perms) {
        roleService.createRole(role, perms);
        return Result.suc();
    }

    @DeleteMapping("/deleteByRid")
    @OperLog(operModul = "角色管理", operType = "删除", operDesc = "删除角色")
    Result deleteRoleByRid(Integer rid) {
        boolean res;
        try {
            res = roleService.deleteRole(rid);
        } catch (BocpetBizException e) {
            return Result.err(-1, e.getMessage());
        }
        return res ? Result.suc() : Result.err(ResultCodeEnum.DELETE_FAILED);
    }

    @PostMapping("/updateRolePerm")
    @OperLog(operModul = "角色管理", operType = "修改", operDesc = "修改角色权限")
    Result updateRolePerm(Integer roleId, Integer[] newPerms, Integer[] delPerms) {
        Set<Integer> newPermSet = new HashSet<>(Arrays.asList(newPerms));
        Set<Integer> delPermSet = new HashSet<>(Arrays.asList(delPerms));
        List<RolePerm> rolePermList = new ArrayList<>();
        newPermSet.forEach(item -> rolePermList.add(new RolePerm(roleId, item)));
        roleService.updateRolePerm(roleId, rolePermList, delPermSet);
        return Result.suc();
    }

    @GetMapping("/getPerm")
    Result getRoleList(Integer pageNum, Integer pageSize, Integer rid) {
        Page<Perm> permPage = roleService.getRolePerm(rid, pageNum, pageSize);
        return Result.suc().putData("list", permPage.getRecords())
                .putData("total", permPage.getTotal());
    }

    @GetMapping("/getUser")
    Result getUserList(Integer pageNum, Integer pageSize, Integer rid) {
        Page<User> userPage = userService.getRoleUser(rid, pageNum, pageSize);
        return Result.suc().putData("list", userPage.getRecords())
                .putData("total", userPage.getTotal());
    }
}
