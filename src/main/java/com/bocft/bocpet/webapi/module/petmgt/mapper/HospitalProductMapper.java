package com.bocft.bocpet.webapi.module.petmgt.mapper;

import com.bocft.bocpet.webapi.module.petmgt.entity.Hospital;
import com.bocft.bocpet.webapi.module.petmgt.entity.HospitalProduct;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-08-23 2:55
 */
@Repository
@Mapper
public interface HospitalProductMapper {
    public int insert(HospitalProduct hospitalProduct);

    public int delete(int id);

    public int update(Integer id, String name);

    public List<HospitalProduct> select();
}
