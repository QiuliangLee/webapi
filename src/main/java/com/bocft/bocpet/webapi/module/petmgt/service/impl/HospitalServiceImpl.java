package com.bocft.bocpet.webapi.module.petmgt.service.impl;

import com.bocft.bocpet.webapi.module.petmgt.entity.Hospital;
import com.bocft.bocpet.webapi.module.petmgt.entity.Project;
import com.bocft.bocpet.webapi.module.petmgt.mapper.HospitalMapper;
import com.bocft.bocpet.webapi.module.petmgt.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-08-23 2:24
 */
@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    HospitalMapper hospitalMapper;
    @Override
    public List<Hospital> select() {
        return hospitalMapper.select();
    }

    @Override
    public int insert(Hospital hospital) {
        return hospitalMapper.insert(hospital);
    }

    @Override
    public int update(Integer id, String name) {
        return hospitalMapper.update(id,name);
    }

    @Override
    public int delete(Integer id) {
        return hospitalMapper.delete(id);
    }
}
