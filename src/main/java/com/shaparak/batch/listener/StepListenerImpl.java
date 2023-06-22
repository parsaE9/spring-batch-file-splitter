package com.shaparak.batch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import java.io.FileWriter;
import java.io.IOException;

public class StepListenerImpl implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
//        try {
//            FileWriter batchFileWriter = new FileWriter("C:/Users/p.aliesfahani/Desktop/test" + "/log.log", true);
//            batchFileWriter.append("before step\n");
//            batchFileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("before  step");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
//        try {
//            FileWriter batchFileWriter = new FileWriter("C:/Users/p.aliesfahani/Desktop/test" + "/log.log", true);
//            batchFileWriter.append("after step\n");
//            batchFileWriter.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println("after  step");
        return ExitStatus.COMPLETED;
    }


}