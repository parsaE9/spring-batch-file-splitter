package com.shaparak.batch;

import com.shaparak.batch.listener.BatchItemWriterListener;
import com.shaparak.batch.service.TimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class BatchApplication implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(BatchApplication.class);

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    public static Map<String, String> jobDetailsMap = new HashMap<>();


    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        logger.warn("sample log for test");
        JobParameters jobParameters = new JobParametersBuilder().addString("JobId", String.valueOf(System.currentTimeMillis())).toJobParameters();
        JobExecution execution = jobLauncher.run(job, jobParameters);
        System.out.println("\n\nSTATUS :: " + execution.getStatus() + "\n\n");

        System.exit(0);
    }


}