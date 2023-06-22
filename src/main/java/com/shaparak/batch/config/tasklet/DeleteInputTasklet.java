package com.shaparak.batch.config.tasklet;

import com.shaparak.batch.BatchApplication;
import com.shaparak.batch.service.CsvService;
import com.shaparak.batch.service.TimeService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;


public class DeleteInputTasklet implements Tasklet {

    @Value("${unzipped.input.file.destination.path}")
    private String extractedInputDirectoryPath;

    @Value("${delete.extracted.input}")
    private boolean deleteExtractedInput;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        long begin = Long.parseLong(BatchApplication.jobDetailsMap.get("jobStartMillis"));
        long end = System.currentTimeMillis();
        String jobProcessDuration = TimeService.calculateDuration(end - begin);
        BatchApplication.jobDetailsMap.put("jobProcessTime", jobProcessDuration);
        BatchApplication.jobDetailsMap.put("jobFinishDateTime", TimeService.formatDateTime(new Date()));
        if (deleteExtractedInput)
            FileUtils.deleteDirectory(new File(extractedInputDirectoryPath));
        return RepeatStatus.FINISHED;
    }


}