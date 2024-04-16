package com.wsminitor.hisexampleserver.service.Impl;

import com.wsminitor.hisexampleserver.entity.Report;
import com.wsminitor.hisexampleserver.mapper.ReportMapper;
import com.wsminitor.hisexampleserver.service.ReportService;
import com.wsminitor.hisexampleserver.service.SaasService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReportServiceImpl extends SaasService implements ReportService {
    @Resource
    private ReportMapper reportMapper;

    @Override
    public List<Report> selReport(String workerId, Integer statusId) {
        return reportMapper.selReport(workerId, statusId);
    }

}
