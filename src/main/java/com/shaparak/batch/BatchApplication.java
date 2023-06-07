package com.shaparak.batch;

import com.shaparak.batch.service.CsvService;
import com.shaparak.batch.service.ZipService;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Value("${input.batch.file.zip.path}")
    private String inputZipFilePath;

    @Value("${unzipped.input.file.destination.path}")
    private String unzippedInputFileDestination;



    public static void main(String[] args) {
        SpringApplication.run(BatchApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

//        unzip();

        deleteOutputFolder();

        startBatchJob();

        if (zipFlag)
            createZipFiles();

        System.exit(0);
    }


    private void startBatchJob() throws Exception {
        long begin = System.currentTimeMillis();
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobId", String.valueOf(System.currentTimeMillis()))
                .addDate("jobDate", new Date())
                .addLong("jobTime", System.currentTimeMillis()).toJobParameters();

        JobExecution execution = jobLauncher.run(job, jobParameters);

        long end = System.currentTimeMillis();
        long time = TimeUnit.MILLISECONDS.toSeconds(end - begin);
        System.out.println("STATUS :: " + execution.getStatus());
        System.out.printf("writing task completed in %d seconds %n", time);
    }


    private void createZipFiles() throws Exception {
        long zipBegin = System.currentTimeMillis();
        iterateDirectoryFiles(outputDirectoryPath);
        for (Thread thread : threadList)
            thread.join();
        long zipEnd = System.currentTimeMillis();
        long zipTime = TimeUnit.MILLISECONDS.toSeconds(zipEnd - zipBegin);
        System.out.printf("zipping task completed in %d seconds %n", zipTime);
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


    private void deleteOutputFolder() throws IOException {
        FileUtils.deleteDirectory(new File(outputDirectoryPath));
    }


    private void unzip() throws IOException {
        String fileZip = inputZipFilePath;
        File destDir = new File(unzippedInputFileDestination);

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                // fix for Windows-created archives
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    throw new IOException("Failed to create directory " + parent);
                }

                // write file content
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }


    public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }

        return destFile;
    }


}