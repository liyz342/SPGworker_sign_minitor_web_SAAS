package com.wsminitor.hisexampleserver.service.Impl;

import com.wsminitor.hisexampleserver.entity.Lpharmacy;
import com.wsminitor.hisexampleserver.entity.Lrecord;
import com.wsminitor.hisexampleserver.mapper.LdrugMapper;
import com.wsminitor.hisexampleserver.service.LdrugService;
import com.wsminitor.hisexampleserver.service.SaasService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LdrugServiceImpl extends SaasService implements LdrugService {

    @Resource
    private LdrugMapper ldrugMapper;

    @Override
    public List<Lpharmacy> selDrug(Lpharmacy lpharmacy) {
        return ldrugMapper.selDrug(lpharmacy);
    }

    @Override
    public List<Lrecord> selDrugs(Lrecord lrecord) {
        return ldrugMapper.selDrugs(lrecord);
    }

    @Override
    public int addDrug(Lrecord lrecord) {
        return ldrugMapper.addDrug(lrecord);
    }

    @Override
    public int updDrug(Lrecord lrecord) {
        return ldrugMapper.updDrug(lrecord);
    }

    @Override
    public int delDrug(Lrecord lrecord) {
        return ldrugMapper.delDrug(lrecord);
    }

    @Override
    public int updDrugs(Lrecord lrecord) {
        return ldrugMapper.updDrugs(lrecord);
    }

    @Override
    public int updNum(Lrecord lrecord) {
        return ldrugMapper.updNum(lrecord);
    }

    @Override
    public int upd(Lrecord lrecord) {
        return ldrugMapper.upd(lrecord);
    }
}
