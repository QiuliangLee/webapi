package com.bocft.bocpet.webapi.module.petmgt.service.impl;

import com.bocft.bocpet.webapi.module.petmgt.entity.Hospital;
import com.bocft.bocpet.webapi.module.petmgt.entity.HospitalProduct;
import com.bocft.bocpet.webapi.module.petmgt.entity.Project;
import com.bocft.bocpet.webapi.module.petmgt.mapper.HospitalMapper;
import com.bocft.bocpet.webapi.module.petmgt.mapper.HospitalProductMapper;
import com.bocft.bocpet.webapi.module.petmgt.service.HospitalProductService;
import com.bocft.bocpet.webapi.module.petmgt.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-08-23 2:24
 */
@Service
public class HospitalProductServiceImpl implements HospitalProductService {
    @Autowired
    HospitalProductMapper hospitalProductMapper;
    @Override
    public List<HospitalProduct> select() {
        return hospitalProductMapper.select();
    }

    @Override
    public int insert(HospitalProduct hospitalProduct) {
        return hospitalProductMapper.insert(hospitalProduct);
    }

    @Override
    public int update(Integer id, String name) {
        return hospitalProductMapper.update(id,name);
    }

    @Override
    public int delete(Integer id) {
        return hospitalProductMapper.delete(id);
    }
}
