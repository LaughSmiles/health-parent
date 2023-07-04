package com.health.items.feign;


import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/getSetmealReport")
@FeignClient(name="items")
public interface ReportFeign {

    @GetMapping("/getBusinessReportData")
    Result getBusinessReportData();
}
