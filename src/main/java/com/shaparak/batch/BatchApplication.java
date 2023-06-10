package com.shaparak.batch;

import com.shaparak.batch.service.CsvService;
import com.shaparak.batch.service.ZipService;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.tomcat.util.http.fileupload.FileUtils;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@SpringBootApplication
@Configuration
@Import(CsvService.class)
public class BatchApplication implements CommandLineRunner {

    @Autowired
    CsvService csvService;

    @Autowired
    private Job job;

    @Autowired
    private JobLauncher jobLauncher;

    private final static List<Thread> threadList = new ArrayList<>();

    @Value("${create.zip}")
    private Boolean zipFlag;

    @Value("${output.directory.path}")
    private String outputDirectoryPath;



    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        startBatchJob();

//        if (zipFlag)
//            createZipFiles();



        System.exit(0);
    }


    private void startBatchJob() throws Exception {
        System.out.println("\n\nstarted batch job\n\n");
        long begin = System.currentTimeMillis();
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobId", String.valueOf(System.currentTimeMillis()))
                .addDate("jobDate", new Date())
                .addLong("jobTime", System.currentTimeMillis()).toJobParameters();

        JobExecution execution = jobLauncher.run(job, jobParameters);

        long end = System.currentTimeMillis();
        long time = TimeUnit.MILLISECONDS.toSeconds(end - begin);
        System.out.println("STATUS :: " + execution.getStatus());
        System.out.printf("batch job completed in %d seconds %n", time);
        System.out.println("finished batch job\n\n\n\n");
    }


    private void createZipFiles() throws Exception {
        System.out.println("started zipping task");
        long zipBegin = System.currentTimeMillis();
        iterateDirectoryFiles(outputDirectoryPath);
        for (Thread thread : threadList)
            thread.join();
        long zipEnd = System.currentTimeMillis();
        long zipTime = TimeUnit.MILLISECONDS.toSeconds(zipEnd - zipBegin);
        System.out.printf("zipping task completed in %d seconds %n", zipTime);
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