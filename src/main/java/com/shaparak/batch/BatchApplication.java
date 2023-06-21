package com.shaparak.batch;

import com.shaparak.batch.service.CsvService;
import com.shaparak.batch.service.TimeService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Configuration
@Import(CsvService.class)
public class BatchApplication implements CommandLineRunner {

    @Autowired
    private CsvService csvService;

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
        // some works are being done in csvService init method
        jobDetailsMap.put("jobStartMillis", String.valueOf(System.currentTimeMillis()));
        jobDetailsMap.put("jobStartDateTime", TimeService.formatDateTime(new Date()));
        JobParameters jobParameters = new JobParametersBuilder().addString("JobId", String.valueOf(System.currentTimeMillis())).toJobParameters();
        JobExecution execution = jobLauncher.run(job, jobParameters);
        System.out.println("STATUS :: " + execution.getStatus());

        System.exit(0);
    }


}