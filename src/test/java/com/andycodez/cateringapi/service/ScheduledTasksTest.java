package com.andycodez.cateringapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class ScheduledTasksTest {

    @Autowired
    private ScheduledTasks scheduledTasks;

    @Test
    void whenWait30Seconds_thenScheduledreportOrderStatsIsCalledAtLeast3Times() throws InterruptedException {
        Thread.sleep(30_000);
        then(scheduledTasks.countCalls()).isEqualTo(3);
    }

}