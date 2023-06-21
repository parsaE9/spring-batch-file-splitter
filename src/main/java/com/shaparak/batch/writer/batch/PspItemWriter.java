package com.shaparak.batch.writer.batch;

import com.shaparak.batch.aggregator.batch.PspLineAggregator;
import com.shaparak.batch.dto.csv.SwitchDto;
import com.shaparak.batch.header.HeaderWriter;
import com.shaparak.batch.dto.BatchRecord;
import com.shaparak.batch.service.CsvService;
import com.shaparak.batch.service.UnzipService;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
public class PspItemWriter {

    @Value("${output.directory.path}")
    private String outputDirectoryPath;


    @Bean
    public FlatFileItemWriter<BatchRecord> sep2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672042");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> sep1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672041");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> sep3SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672043");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> pna1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672051");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> pna2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672052");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> pec1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672061");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> pec2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672062");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> sayn1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672082");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> sayn2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672081");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> fanvSwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672091");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> kicc1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672111");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> kicc2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672112");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> mabnSwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672121");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> sada1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672131");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> sada2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672132");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> pep1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672141");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> pep2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672142");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> persSwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672011");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> ecd1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672021");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> ecd2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672022");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> bpm1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672031");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> bpm2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672032");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> sshpSwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672271");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> hubSwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672001");
        return createWriter(switchDto.getFolderName(), switchDto.getIin());
    }


    private FlatFileItemWriter<BatchRecord> createWriter(String folderName, String iin) throws Exception {
        String pspOutputPath = new File(outputDirectoryPath + "/PSPs/" + folderName + "/" + "batch_" + UnzipService.fileDate + "_cycle_01_details.shap_psp_" + iin + ".txt").getAbsolutePath();
        FlatFileItemWriter<BatchRecord> writer = new FlatFileItemWriter<>();
        writer.setHeaderCallback(new HeaderWriter(getPspHeader()));
        writer.setLineAggregator(new PspLineAggregator());
        writer.setResource(new FileSystemResource(pspOutputPath));
        writer.afterPropertiesSet();
        return writer;
    }


    private String getPspHeader() {
        return "row_number|psp_code|acceptor_code|trace_code|local_date|local_time|recive_date|IBAN|deposite_date|deposite_type|deposite_circle_number|terminal_type|proccess_type|card_type|amount_shaparak|reference_code|deposite_flag|acceptor_Commission|PSP_Commission|PSP_NET_Commission|terminal_code|orig_txn_info|acceptor_Net_Commission|acceptor_bank_Commission|business_category_code|reserve";
    }


}