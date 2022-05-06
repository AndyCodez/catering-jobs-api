package com.andycodez.cateringapi.service;

import com.andycodez.cateringapi.data.repository.CateringJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static int count = 0;

    private final CateringJobRepository cateringJobRepository;

    public ScheduledTasks(CateringJobRepository cateringJobRepository) {
        this.cateringJobRepository = cateringJobRepository;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void reportOrderStats() {
        String noOfOrders = String.valueOf(this.cateringJobRepository.count());
        logger.info("We have : {} orders.", noOfOrders);

        // To help with testing
        countCalls();
    }

    public int countCalls() {
        return count++;
    }
}
