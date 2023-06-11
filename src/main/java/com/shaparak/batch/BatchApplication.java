package com.shaparak.batch;

import com.shaparak.batch.service.CsvService;
import com.shaparak.batch.service.LogService;
import com.shaparak.batch.service.TimeService;
import com.shaparak.batch.service.ZipService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Stream;

@SpringBootApplication
@Configuration
@Import(CsvService.class)
public class BatchApplication implements CommandLineRunner {

    @Autowired
    private CsvService csvService;

    @Autowired
    private LogService logService;

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    private final static List<Thread> threadList = new ArrayList<>();

    @Value("${create.zip}")
    private Boolean zipFlag;

    @Value("${output.directory.path}")
    private String outputDirectoryPath;

    public static Map<String, String> jobDetailsMap = new HashMap<>();


    public static void main(String[] args) {SpringApplication.run(BatchApplication.class, args);}


    @Override
    public void run(String... args) throws Exception {

        startBatchJob();

        logService.writeLogs();

        if (zipFlag)
            createZipFiles();


        System.exit(0);
    }


    private void startBatchJob() throws Exception {
        System.out.println("\n\nstarted batch job\n\n");
        long begin = System.currentTimeMillis();
        jobDetailsMap.put("jobStartDateTime", TimeService.formatDateTime(new Date()));

        JobParameters jobParameters = new JobParametersBuilder().addString("JobId", String.valueOf(System.currentTimeMillis())).toJobParameters();

        JobExecution execution = jobLauncher.run(job, jobParameters);

        long end = System.currentTimeMillis();
        String jobProcessDuration = TimeService.calculateDuration(end - begin);
        jobDetailsMap.put("jobProcessTime", jobProcessDuration);
        jobDetailsMap.put("jobFinishDateTime", TimeService.formatDateTime(new Date()));
        System.out.println("STATUS :: " + execution.getStatus());
        System.out.println("batch job process time: " + jobProcessDuration);
        System.out.println("finished batch job\n\n\n\n");
    }


    private void createZipFiles() throws Exception {
        System.out.println("started zipping task");
        long zipBegin = System.currentTimeMillis();
        iterateDirectoryFiles(outputDirectoryPath);
        for (Thread thread : threadList)
            thread.join();
        long zipEnd = System.currentTimeMillis();
        System.out.println("zipping task completion time: " + TimeService.calculateDuration(zipEnd - zipBegin));
        System.out.println("finished zipping task\n\n\n\n");
    }


    private void iterateDirectoryFiles(String directoryPath) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(outputDirectoryPath))) {
            stream.filter(Files::isRegularFile)
                    .forEach(path -> {
                        Thread thread = new Thread(new ZipService(path + ""));
                        threadList.add(thread);
                        thread.start();
                    });
        }
    }




}