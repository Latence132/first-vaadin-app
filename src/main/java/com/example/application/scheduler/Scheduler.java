package com.example.application.scheduler;
import com.example.application.batch.BatchLauncher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Scheduler {

    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private BatchLauncher batchLauncher;

    @Scheduled(fixedDelay = 8000)
    public void perform() throws Exception {
        log.info("Batch programmé pour tourner toutes les 8 secondes");
        batchLauncher.run();
    }
}