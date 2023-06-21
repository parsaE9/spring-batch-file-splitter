package com.shaparak.batch.tasklet;

import com.shaparak.batch.service.FileService;
import com.shaparak.batch.service.TimeService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class RowNumberTasklet implements Tasklet {

    @Value("${output.directory.path}")
    private String outputDirectoryPath;

    @Value("${thread.count.rowNumber.task}")
    private int threadCountRowNumberTask;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        handleRowNumbers();
        return RepeatStatus.FINISHED;
    }


    private void handleRowNumbers() {
        try {
            iterateDirectoryFilesForSettingRowNumber(outputDirectoryPath);
        } catch (Exception e) {
            System.out.println("row number task exception");
            System.out.println(e.getMessage());
        }
    }


    private void iterateDirectoryFilesForSettingRowNumber(String directoryPath) throws Exception {
        try (Stream<Path> stream = Files.walk(Paths.get(directoryPath))) {
            ExecutorService executor = Executors.newFixedThreadPool(threadCountRowNumberTask);
            stream.filter(Files::isRegularFile)
                    .forEach(path -> {
                        if ((path.getFileName() + "").contains("batch")) {
                            Runnable worker = new FileService(path + "");
                            executor.execute(worker);
                        }
                    });
            executor.shutdown();
            while (!executor.isTerminated()) {}
        }
    }


}