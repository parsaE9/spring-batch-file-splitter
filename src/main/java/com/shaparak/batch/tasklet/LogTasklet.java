package com.shaparak.batch.tasklet;

import com.shaparak.batch.BatchApplication;
import com.shaparak.batch.listener.ItemWriteListenerImpl;
import com.shaparak.batch.processor.AchRecordProcessor;
import com.shaparak.batch.service.FileService;
import com.shaparak.batch.service.TimeService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogTasklet implements Tasklet {

    @Value("${output.directory.path}")
    private String outputDirectoryPath;

    @Value("${log.directory.path}")
    private String logDirectoryPath;

    @Value("${input.zip.file.directory.path}")
    private String inputZipFileDirectoryPath;

    @Value("${create.psp.batch-file}")
    private boolean createPspBatch;

    @Value("${create.bank.batch-file}")
    private boolean createBankBatch;


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        writeLogs();
        return RepeatStatus.FINISHED;
    }


    private void writeLogs() {
        try {
            Files.createDirectories(Paths.get(logDirectoryPath));
            if (createBankBatch || createPspBatch)
                writeBatchLog();
            writeAchLog();
            writeExecLog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void writeAchLog() throws Exception {
        String inputZipFileName = BatchApplication.jobDetailsMap.get("inputZipFile");
        String jobStartDateTime = BatchApplication.jobDetailsMap.get("jobStartDateTime");

        StringBuilder log = new StringBuilder();
        log.append(String.format("Processing ACH files from the base file [%s] started at %s\n\n" +
                        "-------------------------------------------------------------------------------------------------------------------------------\n" +
                        "Index | Record Count | File Name\n",
                inputZipFileDirectoryPath + "/" + inputZipFileName, jobStartDateTime));

        try (Stream<Path> stream = Files.walk(Paths.get(outputDirectoryPath + "/PSPs"))) {
            log.append(iterateFiles(stream, "Ach"));
        }
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


        if (Files.exists(Path.of(outputDirectoryPath + "/Banks"))) {
            try (Stream<Path> stream = Files.walk(Paths.get(outputDirectoryPath + "/Banks"))) {
                log.append(iterateFiles(stream, "batch"));
            }
        }

        log.append("\nPSPs\nIndex | Record Count | File Name");

        if (Files.exists(Path.of(outputDirectoryPath + "/PSPs"))) {
            try (Stream<Path> stream = Files.walk(Paths.get(outputDirectoryPath + "/PSPs"))) {
                log.append(iterateFiles(stream, "batch"));
            }
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
        long recordCountSum = 0L;
        StringBuilder log = new StringBuilder();
        int index = 1;
        for (Path path : stream.filter(Files::isRegularFile).collect(Collectors.toList())) {
            try (Stream<String> fileStream = Files.lines(Paths.get(path + ""))) {

                String fileName = String.valueOf(path.getFileName());
                if ((fileName).contains(jobType)) {
                    int recordCount = (int) fileStream.count() - 1;
                    if (recordCount != 0) {
                        log.append("------|--------------|---------------------------------------------------------------------------------------------------------\n");
                        if (jobType.equals("Ach"))
                            log.append(String.format("%6s|%14s|%30s\n", index++, recordCount, fileName));
                        else
                            log.append(String.format("%6s|%14s|%30s\n", index++, recordCount, path));

                        recordCountSum += recordCount;
                    } else {
                        new File(path + "").delete();
                    }
                }
            }
        }

        if (jobType.equals("Ach")) {
            long totalAmount = AchRecordProcessor.totalAmount;
            String jobProcessTime = BatchApplication.jobDetailsMap.get("jobProcessTime");
            String achFilesCount = BatchApplication.jobDetailsMap.get("inputAchFilesCount");
            String jobFinishDateTime = BatchApplication.jobDetailsMap.get("jobFinishDateTime");

            log.append("------|--------------|---------------------------------------------------------------------------------------------------------\n");
            log.append(String.format("+ A total of [%s] ACH transaction summing to [%s] are categorized for [%s] switches\n", recordCountSum, totalAmount, index - 1));
            log.append(String.format("\n+ Process Time: %s", jobProcessTime));
            log.append(String.format("\n+ Processing %s ACH files is finished at %s", achFilesCount, jobFinishDateTime));
        }
        return String.valueOf(log);
    }


    private void writeExecLog() throws IOException {
        String batchFileDate = BatchApplication.jobDetailsMap.get("batchFileDate");
        String batchFileCycle = BatchApplication.jobDetailsMap.get("batchFileCycle");
        String jobStartDate = BatchApplication.jobDetailsMap.get("jobStartDateTime");
        String jobFinishDate = BatchApplication.jobDetailsMap.get("jobFinishDateTime");
        String jobProcessTime = BatchApplication.jobDetailsMap.get("jobProcessTime");
        long pspAmountSum = ItemWriteListenerImpl.totalPspAmount;
        long acceptorCommissionSum = ItemWriteListenerImpl.totalCommission;
        long bankAmountSum = pspAmountSum - acceptorCommissionSum;

        String log = String.format("%-10s|%-12s|%018d|%017d|%s000|%s000|%s00|%s|\n", batchFileDate, batchFileCycle, bankAmountSum,
                acceptorCommissionSum, jobStartDate, jobFinishDate, jobProcessTime, "BANK BATCH, PSP BATCH, PSP ACH");


        File batchFile = new File(logDirectoryPath + "/exec_log.log");
        batchFile.createNewFile();
        FileWriter batchFileWriter = new FileWriter(logDirectoryPath + "/exec_log.log", true);
        batchFileWriter.append(log);
        batchFileWriter.close();
    }






}