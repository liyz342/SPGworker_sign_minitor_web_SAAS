package com.wsminitor.hisexampleserver.mapper;

import com.wsminitor.hisexampleserver.entity.Report;
import com.wsminitor.hisexampleserver.entity.WorkersStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Date;
@Mapper
public interface ReportMapper {
    List<Report> selReport(String workerId, Integer statusId);
}
