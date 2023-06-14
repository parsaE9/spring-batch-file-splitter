package com.shaparak.batch.service;

import com.shaparak.batch.BatchApplication;
import com.shaparak.batch.listener.ItemWriteListenerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LogService {

    @Value("${output.directory.path}")
    private String outputDirectoryPath;

    @Value("${log.directory.path}")
    private String logDirectoryPath;



    public void writeLogs() {
        try {
            Files.createDirectories(Paths.get(logDirectoryPath));
            writeBatchLog();
            writeExecLog();
            writeAchLog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void writeAchLog() throws Exception {
        String batchFilePath = BatchApplication.jobDetailsMap.get("inputTxtBatchFilePath");
        String jobStartDateTime = BatchApplication.jobDetailsMap.get("jobStartDateTime");
        String jobProcessTime = BatchApplication.jobDetailsMap.get("jobProcessTime");
        StringBuilder log = new StringBuilder();
        log.append(String.format("Processing file [%s] started at %s\n\nBanks\n" +
                        "-------------------------------------------------------------------------------------------------------------------------------\n" +
                        "Index | Record Count | File Name\n",
                batchFilePath, jobStartDateTime));

        try (Stream<Path> stream = Files.walk(Paths.get(outputDirectoryPath + "/PSPs"))) {
            log.append(iterateFiles(stream, "Ach"));
        }

//        log.append(String.format("\n+ Process Time: %s", jobProcessTime));
        log.append("\n_________________________________________________________________________________________________________________________________\n");

        File batchFile = new File(logDirectoryPath + "/ach.log");
        batchFile.createNewFile();
        FileWriter batchFileWriter = new FileWriter(logDirectoryPath + "/ach.log", true);
        batchFileWriter.append(log);
        batchFileWriter.close();
    }



    private void writeBatchLog() throws Exception {
        String batchFilePath = BatchApplication.jobDetailsMap.get("inputTxtBatchFilePath");
        String jobStartDateTime = BatchApplication.jobDetailsMap.get("jobStartDateTime");
        String jobProcessTime = BatchApplication.jobDetailsMap.get("jobProcessTime");
        StringBuilder log = new StringBuilder();
        log.append(String.format("Processing file [%s] started at %s\n\nBanks\n" +
                        "-------------------------------------------------------------------------------------------------------------------------------\n" +
                        "Index | Record Count | File Name\n",
                batchFilePath, jobStartDateTime));

        try (Stream<Path> stream = Files.walk(Paths.get(outputDirectoryPath + "/Banks"))) {
          log.append(iterateFiles(stream, "batch"));
        }
        log.append("\nPSPs\nIndex | Record Count | File Name");
        try (Stream<Path> stream = Files.walk(Paths.get(outputDirectoryPath + "/PSPs"))) {
          log.append(iterateFiles(stream, "batch"));
        }

        log.append(String.format("\n+ Total PSP Amount: %s", ItemWriteListenerImpl.totalPspAmount));
        log.append(String.format("\n+ Total Bank Amount: %s", (ItemWriteListenerImpl.totalPspAmount - ItemWriteListenerImpl.totalCommission)));
        log.append(String.format("\n+ Total Commission Amount: %s", ItemWriteListenerImpl.totalCommission));
        log.append(String.format("\n+ Process Time: %s", jobProcessTime));
        log.append("\n_________________________________________________________________________________________________________________________________\n");

        File batchFile = new File(logDirectoryPath + "/batch.log");
        batchFile.createNewFile();
        FileWriter batchFileWriter = new FileWriter(logDirectoryPath + "/batch.log", true);
        batchFileWriter.append(log);
        batchFileWriter.close();
    }



    private String iterateFiles(Stream<Path> stream, String jobType) throws IOException {
        StringBuilder log = new StringBuilder();
        int index = 1;
        for (Path path : stream.filter(Files::isRegularFile).collect(Collectors.toList())) {
            try (Stream<String> fileStream = Files.lines(Paths.get(path + ""))) {
                if ((path + "").contains(jobType)) {
                    int recordCount = (int) fileStream.count() - 1;
                    log.append("------|--------------|---------------------------------------------------------------------------------------------------------\n");
                    log.append(String.format("%6s|%14s|%30s\n", index++, recordCount, path));
                }
            }
        }
        return String.valueOf(log);
    }


    private void writeExecLog() throws IOException {
        String batchFileDate = BatchApplication.jobDetailsMap.get("batchFileDate");
        String batchFileCycle = BatchApplication.jobDetailsMap.get("batchFileCycle");
        String jobStartDate = BatchApplication.jobDetailsMap.get("jobStartDateTime");
        String jobFinishDate = BatchApplication.jobDetailsMap.get("jobFinishDateTime");
        String jobProcessTime = BatchApplication.jobDetailsMap.get("jobProcessTime");
        String amountShaparakSum = String.valueOf(ItemWriteListenerImpl.totalPspAmount);
        String acceptorCommissionSum = String.valueOf(ItemWriteListenerImpl.totalCommission);

        String log = String.format("%s|%s|%s|%s|%s|%s|%s|%s|\n", batchFileDate, batchFileCycle,amountShaparakSum, acceptorCommissionSum,
                jobStartDate, jobFinishDate, jobProcessTime, "BANK BATCH, PSP BATCH");


        File batchFile = new File(logDirectoryPath + "/exec_log.log");
        batchFile.createNewFile();
        FileWriter batchFileWriter = new FileWriter(logDirectoryPath + "/exec_log.log", true);
        batchFileWriter.append(log);
        batchFileWriter.close();
    }



}