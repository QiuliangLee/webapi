package com.bocft.bocpet.webapi.module.sysmgt.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Role;
import com.bocft.bocpet.webapi.module.sysmgt.entity.User;
import com.bocft.bocpet.webapi.module.sysmgt.entity.UserRole;
import com.bocft.bocpet.webapi.module.sysmgt.entity.UserVO;
import com.bocft.bocpet.webapi.module.sysmgt.param.UserSelectParam;

import java.util.List;
import java.util.Set;

/**
 * created by liuzhe at 2018/4/18 16:08<br>
 */
public interface IUserService extends IService<User> {

    User findUserByName(String uname);

    User findUserByUid(Integer uid);

    User findUserByMobile(String mobile, String userType);

    Page<User> getUserList(User user, Integer pageNum, Integer pageSize);

    boolean deleteUserByUid(Integer uid);

    void addRolesToUser(List<UserRole> userRoleList);

    void delRolesToUser(Integer uid, Set<Integer> roleIds);

    void updateUser(User user);

    Page<Role> getUserRole(Integer uid, Integer pageNum, Integer pageSize);

    void updateUserRole(List<UserRole> newUserRoles, List<UserRole> delUserRoles);

    Page<User> getRoleUser(Integer rid, Integer pageNum, Integer pageSize);

    Boolean isUnameExisted(String uname);

    void resetPwd(String uname, String newPwd);

    Page<UserVO> selectUserVOList(UserSelectParam user, Integer pageNum, Integer pageSize);

    void userSignup(User user);

    void userCreate(User user, int[] newRoleIds);
}
