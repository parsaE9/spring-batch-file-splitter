package com.shaparak.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;


public class DeleteEmptyDirsTasklet implements Tasklet {

    @Value("${output.directory.path}")
    private String outputDirectoryPath;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        File[] files = new File(outputDirectoryPath).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory())
                    deleteEmptyFolder(file.getAbsolutePath());
            }
        }
        return RepeatStatus.FINISHED;
    }


    private void deleteEmptyFolder(String path) {
        File folder = new File(path);
        File[] folderContent = folder.listFiles();
        if (folderContent == null){
            return;
        } else if (folderContent.length == 0) {
            folder.delete();
        } else {
            for (File file: folderContent)
                if (file.isDirectory())
                    deleteEmptyFolder(file.getAbsolutePath());
        }
    }


}