package com.wsminitor.hisexampleserver.service;

import com.wsminitor.hisexampleserver.entity.Finance;
import com.wsminitor.hisexampleserver.entity.SdoctorDuibi;
import com.wsminitor.hisexampleserver.entity.currentFinance;

import java.util.List;

public interface FinanceService{
    /*门诊月收入对比*/
    List<Double> reportYearFinance(String year);
    /*门诊年收入对比*/
    List<Finance> reportYearBingFinance();
    /*住院月收入对比*/
    List<Double> zhuYuanYearFinance(String year);
    /*住院年收入对比*/
    List<Finance> zhuYuanYearBingFinance();
    /*医生收入统计对比*/
    List<SdoctorDuibi> doctorDuibi(SdoctorDuibi sdoctorDuibi);
    /*住院部医生收入统计对比*/
    List<SdoctorDuibi> zDoctorDuibi(SdoctorDuibi sdoctorDuibi);
    /*门诊当天*/
    List<currentFinance> currentFinance(String current);
}
