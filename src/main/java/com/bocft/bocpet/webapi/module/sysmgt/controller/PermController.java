package com.bocft.bocpet.webapi.module.sysmgt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.common.util.PermUtils;
import com.bocft.bocpet.webapi.module.sysmgt.constant.SysMgtConstants;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Perm;
import com.bocft.bocpet.webapi.module.sysmgt.service.IPermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/perm")
public class PermController {
    @Autowired
    IPermService permService;

    @GetMapping("/getList")
    Result getPermList(Integer pageNum, Integer pageSize, Perm perm) {
        Page<Perm> permPage = permService.getPermList(perm, pageNum, pageSize);
        return Result.suc().putData("list", permPage.getRecords())
                .putData("total", permPage.getTotal());
    }

    @GetMapping("/getTree")
    Result getPermTree() {
        List<Perm> permList = permService.list(new QueryWrapper<>());
        List<Perm> permTree = PermUtils.generatePermList(SysMgtConstants.PERM_ROOT, permList);
        return Result.suc().putData("tree", permTree);
    }
}
