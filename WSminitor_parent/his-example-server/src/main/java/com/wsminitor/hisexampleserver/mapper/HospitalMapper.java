package com.wsminitor.hisexampleserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsminitor.hisexampleserver.entity.HospitalMeta;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HospitalMapper extends BaseMapper<HospitalMeta> {
}
