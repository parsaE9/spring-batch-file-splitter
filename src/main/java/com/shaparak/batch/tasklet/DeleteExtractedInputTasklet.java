package com.shaparak.batch.tasklet;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;


public class DeleteExtractedInputTasklet implements Tasklet {

    @Value("${extracted.input.file.destination.path}")
    private String extractedInputDirectoryPath;

    @Value("${delete.extracted.input}")
    private boolean deleteExtractedInput;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        if (deleteExtractedInput)
            FileUtils.deleteDirectory(new File(extractedInputDirectoryPath));
        return RepeatStatus.FINISHED;
    }


}