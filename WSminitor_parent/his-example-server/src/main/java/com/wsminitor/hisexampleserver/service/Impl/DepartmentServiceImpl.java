package com.wsminitor.hisexampleserver.service.Impl;

import com.wsminitor.hisexampleserver.entity.Departments;
import com.wsminitor.hisexampleserver.mapper.DepartmentMapper;
import com.wsminitor.hisexampleserver.service.DepartmentService;
import com.wsminitor.hisexampleserver.service.SaasService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl extends SaasService implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;
    @Override
    public List<Departments> departmentList(Departments departments) {
        return departmentMapper.departmentList(departments);
    }

    @Override
    public int deleteDepartment(Integer departmentId) {
        return departmentMapper.deleteDepartment(departmentId);
    }

    @Override
    public int addDepartment(Departments departments) {
        return departmentMapper.addDepartment(departments);
    }


    @Override
    public int count(Departments departments) {
        return departmentMapper.count(departments);
    }

    @Override
    public int checkCount(Integer departmentId) {
        return departmentMapper.checkCount(departmentId);
    }
}
