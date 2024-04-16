package com.wsminitor.hisexampleserver.service.Impl;

import com.wsminitor.hisexampleserver.entity.Lrecord;
import com.wsminitor.hisexampleserver.entity.Pay;
import com.wsminitor.hisexampleserver.entity.Register;
import com.wsminitor.hisexampleserver.mapper.LpayMapper;
import com.wsminitor.hisexampleserver.service.LpayService;
import com.wsminitor.hisexampleserver.service.SaasService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LpayServiceImpl extends SaasService implements LpayService {

    @Resource
    private LpayMapper lpayMapper;

    @Override
    public int updPay(Register register) {
        return lpayMapper.updPay(register);
    }

    @Override
    public int addPay(Register register) {
        return lpayMapper.addPay(register);
    }

    @Override
    public List<Pay> selPays(Register register) {
        return lpayMapper.selPays(register);
    }

    @Override
    public List<Lrecord> selSurplus(Lrecord lrecord) {
        return lpayMapper.selSurplus(lrecord);
    }
}
