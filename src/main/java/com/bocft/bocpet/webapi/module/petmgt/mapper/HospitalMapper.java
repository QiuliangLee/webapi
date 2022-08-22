package com.bocft.bocpet.webapi.module.petmgt.mapper;

import com.bocft.bocpet.webapi.module.petmgt.entity.Hospital;
import com.bocft.bocpet.webapi.module.petmgt.entity.Pet;
import com.bocft.bocpet.webapi.module.petmgt.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-07-24 22:03
 */
@Repository
@Mapper
public interface HospitalMapper {
    public int insert(Hospital hospital);

    public int delete(int id);

    public int update(Integer id, String name);

    public List<Hospital> select();
}
