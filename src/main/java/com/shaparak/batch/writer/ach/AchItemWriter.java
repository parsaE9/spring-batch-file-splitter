package com.shaparak.batch.writer.ach;

import com.shaparak.batch.aggregator.ach.AchLineAggregator;
import com.shaparak.batch.aggregator.batch.PspLineAggregator;
import com.shaparak.batch.dto.csv.SwitchDto;
import com.shaparak.batch.dto.xml.CdtTrfTxInfDto;
import com.shaparak.batch.header.HeaderWriter;
import com.shaparak.batch.service.CsvService;
import com.shaparak.batch.service.UnzipService;
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
    public FlatFileItemWriter<CdtTrfTxInfDto> sep2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672042");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> sep1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672041");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> sep3SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672043");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> pna1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672051");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> pna2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672052");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> pec1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672061");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> pec2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672062");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> sayn1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672082");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> sayn2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672081");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> fanvSwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672091");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> kicc1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672111");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> kicc2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672112");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> mabnSwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672121");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> sada1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672131");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> sada2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672132");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> pep1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672141");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> pep2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672142");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> persSwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672011");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> ecd1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672021");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> ecd2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672022");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> bpm1SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672031");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> bpm2SwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672032");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> sshpSwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672271");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<CdtTrfTxInfDto> hubSwitchAchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672001");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }


    private FlatFileItemWriter<CdtTrfTxInfDto> createWriter(String folderName, String iin) throws Exception {
        String fileName = "/PSPs/" + folderName + "/" + "Ach_Cycle_01_" + UnzipService.fileDate + "_" + iin + ".txt";
        String pspOutputPath = new File(outputDirectoryPath + fileName).getAbsolutePath();
        FlatFileItemWriter<CdtTrfTxInfDto> writer = new FlatFileItemWriter<>();
        writer.setEncoding("UTF-8");
        writer.setHeaderCallback(new HeaderWriter(getAchHeader()));
        writer.setLineAggregator(new AchLineAggregator());
        writer.setResource(new FileSystemResource(pspOutputPath));
        writer.afterPropertiesSet();
        return writer;
    }


    private String getAchHeader() {
        return "Acceptor_name%IBAN%Deposite_Identity%Amount%Trace_code%payment_Identity";
    }


}