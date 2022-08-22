package com.bocft.bocpet.webapi.module.petmgt.service.impl;

import com.bocft.bocpet.webapi.module.petmgt.entity.Project;
import com.bocft.bocpet.webapi.module.petmgt.mapper.ProjectMapper;
import com.bocft.bocpet.webapi.module.petmgt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lucas
 * @create 2022-08-22 23:14
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectMapper projectMapper;
    @Override
    public List<Project> selectAllProject() {
       return projectMapper.selectAllProject();
    }

    @Override
    public int insertProject(Project project) {
        return projectMapper.insertProject(project);
    }

    @Override
    public int updateProject(Integer id, String name) {
        return projectMapper.updateProject(id,name);
    }

    @Override
    public int deleteProjectById(Integer id) {
        return deleteProjectById(id);
    }
}
