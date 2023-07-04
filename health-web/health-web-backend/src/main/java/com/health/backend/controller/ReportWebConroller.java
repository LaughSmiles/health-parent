package com.health.backend.controller;


import com.health.items.feign.ReportFeign;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
@CrossOrigin
public class ReportWebConroller {

    @Autowired
    private ReportFeign reportFeign;

    @GetMapping("/web/getBusinessReportData")
    public Result getBusinessReportData() throws Exception {
        return reportFeign.getBusinessReportData();
    }
}


