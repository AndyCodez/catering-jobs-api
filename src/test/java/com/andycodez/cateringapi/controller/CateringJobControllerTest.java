package com.andycodez.cateringapi.controller;

import com.andycodez.cateringapi.data.entity.CateringJob;
import com.andycodez.cateringapi.data.entity.Status;
import com.andycodez.cateringapi.service.CateringJobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CateringJobControllerTest {

    @MockBean
    private CateringJobService cateringJobService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void canGetAllCateringJobs() throws Exception {
        List<CateringJob> cateringJobList = new ArrayList<>();

        CateringJob cateringJob1 = new CateringJob(1L, "John Doe", "0712345678",
                "johndoe@example.com", "{\"someMenu\":\"someMenuItem\"}", 20, Status.IN_PROGRESS);
        CateringJob cateringJob2 = new CateringJob(2L, "John Does", "0712345679",
                "johndoe2@example.com", "{\"someMenu\":\"someMenuItem\"}", 10, Status.CANCELLED);

        cateringJobList.add(cateringJob1);
        cateringJobList.add(cateringJob2);

        given(this.cateringJobService.getAllCateringJobs()).willReturn(cateringJobList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/catering-jobs");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("[0].id").value(1))
                .andExpect(jsonPath("[0].customerName").value("John Doe"))
                .andExpect(jsonPath("[0].phoneNumber").value("0712345678"))
                .andExpect(jsonPath("[0].email").value("johndoe@example.com"))
                .andExpect(jsonPath("[0].menu").value("{\"someMenu\":\"someMenuItem\"}"))
                .andExpect(jsonPath("[0].noOfGuests").value(20))
                .andExpect(jsonPath("[0].status").value("IN_PROGRESS"))
        ;
    }

    @Test
    void canGetJobsByStatus() throws Exception {
        List<CateringJob> cancelledCateringJobs = new ArrayList<>();
        CateringJob cancelledCateringJob1 = new CateringJob(null, "John Does", "0712345679",
                "johndoe2@example.com", "{\"someMenu\":\"someMenuItem\"}", 10, Status.CANCELLED);
        CateringJob cancelledCateringJob2 = new CateringJob(null, "John Does", "0712345679",
                "johndoe2@example.com", "{\"someMenu\":\"someMenuItem\"}", 10, Status.CANCELLED);

        cancelledCateringJobs.add(cancelledCateringJob1);
        cancelledCateringJobs.add(cancelledCateringJob2);

        given(this.cateringJobService.findCateringJobsByStatus(Status.CANCELLED)).willReturn(cancelledCateringJobs);

        RequestBuilder request = MockMvcRequestBuilders.get("/catering-jobs?status=CANCELLED");

        mockMvc.perform(request)
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("[0].status").value("CANCELLED"))
                .andExpect(jsonPath("[1].status").value("CANCELLED"));
    }
}