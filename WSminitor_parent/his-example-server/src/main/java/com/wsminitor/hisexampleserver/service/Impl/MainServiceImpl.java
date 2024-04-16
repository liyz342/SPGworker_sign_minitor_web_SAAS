package com.wsminitor.hisexampleserver.service.Impl;

import com.wsminitor.hisexampleserver.entity.Paiban;
import com.wsminitor.hisexampleserver.entity.WorkersStatus;
import com.wsminitor.hisexampleserver.mapper.MainMapper;
import com.wsminitor.hisexampleserver.service.MainService;
import com.wsminitor.hisexampleserver.service.SaasService;
import com.wsminitor.hisexampleserver.service.WarehuoseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class MainServiceImpl extends SaasService implements MainService {
    @Resource
    private MainMapper mainMapper;
    @Override
    public List<WorkersStatus> statusList(){return mainMapper.statusList();}

    @Override
    public int currentNum() {
        return mainMapper.currentNum();
    }

    @Override
    public int Total() {
        return mainMapper.Total();
    }

    @Override
    public int unhealthTotal() {
        return mainMapper.unhealthTotal();
    }

    @Override
    public int currentUnhealth() {
        return mainMapper.currentUnhealth();
    }


}
