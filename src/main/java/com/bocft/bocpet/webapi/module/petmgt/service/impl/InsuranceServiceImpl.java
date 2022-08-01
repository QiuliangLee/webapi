package com.bocft.bocpet.webapi.module.petmgt.service.impl;

import com.bocft.bocpet.webapi.module.petmgt.entity.Insurance;
import com.bocft.bocpet.webapi.module.petmgt.mapper.InsuranceMapper;
import com.bocft.bocpet.webapi.module.petmgt.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Lucas
 * @create 2022-07-28 22:10
 */
@Service
public class InsuranceServiceImpl implements InsuranceService {
    @Autowired
    InsuranceMapper insuranceMapper;
    @Override
    public List<Insurance> getInsuranceList() {
        return insuranceMapper.selectInsuranceList();
    }
}
