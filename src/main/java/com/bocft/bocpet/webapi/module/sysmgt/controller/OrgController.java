package com.bocft.bocpet.webapi.module.sysmgt.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bocft.bocpet.webapi.common.enums.ResultCodeEnum;
import com.bocft.bocpet.webapi.common.pojo.PageParam;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.module.sysmgt.entity.Org;
import com.bocft.bocpet.webapi.module.sysmgt.mapper.OrgMapper;
import com.bocft.bocpet.webapi.module.sysmgt.param.OrgUpdateParam;
import com.bocft.bocpet.webapi.module.sysmgt.param.ParamValidateGroup;
import com.bocft.bocpet.webapi.module.sysmgt.service.IOrgService;
import com.bocft.bocpet.webapi.module.sysmgt.util.OrgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liuzhe
 * @since 2020-09-24
 */
@RestController
@RequestMapping("/org")
public class OrgController {

    @Autowired
    IOrgService orgService;

    @Autowired
    OrgMapper orgMapper;

    @GetMapping("/getTree")
    public Result getTree(@RequestParam(value = "rootId", defaultValue = "00001") String rootId, Integer[] orgTypes) {
        LambdaQueryWrapper<Org> lqw = new LambdaQueryWrapper<>();
        if (orgTypes != null && orgTypes.length > 0) {
            lqw.in(Org::getOrgType, Arrays.asList(orgTypes));
        }
        List<Org> orgList = orgService.list(lqw);
        return Result.suc().putData("tree", OrgUtils.generateOrgTree(rootId, orgList));
    }

    @GetMapping("/getList")
    public Result getList(Org org, Integer pageNum, Integer pageSize) {
        Page<Org> orgPage = orgService.page(PageParam.page(pageNum, pageSize), new QueryWrapper<>(org));
        return Result.suc().putData("total", orgPage.getTotal())
                .putData("list", orgPage.getRecords());
    }

    @PostMapping("create")
    public Result create(@RequestBody @Validated(ParamValidateGroup.create.class) Org org) {
        if (orgMapper.ifConflictOrgName(org.getOrgName())) {
            return Result.err(ResultCodeEnum.ORGNAME_DUPLICATION_EXCEPTION);
        } else {
            orgService.save(org);
            return Result.suc();
        }
    }

    @PutMapping("update")
    public Result update(@RequestBody @Validated OrgUpdateParam orgUpdateParam) {
        Org org = new Org();
        BeanUtil.copyProperties(orgUpdateParam, org);
        orgService.updateById(org);
        return Result.suc();
    }

    @DeleteMapping("delete")
    public Result delete(String orgId) {
        orgService.removeById(orgId);
        return Result.suc();
    }
}
