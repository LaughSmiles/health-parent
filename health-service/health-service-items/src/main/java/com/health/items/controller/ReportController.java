package com.health.items.controller;


import com.health.items.service.ReportService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/report")
@CrossOrigin
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/getBusinessReportData")
    public Result getBusinessReportData() throws Exception {
        Map<String, Object> map = reportService.getBusinessReport();
        return new Result(true, StatusCode.OK, "查询成功", map);
    }

}
