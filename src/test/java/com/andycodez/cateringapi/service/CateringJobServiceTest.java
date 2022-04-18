package com.andycodez.cateringapi.service;

import com.andycodez.cateringapi.data.entity.CateringJob;
import com.andycodez.cateringapi.data.entity.Status;
import com.andycodez.cateringapi.data.repository.CateringJobRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class CateringJobServiceTest {

    @Autowired
    private CateringJobService cateringJobService;

    @MockBean
    private CateringJobRepository cateringJobRepository;

    @Test
    void getAllCateringJobs_returnsAListOfCateringJobs() {
        List<CateringJob> cateringJobList = new ArrayList<>();

        CateringJob cateringJob1 = new CateringJob(1L, "John Doe", "0712345678",
                "johndoe@example.com", "{\"someMenu\":\"someMenuItem\"}", 20, Status.IN_PROGRESS);
        CateringJob cateringJob2 = new CateringJob(2L, "John Does", "0712345679",
                "johndoe2@example.com", "{\"someMenu\":\"someMenuItem\"}", 10, Status.CANCELED);

        cateringJobList.add(cateringJob1);
        cateringJobList.add(cateringJob2);

        given(this.cateringJobRepository.findAll()).willReturn(cateringJobList);

        then(this.cateringJobService.getAllCateringJobs().size()).equals(2);
        then(this.cateringJobService.getAllCateringJobs()).equals(cateringJobList);
    }
}