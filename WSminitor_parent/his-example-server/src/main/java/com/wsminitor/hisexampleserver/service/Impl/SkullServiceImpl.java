package com.wsminitor.hisexampleserver.service.Impl;

import com.wsminitor.hisexampleserver.entity.Skull;
import com.wsminitor.hisexampleserver.mapper.SkullMapper;
import com.wsminitor.hisexampleserver.service.SaasService;
import com.wsminitor.hisexampleserver.service.SkullService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class SkullServiceImpl extends SaasService implements SkullService {
    @Resource
    private SkullMapper skullMapper;
    @Override
    public List<Skull> findAllSkull(Skull skull) {
        return skullMapper.findAllSkull(skull);
    }

    @Override
    public int deleteSkull(Integer skullId) {
        return skullMapper.deleteSkull(skullId);
    }

    @Override
    public int addSkull(Skull skull) {
        return skullMapper.addSkull(skull);
    }

    @Override
    public int editSkull(Skull skull) {
        return skullMapper.editSkull(skull);
    }
}
