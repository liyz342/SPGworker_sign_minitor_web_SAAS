package com.wsminitor.hisexampleserver.service;

import com.wsminitor.hisexampleserver.entity.Paiban;
import com.wsminitor.hisexampleserver.entity.WorkersStatus;

import java.util.List;

public interface MainService{
    //工人状态表
    List<WorkersStatus> statusList();
    //体征检测当天人数
    int currentNum();
    //总人数
    int Total();
    //异常总人数
    int unhealthTotal();
    //异常当天人数
    int currentUnhealth();
}
