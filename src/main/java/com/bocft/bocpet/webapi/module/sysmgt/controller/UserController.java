package com.bocft.bocpet.webapi.module.sysmgt.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bocft.bocpet.webapi.common.annotation.OperLog;
import com.bocft.bocpet.webapi.common.enums.ResultCodeEnum;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.common.util.UserUtils;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Role;
import com.bocft.bocpet.webapi.module.sysmgt.entity.User;
import com.bocft.bocpet.webapi.module.sysmgt.entity.UserRole;
import com.bocft.bocpet.webapi.module.sysmgt.entity.UserVO;
import com.bocft.bocpet.webapi.module.sysmgt.param.*;
import com.bocft.bocpet.webapi.module.sysmgt.service.IUserService;
import com.bocft.bocpet.webapi.module.sysmgt.util.AESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    @Value("${aes.secret.key}")
    private String secretKey;

    @Value("${aes.secret.iv}")
    private String secretIv;

    @PostMapping("/create")
    @OperLog(operModul = "用户管理", operType = "新增", operDesc = "新增用户")
    Result createUser(@RequestBody @Validated(ParamValidateGroup.create.class) UserCreateParam userCreateParam) {
        if (!UserUtils.isUnameLegal(userCreateParam.getUname())) {
            return Result.err(ResultCodeEnum.ILLEGAL_UNAME);
        }
        if (userService.isUnameExisted(userCreateParam.getUname())) {
            return Result.err(ResultCodeEnum.EXISTED_UNAME);
        }
        if (userService.findUserByMobile(userCreateParam.getMobile(), userCreateParam.getType()) != null) {
            return Result.err(-1, "该手机号已经被注册");
        }
        User newUser = new User();
        BeanUtil.copyProperties(userCreateParam, newUser);
        userService.userCreate(newUser, userCreateParam.getNewRoleIds());
        return Result.suc("新增用户成功");
    }

    @GetMapping("/getList")
    Result getUserList(Integer pageNum, Integer pageSize, UserSelectParam param) {
        Page<UserVO> userVOPage = userService.selectUserVOList(param, pageNum, pageSize);
        return Result.suc().putData("list", userVOPage.getRecords())
                .putData("total", userVOPage.getTotal());
    }

    @DeleteMapping("/deleteByUid")
    @OperLog(operModul = "用户管理", operType = "删除", operDesc = "删除用户")
    Result deleteByUid(Integer uid) {
        if (uid == null) {
            return Result.err(ResultCodeEnum.DELETE_FAILED);
        }
        if (userService.deleteUserByUid(uid)) {
            return Result.suc();
        } else return Result.err(ResultCodeEnum.DELETE_FAILED);
    }

    @PostMapping("/addRoles")
    @OperLog(operModul = "用户管理", operType = "修改", operDesc = "给用户添加角色")
    Result addRolesToUser(Integer uid, Integer[] roleIds) {
        List<UserRole> userRoleList = new ArrayList<>();
        Set<Integer> roleSet = new HashSet<>(Arrays.asList(roleIds));
        roleSet.forEach(item -> userRoleList.add(new UserRole(uid, item)));
        userService.addRolesToUser(userRoleList);
        return Result.suc();
    }

    @PostMapping("/delRoles")
    @OperLog(operModul = "用户管理", operType = "修改", operDesc = "给用户删除角色")
    Result delRolesToUser(Integer uid, Integer[] roleIds) {
        Set<Integer> roleSet = new HashSet<>(Arrays.asList(roleIds));
        userService.delRolesToUser(uid, roleSet);
        return Result.suc();
    }

    @PutMapping("/updatePwd")
    Result updatePwd(@RequestBody JSONObject request) {
        Integer uid = request.getInt("uid");
        String originPwd = AESUtil.desEncrypt(request.getStr("originPwd"), secretKey, secretIv);
        String newPwd = AESUtil.desEncrypt(request.getStr("newPwd"), secretKey, secretIv);
        if (StrUtil.isEmpty(newPwd)) {
            return Result.err(-1, "新密码不能为空");
        }
        if (!uid.equals(UserUtils.getCurrentUserId())) {
            return Result.err(ResultCodeEnum.UNAUTHORIZED_OPERATION);
        } else {
            User dbUser = userService.findUserByUid(uid);
            String encryptedOriginPwd = UserUtils.encryptPwd(dbUser.getSalt(), originPwd);
            if (!encryptedOriginPwd.equals(dbUser.getPwd())) {
                return Result.err(ResultCodeEnum.INCORRECT_ORIGIN_PWD);
            }
            String encryptedNewPwd = UserUtils.encryptPwd(dbUser.getSalt(), newPwd);
            if (encryptedNewPwd.equalsIgnoreCase(encryptedOriginPwd)) {
                return Result.err(-1, "新密码不能与原密码相同");
            }
            User user = new User();
            user.setUid(uid);
            user.setPwd(UserUtils.encryptPwd(dbUser.getSalt(), newPwd));
            user.setPwdUpdated(new Date());
            userService.updateUser(user);
            return Result.suc();
        }
    }

    @GetMapping("/getRole")
    Result getUserRole(Integer pageNum, Integer pageSize, Integer uid) {
        Page<Role> rolePage = userService.getUserRole(uid, pageNum, pageSize);
        return Result.suc().putData("list", rolePage.getRecords())
                .putData("total", rolePage.getTotal());
    }

    @PostMapping("/updateUserRole")
    @OperLog(operModul = "用户管理", operType = "修改", operDesc = "修改用户角色")
    Result updateUserRole(@RequestBody JSONObject request) {
        JSONArray newUserRoles = request.getJSONArray("newUserRoles");
        JSONArray delUserRoles = request.getJSONArray("delUserRoles");
        List<UserRole> newUserRoleList = newUserRoles.toList(UserRole.class);
        List<UserRole> delUserRoleList = delUserRoles.toList(UserRole.class);
        userService.updateUserRole(newUserRoleList, delUserRoleList);
        return Result.suc();
    }

    @PutMapping("/update")
    @OperLog(operModul = "用户管理", operType = "修改", operDesc = "修改用户信息")
    Result updateUser(@RequestBody @Validated UserUpdateParam userUpdateParam) {
        System.out.println("userUpdateParam-----------" + userUpdateParam);
        User user = new User();
        BeanUtil.copyProperties(userUpdateParam, user);
        if (userService.updateById(user)) {
            return Result.suc();
        } else {
            return Result.err(-1, "更新失败");
        }
    }

    @GetMapping("/getCurrentUserInfo")
    Result getCurrentUserInfo() {
        UserSelectParam userParam = new UserSelectParam();
        userParam.setUid(UserUtils.getCurrentUserId());
        Page<UserVO> userVOPage = userService.selectUserVOList(userParam, 1, -1);
        return Result.suc().putData("list", userVOPage.getRecords())
                .putData("total", userVOPage.getTotal());
    }

    @PutMapping("/updateCurrentUserInfo")
    @OperLog(operModul = "用户管理", operType = "修改", operDesc = "修改个人信息")
    Result updateCurrentUserInfo(@RequestBody @Validated CurrentUserUpdateParam currentUserUpdateParam) {
        User user = new User();
        BeanUtil.copyProperties(currentUserUpdateParam, user);
        user.setUid(UserUtils.getCurrentUserId());
        if (userService.updateById(user)) {
            return Result.suc();
        } else {
            return Result.err(-1, "更新失败");
        }
    }
}
