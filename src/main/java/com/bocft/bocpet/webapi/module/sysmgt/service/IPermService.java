package com.bocft.bocpet.webapi.module.sysmgt.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Perm;

import java.util.List;
import java.util.Set;

/**
 * created by liuzhe at 2018/4/18 16:08<br>
 */

public interface IPermService extends IService<Perm> {

    Set<String> getPermValuesByUserId(Integer uid);

    List<Perm> getPermsByUserId(Integer uid, int sysId);

    Page<Perm> getPermList(Perm perm, Integer pageNum, Integer pageSize);
}
