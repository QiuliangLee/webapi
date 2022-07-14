package com.bocft.bocpet.webapi.module.sysmgt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bocft.bocpet.webapi.common.pojo.PageParam;
import com.bocft.bocpet.webapi.common.util.UserUtils;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Role;
import com.bocft.bocpet.webapi.module.sysmgt.entity.User;
import com.bocft.bocpet.webapi.module.sysmgt.entity.UserRole;
import com.bocft.bocpet.webapi.module.sysmgt.entity.UserVO;
import com.bocft.bocpet.webapi.module.sysmgt.mapper.RoleMapper;
import com.bocft.bocpet.webapi.module.sysmgt.mapper.UserMapper;
import com.bocft.bocpet.webapi.module.sysmgt.mapper.UserRoleMapper;
import com.bocft.bocpet.webapi.module.sysmgt.param.UserSelectParam;
import com.bocft.bocpet.webapi.module.sysmgt.service.IUserRoleService;
import com.bocft.bocpet.webapi.module.sysmgt.service.IUserService;
import com.bocft.bocpet.webapi.module.sysmgt.util.AESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * created by liuzhe at 2018/4/18 16:08<br>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    IUserRoleService userRoleService;

    @Value("${aes.secret.key}")
    private String secretKey;

    @Value("${aes.secret.iv}")
    private String secretIv;

    @Override
    public User findUserByName(String uname) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUname, uname);
        return userMapper.selectOne(lqw);
    }

    @Override
    public User findUserByUid(Integer uid) {
        return userMapper.selectById(uid);
    }

    @Override
    public Page<User> getUserList(User user, Integer pageNum, Integer pageSize) {
        return userMapper.selectPage(PageParam.page(pageNum, pageSize), new QueryWrapper<>(user));
    }

    @Override
    @Transactional
    public boolean deleteUserByUid(Integer uid) {
        userRoleMapper.delete(new QueryWrapper<>(new UserRole(uid, null)));
        return userMapper.deleteById(uid) == 1;
    }

    @Override
    public void addRolesToUser(List<UserRole> userRoleList) {
        userRoleService.saveBatch(userRoleList);
    }

    @Override
    public void delRolesToUser(Integer uid, Set<Integer> roleIds) {
        userRoleMapper.deleteByRoleSet(uid, roleIds);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public Page<Role> getUserRole(Integer uid, Integer pageNum, Integer pageSize) {
        return roleMapper.selectByUid(PageParam.page(pageNum, pageSize), uid);
    }

    @Override
    @Transactional
    public void updateUserRole(List<UserRole> newUserRoles, List<UserRole> delUserRoles) {
        if (!CollectionUtils.isEmpty(newUserRoles)) {
            userRoleService.saveBatch(newUserRoles);
        }
        if (!CollectionUtils.isEmpty(delUserRoles)) {
            userRoleMapper.deleteAll(delUserRoles);
        }
    }

    @Override
    public Page<User> getRoleUser(Integer rid, Integer pageNum, Integer pageSize) {
        return userMapper.selectByRole(PageParam.page(pageNum, pageSize), rid);
    }


    @Override
    public Boolean isUnameExisted(String uname) {
        return this.findUserByName(uname) != null;
    }


    @Override
    public Page<UserVO> selectUserVOList(UserSelectParam user, Integer pageNum, Integer pageSize) {
        return userMapper.selectUserVO(PageParam.page(pageNum, pageSize), user);
    }

    @Override
    public User findUserByMobile(String mobile, String userType) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getMobile, mobile);
        lqw.eq(User::getType, userType);
        return userMapper.selectOne(lqw);
    }

    @Override
    @Transactional
    public void userSignup(User user) {
        //TODO
    }

    @Override
    public void resetPwd(String uname, String newPwd) {
        User dbUser = this.findUserByName(uname);
        User user = new User();
        user.setUid(dbUser.getUid());
        user.setPwd(UserUtils.encryptPwd(dbUser.getSalt(), newPwd));
        this.updateUser(user);
    }

    @Override
    @Transactional
    public void userCreate(User user, int[] newRoleIds) {
        String salt = UserUtils.generateSalt(user.getUname());
        user.setSalt(salt);
        String password = AESUtil.desEncrypt(user.getPwd(), secretKey, secretIv);
        user.setPwd(UserUtils.encryptPwd(salt, password));
        this.save(user);
        List<UserRole> newUserRoles = new ArrayList<>();
        for (int newRoleId : newRoleIds) {
            newUserRoles.add(new UserRole(user.getUid(), newRoleId));
        }
        userRoleService.saveBatch(newUserRoles);
    }
}