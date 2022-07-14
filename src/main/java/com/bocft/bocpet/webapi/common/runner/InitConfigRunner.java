package com.bocft.bocpet.webapi.common.runner;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bocft.bocpet.webapi.common.constant.SysConfigContext;
import com.bocft.bocpet.webapi.common.constant.SysDictSingleton;
import com.bocft.bocpet.webapi.module.sysmgt.entity.SysConfig;
import com.bocft.bocpet.webapi.module.sysmgt.entity.SysDict;
import com.bocft.bocpet.webapi.module.sysmgt.mapper.SysConfigMapper;
import com.bocft.bocpet.webapi.module.sysmgt.mapper.SysDictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
public class InitConfigRunner implements CommandLineRunner {

    @Autowired
    SysConfigMapper sysConfigMapper;

    @Autowired
    SysDictMapper sysDictMapper;

    @Override
    public void run(String... args) {
        List<SysConfig> sysConfigList = sysConfigMapper.selectList(Wrappers.emptyWrapper());
        sysConfigList.forEach(record -> SysConfigContext.putConstant(record.getKey(), record.getValue()));
        List<SysDict> sysDictList = sysDictMapper.selectList(Wrappers.emptyWrapper());
        sysDictList.forEach(record -> SysDictSingleton.me().put(record.getDictType() + "-" + record.getDictKey(), record.getDictValue()));
    }
}
