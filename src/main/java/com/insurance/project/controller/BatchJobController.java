package com.insurance.project.controller;

import jakarta.annotation.security.PermitAll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class BatchJobController {
    private static final Logger logger = LogManager.getLogger(BatchJobController.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job processInsuranceJob;

    @PermitAll
    @PostMapping("/startBatchJob")
    public ResponseEntity<String> startBatchJob() {
        JobParameters jobParameters = new JobParameters();

        try {
            // Check the type of JobLauncher before using it
            if (jobLauncher instanceof TaskExecutorJobLauncher) {
                TaskExecutorJobLauncher taskExecutorJobLauncher = (TaskExecutorJobLauncher) jobLauncher;
                // Use TaskExecutorJobLauncher methods if needed
                // For example: taskExecutorJobLauncher.runTaskExecutorJob(processInsuranceJob, jobParameters);
            } else {
                // If it's not TaskExecutorJobLauncher, proceed with default run method
                jobLauncher.run(processInsuranceJob, jobParameters);
            }

            logger.info("Batch job started successfully.");
            return ResponseEntity.ok("Batch job started successfully.");
        } catch (Exception e) {
            logger.error("Error starting batch job", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error starting batch job: " + e.getMessage());
        }
    }

}
