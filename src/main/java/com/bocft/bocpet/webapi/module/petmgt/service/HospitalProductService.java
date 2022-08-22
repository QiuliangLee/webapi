package com.bocft.bocpet.webapi.module.petmgt.service;

import com.bocft.bocpet.webapi.module.petmgt.entity.Hospital;
import com.bocft.bocpet.webapi.module.petmgt.entity.HospitalProduct;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-08-22 23:10
 */
public interface HospitalProductService {
    public List<HospitalProduct> select();
    public int insert(HospitalProduct hospitalProduct);
    public int update(Integer id, String name);
    public int delete(Integer id);
}
