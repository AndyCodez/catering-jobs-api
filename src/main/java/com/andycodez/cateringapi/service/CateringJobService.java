package com.andycodez.cateringapi.service;

import com.andycodez.cateringapi.data.entity.CateringJob;
import com.andycodez.cateringapi.data.repository.CateringJobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CateringJobService {

    private final CateringJobRepository cateringJobRepository;

    public CateringJobService(CateringJobRepository cateringJobRepository) {
        this.cateringJobRepository = cateringJobRepository;
    }

    public List<CateringJob> getAllCateringJobs() {
        return this.cateringJobRepository.findAll();
    }
}
