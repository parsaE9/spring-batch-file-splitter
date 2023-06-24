package com.shaparak.batch.tasklet;

import com.shaparak.batch.dto.csv.BankDto;
import com.shaparak.batch.dto.csv.SwitchDto;
import com.shaparak.batch.service.CsvService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.util.Map;


public class DeleteExtraOutputTasklet implements Tasklet {

    @Value("${output.directory.path}")
    private String outputDirectoryPath;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        deleteDeactiveBatchFiles();
        deleteEmptyDirs();
        return RepeatStatus.FINISHED;
    }


    private void deleteDeactiveBatchFiles() {
        for (BankDto bankDto : CsvService.bankMap.values()) {
            if (!bankDto.isActive())
                new File(bankDto.getFilePath()).delete();
        }
        for (SwitchDto switchDto : CsvService.switchMap.values()) {
            if (!switchDto.isActive())
                new File(switchDto.getFilePath()).delete();
        }
    }


    private void deleteEmptyDirs() {
        File[] files = new File(outputDirectoryPath).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory())
                    iterateFilesForDeletingEmptyDirs(file.getAbsolutePath());
            }
        }
    }

    private void iterateFilesForDeletingEmptyDirs(String path) {
        File folder = new File(path);
        File[] folderContent = folder.listFiles();
        if (folderContent == null) {
            return;
        } else if (folderContent.length == 0) {
            folder.delete();
        } else {
            for (File file : folderContent)
                if (file.isDirectory())
                    iterateFilesForDeletingEmptyDirs(file.getAbsolutePath());
        }
    }


}