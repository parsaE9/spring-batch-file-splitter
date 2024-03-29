package com.shaparak.batch.config.writer.ach;

import com.shaparak.batch.aggregator.ach.AchLineAggregator;
import com.shaparak.batch.dto.csv.SwitchDto;
import com.shaparak.batch.dto.ach.AchRecord;
import com.shaparak.batch.header.HeaderWriter;
import com.shaparak.batch.service.CsvService;
import com.shaparak.batch.service.UnzipService;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
public class AchItemWriter {

    @Value("${output.directory.path}")
    private String outputDirectoryPath;


    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> sep2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672042");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> sep1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672041");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> sep3SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672043");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> pna1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672051");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> pna2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672052");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> pec1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672061");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> pec2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672062");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> sayn1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672082");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> sayn2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672081");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> fanvSwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672091");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> kicc1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672111");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> kicc2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672112");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> mabnSwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672121");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> sada1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672131");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> sada2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672132");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> pep1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672141");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> pep2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672142");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> persSwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672011");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> ecd1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672021");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> ecd2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672022");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> bpm1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672031");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> bpm2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672032");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> sshpSwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672271");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<AchRecord> hubSwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672001");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }


    private FlatFileItemWriter<AchRecord> createWriter(String folderName, String iin) throws Exception {
        String fileName = "/PSPs/" + folderName + "/" + "Ach_Cycle_01_" + UnzipService.fileDate + "_" + iin + ".txt";
        String pspOutputPath = new File(outputDirectoryPath + fileName).getAbsolutePath();
        FlatFileItemWriter<AchRecord> writer = new FlatFileItemWriter<>();
        writer.setEncoding("UTF-8");
//        writer.setHeaderCallback(new HeaderWriter(getAchHeader()));
        writer.setLineAggregator(new AchLineAggregator());
        writer.setResource(new FileSystemResource(pspOutputPath));
        writer.setAppendAllowed(true);
        writer.afterPropertiesSet();
        return writer;
    }


    private String getAchHeader() {
        return "Acceptor_name%IBAN%Deposite_Identity%Amount%Trace_code%payment_Identity";
    }


}