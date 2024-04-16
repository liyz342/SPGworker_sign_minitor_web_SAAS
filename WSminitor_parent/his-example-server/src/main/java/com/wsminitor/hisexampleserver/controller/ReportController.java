package com.wsminitor.hisexampleserver.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsminitor.hisexampleserver.entity.Report;
import com.wsminitor.hisexampleserver.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Map;
@Controller
@RequestMapping("report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @RequestMapping("selReport")
    @ResponseBody
    public Object selReport(@RequestParam("workerId") String workerId,
                            @RequestParam("statusId") Integer statusId){
            List<Report> report = reportService.selReport(workerId, statusId);
            //System.out.println(report);
            return report;
    }

    @RequestMapping("report")
    public String showReport(@RequestParam("workerId") String workerId,
                             @RequestParam("statusId") Integer statusId,
                             Model model){

        model.addAttribute("workerId",workerId);
        model.addAttribute("statusId",statusId);
        return "report/report";
    }
}
