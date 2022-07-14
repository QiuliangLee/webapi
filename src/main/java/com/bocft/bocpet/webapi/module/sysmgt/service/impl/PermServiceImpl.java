package com.bocft.bocpet.webapi.module.sysmgt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bocft.bocpet.webapi.common.pojo.PageParam;
import com.bocft.bocpet.webapi.common.util.PermUtils;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Perm;
import com.bocft.bocpet.webapi.module.sysmgt.mapper.PermMapper;
import com.bocft.bocpet.webapi.module.sysmgt.service.IPermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * created by liuzhe at 2018/4/18 16:08<br>
 */
@Service
public class PermServiceImpl extends ServiceImpl<PermMapper, Perm> implements IPermService {

    @Autowired
    PermMapper permMapper;

    @Override
    public Set<String> getPermValuesByUserId(Integer uid) {
        Set<Integer> pids = permMapper.selectByUid(uid).stream().map(Perm::getPid).collect(Collectors.toSet());
        List<Perm> allPerms = permMapper.selectList(Wrappers.lambdaQuery());
        List<Perm> authPerms = PermUtils.findAncestorPerms(allPerms, pids);
        Set<String> perms = new HashSet<>();
        authPerms.forEach(item -> perms.add(item.getPval()));
        return perms;
    }

    @Override
    public List<Perm> getPermsByUserId(Integer uid, int sysId) {
        Set<Integer> pids = permMapper.selectByUid(uid).stream().map(Perm::getPid).collect(Collectors.toSet());
        List<Perm> allPerms = permMapper.selectList(Wrappers.lambdaQuery());
        List<Perm> authPerms = PermUtils.findAncestorPerms(allPerms, pids);
        //过滤出PTYPE_MENU类型的perm
        List<Perm> menuPerms = authPerms.stream().filter(item -> Perm.PTYPE_MENU == item.getPtype()).collect(Collectors.toList());
        return PermUtils.generatePermList(sysId, menuPerms);
    }

    @Override
    public Page<Perm> getPermList(Perm perm, Integer pageNum, Integer pageSize) {
        return permMapper.selectPage(PageParam.page(pageNum, pageSize), new QueryWrapper<>(perm));
    }
}
