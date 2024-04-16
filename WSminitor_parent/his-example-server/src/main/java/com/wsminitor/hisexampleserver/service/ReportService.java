package com.wsminitor.hisexampleserver.service;

import com.wsminitor.hisexampleserver.entity.Report;

import java.util.Date;
import java.util.List;
public interface ReportService {
    List<Report> selReport(String workerId, Integer statusId);
}
