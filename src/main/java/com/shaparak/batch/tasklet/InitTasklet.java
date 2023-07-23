package com.shaparak.batch.tasklet;

import com.shaparak.batch.BatchApplication;
import com.shaparak.batch.service.CsvService;
import com.shaparak.batch.service.FileService;
import com.shaparak.batch.service.TimeService;
import com.shaparak.batch.service.UnzipService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Date;

public class InitTasklet implements Tasklet {

    @Autowired
    private UnzipService unzipService;

    @Value("${input.csv.file.path.switch}")
    private String switchCsvFilePath;

    @Value("${input.csv.file.path.bank}")
    private String bankCsvFilePath;

    @Value("${unzip.input.file}")
    private boolean unzipInputFile;

    @Value("${output.directory.path}")
    private String outputDirectoryPath;

    @Value("${extracted.input.file.destination.path}")
    private String extractedInputFilePath;


    @PostConstruct
    private void init() throws Exception {
        BatchApplication.jobDetailsMap.put("jobStartMillis", String.valueOf(System.currentTimeMillis()));
        BatchApplication.jobDetailsMap.put("jobStartDateTime", TimeService.formatDateTime(new Date()));

        CsvService.readCsvInputFiles(switchCsvFilePath, bankCsvFilePath);

        FileService.clearFolders(outputDirectoryPath, extractedInputFilePath, unzipInputFile);

        if (unzipInputFile)
            unzipService.unzipInput();
    }



    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        return RepeatStatus.FINISHED;
    }


}