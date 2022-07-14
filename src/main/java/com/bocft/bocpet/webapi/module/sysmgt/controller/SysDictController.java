package com.bocft.bocpet.webapi.module.sysmgt.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bocft.bocpet.webapi.common.pojo.Result;
import com.bocft.bocpet.webapi.module.sysmgt.entity.SysDict;
import com.bocft.bocpet.webapi.module.sysmgt.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 8902882
 * @since 2021-10-26
 */
@RestController
@RequestMapping("/sysmgt/sys-dict")
public class SysDictController {

    @Autowired
    ISysDictService sysDictService;

    @GetMapping("/page")
    public Result page(SysDict sysDict, Integer pageNo, Integer pageSize) {
        Page<SysDict> page = new Page<>(pageNo, pageSize);
        page = sysDictService.page(page, new LambdaQueryWrapper<>(sysDict).orderByAsc(SysDict::getId));
        return Result.suc()
                .putData("total", page.getTotal())
                .putData("list", page.getRecords());
    }

    @PostMapping("/create")
    public Result create(@RequestBody SysDict sysDict) {
        boolean res = sysDictService.save(sysDict);
        return res ? Result.suc() : Result.err(-1, "添加失败！");
    }

    @DeleteMapping("/delete")
    public Result delete(Integer id) {
        boolean res = sysDictService.removeById(id);
        return res ? Result.suc() : Result.err(-1, "删除失败！");
    }

    @PutMapping("/update")
    public Result update(@RequestBody SysDict sysDict) {
        boolean res = sysDictService.updateById(sysDict);
        return res ? Result.suc() : Result.err(-1, "修改失败！");
    }
}
