package com.wsminitor.hisexampleserver.service.Impl;

import com.wsminitor.hisexampleserver.entity.Register;
import com.wsminitor.hisexampleserver.mapper.LleaveMapper;
import com.wsminitor.hisexampleserver.service.LleaveService;
import com.wsminitor.hisexampleserver.service.SaasService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LleaveServiceImpl extends SaasService implements LleaveService {

    @Resource
    private LleaveMapper lleaveMapper;

    @Override
    public List<Register> selRegisters(Register register) {
        return lleaveMapper.selRegisters(register);
    }

    @Override
    public int updRstate(Register register) {
        return lleaveMapper.updRstate(register);
    }

    @Override
    public int updBstate(Register register) {
        return lleaveMapper.updBstate(register);
    }

    @Override
    public int selYaos(Register register) {
        return lleaveMapper.selYaos(register);
    }
}
