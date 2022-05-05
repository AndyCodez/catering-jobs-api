package com.andycodez.cateringapi.data.repository;

import com.andycodez.cateringapi.data.entity.CateringJob;
import com.andycodez.cateringapi.data.entity.Status;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@DataJpaTest
@Transactional
class CateringJobRepositoryTest {

    @Autowired
    private CateringJobRepository cateringJobRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void findAll_shouldReturnAnArrayList() {
        CateringJob cateringJob1 = new CateringJob(null, "John Doe", "0712345678",
                "johndoe@example.com", "{\"someMenu\":\"someMenuItem\"}", 20, Status.IN_PROGRESS);
        CateringJob cateringJob2 = new CateringJob(null, "John Does", "0712345679",
                "johndoe2@example.com", "{\"someMenu\":\"someMenuItem\"}", 10, Status.CANCELED);

        List<CateringJob> cateringJobs = new ArrayList<>();
        cateringJobs.add(cateringJob1);
        cateringJobs.add(cateringJob2);

        this.testEntityManager.persistAndFlush(cateringJob1);
        this.testEntityManager.persistAndFlush(cateringJob2);

        BDDAssertions.then(this.cateringJobRepository.findAll().size()).isEqualTo(2);
        BDDAssertions.then(this.cateringJobRepository.findAll()).isEqualTo(cateringJobs);
    }

    @Test
    void findCateringJobByStatus_shouldReturnAllJobsWithSpecifiedStatus() {
        CateringJob inProgressCateringJob = new CateringJob(null, "John Doe", "0712345678",
                "johndoe@example.com", "{\"someMenu\":\"someMenuItem\"}", 20, Status.IN_PROGRESS);
        CateringJob cancelledCateringJob1 = new CateringJob(null, "John Does", "0712345679",
                "johndoe2@example.com", "{\"someMenu\":\"someMenuItem\"}", 10, Status.CANCELED);
        CateringJob cancelledCateringJob2 = new CateringJob(null, "John Does", "0712345679",
                "johndoe2@example.com", "{\"someMenu\":\"someMenuItem\"}", 10, Status.CANCELED);

        List<CateringJob> cateringJobs = new ArrayList<>();
        cateringJobs.add(inProgressCateringJob);
        cateringJobs.add(cancelledCateringJob1);
        cateringJobs.add(cancelledCateringJob2);

        this.cateringJobRepository.saveAll(cateringJobs);

        BDDAssertions.then(this.cateringJobRepository.findCateringJobByStatus(Status.IN_PROGRESS).size()).isEqualTo(1);
        BDDAssertions.then(this.cateringJobRepository.findCateringJobByStatus(Status.CANCELED).size()).isEqualTo(2);
    }
}