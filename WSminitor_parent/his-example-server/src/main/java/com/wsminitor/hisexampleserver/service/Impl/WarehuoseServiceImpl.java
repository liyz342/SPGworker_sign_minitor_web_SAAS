package com.wsminitor.hisexampleserver.service.Impl;

import com.wsminitor.hisexampleserver.entity.Warehuose;
import com.wsminitor.hisexampleserver.mapper.WarehuoseMapper;
import com.wsminitor.hisexampleserver.service.SaasService;
import com.wsminitor.hisexampleserver.service.WarehuoseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class WarehuoseServiceImpl extends SaasService implements WarehuoseService {
    @Resource
    private WarehuoseMapper warehuoseMapper;
    @Override
    public List<Warehuose> findAllWarehuose(Warehuose warehuose) {
        return warehuoseMapper.findAllWarehuose(warehuose);
    }

    @Override
    public int deleteWarehuose(Integer warehouseId) {
        return warehuoseMapper.deleteWarehuose(warehouseId);
    }

    @Override
    public int addWarehuose(Warehuose warehuose) {
        return warehuoseMapper.addWarehuose(warehuose);
    }


    @Override
    public int count(Warehuose warehuose) {
        return warehuoseMapper.count(warehuose);
    }
}
