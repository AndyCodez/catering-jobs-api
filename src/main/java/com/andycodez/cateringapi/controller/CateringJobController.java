package com.andycodez.cateringapi.controller;

import com.andycodez.cateringapi.data.entity.CateringJob;
import com.andycodez.cateringapi.data.entity.Status;
import com.andycodez.cateringapi.service.CateringJobService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catering-jobs")
public class CateringJobController {

    private final CateringJobService cateringJobService;

    public CateringJobController(CateringJobService cateringJobService) {
        this.cateringJobService = cateringJobService;
    }

    @GetMapping
    public List<CateringJob> getCateringJobs(@RequestParam(required = false) Status status) {
        if (status == null) {
            return this.cateringJobService.getAllCateringJobs();
        }
        return this.cateringJobService.findCateringJobsByStatus(status);
    }
}
