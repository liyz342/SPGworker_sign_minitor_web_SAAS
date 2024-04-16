package com.wsminitor.hisexampleserver.mapper;

import com.wsminitor.hisexampleserver.entity.Paiban;
import com.wsminitor.hisexampleserver.entity.WorkersStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {
    List<WorkersStatus> statusList();
    int currentNum();
    int Total();
    int unhealthTotal();
    int currentUnhealth();

}
