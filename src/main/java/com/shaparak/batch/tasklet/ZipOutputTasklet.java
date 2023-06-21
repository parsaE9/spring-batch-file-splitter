package com.shaparak.batch.tasklet;

import com.shaparak.batch.service.TimeService;
import com.shaparak.batch.service.ZipService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class ZipOutputTasklet implements Tasklet {

    @Value("${output.directory.path}")
    private String outputDirectoryPath;

    private final static List<Thread> zipThreadList = new ArrayList<>();


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        createZipFiles();
        return RepeatStatus.FINISHED;
    }


    private void createZipFiles() throws Exception {
        iterateDirectoryFilesForZipping(outputDirectoryPath);
        for (Thread thread : zipThreadList)
            thread.join();
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


}