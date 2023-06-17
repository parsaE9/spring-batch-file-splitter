package com.shaparak.batch;

import com.shaparak.batch.service.*;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
    private UnzipService unzipService;

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    private final static List<Thread> zipThreadList = new ArrayList<>();

    @Value("${create.zip}")
    private Boolean zipFlag;

    @Value("${thread.count.rowNumber.task}")
    private int threadCount;

    @Value("${output.directory.path}")
    private String outputDirectoryPath;

    public static Map<String, String> jobDetailsMap = new HashMap<>();


    public static void main(String[] args) {SpringApplication.run(BatchApplication.class, args);}


    @Override
    public void run(String... args) throws Exception {
        // some works are being done in csvService init method

        startBatchJob();

        handleRowNumbers();

        logService.writeLogs();

        if (zipFlag)
            createZipFiles();

        System.exit(0);
    }


//    @Bean
//    public String initBean() throws Exception {
//        unzipService.clearFolders();
//        unzipService.unzip();
//        return "initBean";
//    }


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
        iterateDirectoryFilesForZipping(outputDirectoryPath);
        for (Thread thread : zipThreadList)
            thread.join();
        long zipEnd = System.currentTimeMillis();
        System.out.println("zipping task completion time: " + TimeService.calculateDuration(zipEnd - zipBegin));
        System.out.println("finished zipping task\n\n\n\n");
    }


    private void iterateDirectoryFilesForZipping(String directoryPath) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(directoryPath))) {
            stream.filter(Files::isRegularFile)
                    .forEach(path -> {
                        Thread thread = new Thread(new ZipService(path + ""));
                        zipThreadList.add(thread);
                        thread.start();
                    });
        }
    }

    private void handleRowNumbers() {
        System.out.println("started setting rowNumbers");
        long begin = System.currentTimeMillis();
        try {
            iterateDirectoryFilesForSettingRowNumber(outputDirectoryPath);
        } catch (Exception e) {
            System.out.println("row number task exception");
            System.out.println(e.getMessage());
        }
        long end = System.currentTimeMillis();
        System.out.println("rowNumber task completion time: " + TimeService.calculateDuration(end - begin));
        System.out.println("finished setting rowNumbers\n\n");
    }


    private void iterateDirectoryFilesForSettingRowNumber(String directoryPath) throws Exception {
        try (Stream<Path> stream = Files.walk(Paths.get(directoryPath))) {

            ExecutorService executor = Executors.newFixedThreadPool(threadCount);
            stream.filter(Files::isRegularFile)
                    .forEach(path -> {
                        if ((path + "").contains("batch")) {

                            Runnable worker = new FileService(path + "");
                            executor.execute(worker);
                        }
                    });
            executor.shutdown();
            while (!executor.isTerminated()) {}

        }
    }


}