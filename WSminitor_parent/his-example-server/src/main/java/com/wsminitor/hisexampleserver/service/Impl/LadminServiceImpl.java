package com.wsminitor.hisexampleserver.service.Impl;

import com.wsminitor.hisexampleserver.entity.*;
import com.wsminitor.hisexampleserver.mapper.LadminMapper;
import com.wsminitor.hisexampleserver.service.LadminService;
import com.wsminitor.hisexampleserver.service.SaasService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LadminServiceImpl extends SaasService implements LadminService {

    @Resource
    private LadminMapper ladminMapper;

    @Override
    public List<Departments> selDepartment(Departments departments) {
        return ladminMapper.selDepartment(departments);
    }

    @Override
    public List<Doctor> selDoctor(Doctor doctor) {
        return ladminMapper.selDoctor(doctor);
    }

    @Override
    public List<Bed> selBed(Bed bed) {
        return ladminMapper.selBed(bed);
    }

    @Override
    public int addRegister(Register register) {
        return ladminMapper.addRegister(register);
    }

    @Override
    public List<Register> selRegister(Register register) {
        return ladminMapper.selRegister(register);
    }

    @Override
    public int updBed(Register register) {
        return ladminMapper.updBed(register);
    }

    @Override
    public List<Moneytype> selDis() {
        return ladminMapper.selDis();
    }

    @Override
    public List<Register> selDoor() {
        return ladminMapper.selDoor();
    }

    @Override
    public int updZ(Register register) {
        return ladminMapper.updZ(register);
    }

    @Override
    public int updKe(Register register) {
        return ladminMapper.updKe(register);
    }
}
