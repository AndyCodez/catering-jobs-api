package com.andycodez.cateringapi.controller;

import com.andycodez.cateringapi.data.entity.CateringJob;
import com.andycodez.cateringapi.data.entity.Status;
import com.andycodez.cateringapi.data.repository.CateringJobRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class CateringJobControllerIntegrationTest {

    @Autowired
    private CateringJobRepository cateringJobRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenGettingCateringJobs_ReturnsAllCateringJobs() throws Exception {
        createTwoCateringJobs();

        mockMvc.perform(MockMvcRequestBuilders.get("/catering-jobs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
        ;
    }

    @Test
    void gettingCateringJobsByStatus_ReturnsAllCateringJobsByTheSpecifiedStatus() throws Exception {
        createTwoCateringJobsWithStatusInProgress();
        createTwoCateringJobsWithStatusCancelled();

        RequestBuilder getInprogressJobs = MockMvcRequestBuilders.get("/catering-jobs?status=IN_PROGRESS");

        mockMvc.perform(getInprogressJobs)
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("[0].status").value("IN_PROGRESS"))
                .andExpect(jsonPath("[1].status").value("IN_PROGRESS"));

        RequestBuilder getCancelledJobs = MockMvcRequestBuilders.get("/catering-jobs?status=CANCELLED");

        mockMvc.perform(getCancelledJobs)
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("[0].status").value("CANCELLED"))
                .andExpect(jsonPath("[1].status").value("CANCELLED"));
    }

    void createTwoCateringJobs() {
        CateringJob cateringJob1 = new CateringJob(null, "John Doe", "0712345678",
                "johndoe@example.com", "{\"someMenu\":\"someMenuItem\"}", 20, Status.IN_PROGRESS);
        CateringJob cateringJob2 = new CateringJob(null, "John Does", "0712345679",
                "johndoe2@example.com", "{\"someMenu\":\"someMenuItem\"}", 10, Status.CANCELLED);

        List<CateringJob> cateringJobs = Arrays.asList(cateringJob1, cateringJob2);
        this.cateringJobRepository.saveAll(cateringJobs);
    }

    void createTwoCateringJobsWithStatusInProgress() {
        CateringJob cateringJob1 = new CateringJob(null, "John Doe", "0722345678",
                "johndoe@example.com", "{\"someMenu\":\"someMenuItem\"}", 20, Status.IN_PROGRESS);
        CateringJob cateringJob2 = new CateringJob(null, "John Does", "0722345679",
                "johndoe2@example.com", "{\"someMenu\":\"someMenuItem\"}", 10, Status.IN_PROGRESS);

        List<CateringJob> cateringJobs = Arrays.asList(cateringJob1, cateringJob2);
        this.cateringJobRepository.saveAll(cateringJobs);
    }

    void createTwoCateringJobsWithStatusCancelled() {
        CateringJob cateringJob1 = new CateringJob(null, "John Doe", "0711223344",
                "johndoe@example.com", "{\"someMenu\":\"someMenuItem\"}", 20, Status.CANCELLED);
        CateringJob cateringJob2 = new CateringJob(null, "John Does", "0712223344",
                "johndoe2@example.com", "{\"someMenu\":\"someMenuItem\"}", 10, Status.CANCELLED);

        List<CateringJob> cateringJobs = Arrays.asList(cateringJob1, cateringJob2);
        this.cateringJobRepository.saveAll(cateringJobs);
    }
}
