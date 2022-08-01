package com.bocft.bocpet.webapi.module.petmgt.service;

import com.bocft.bocpet.webapi.module.petmgt.entity.Insurance;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-07-28 22:09
 */
public interface InsuranceService {
    public List<Insurance> getInsuranceList();
}
