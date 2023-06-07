package com.shaparak.batch.writer;

import com.shaparak.batch.aggregator.PspLineAggregator;
import com.shaparak.batch.dto.SwitchDto;
import com.shaparak.batch.header.HeaderWriter;
import com.shaparak.batch.dto.Record;
import com.shaparak.batch.service.CsvService;
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
    public FlatFileItemWriter<Record> sep2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672042");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> sep1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672041");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> sep3SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672043");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> pna1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672051");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> pna2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672052");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> pec1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672061");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> pec2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672062");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> sayn1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672082");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> sayn2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672081");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> fanvSwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672091");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> kicc1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672111");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> kicc2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672112");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> mabnSwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672121");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> sada1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672131");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> sada2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672132");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> pep1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672141");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> pep2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672142");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> persSwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672011");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> ecd1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672021");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> ecd2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672022");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> bpm1SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672031");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> bpm2SwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672032");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> sshpSwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672271");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }

    @Bean
    public FlatFileItemWriter<Record> hubSwitchItemWriter() throws Exception {
        SwitchDto switchDto = CsvService.switchMap.get("581672001");
        return createWriter(switchDto.getName(), switchDto.getIin());
    }


    private FlatFileItemWriter<Record> createWriter(String folderName, String iin) throws Exception {
//        batch_14011202_cycle_01_details.shap_psp_581672081.txt
        String pspOutputPath = new File(outputDirectoryPath + "\\psp\\" + folderName + "\\" + "batch_14011202_cycle_01_details.shap_psp_" + iin + ".txt").getAbsolutePath();
        FlatFileItemWriter<Record> writer = new FlatFileItemWriter<>();
        writer.setHeaderCallback(new HeaderWriter(getPspHeader()));
        writer.setLineAggregator(new PspLineAggregator());
        writer.setResource(new FileSystemResource(pspOutputPath));
        writer.afterPropertiesSet();
        return writer;
    }


    private String getPspHeader() {
        return "row_number| psp_code| acceptor_code| trace_code| local_date| local_time| recive_date| IBAN| deposite_date| deposite_type| deposite_circle_number| terminal_type| proccess_type| card_type| amount_shaparak| reference_code| deposite_flag| acceptor_Commission| PSP_Commission| PSP_NET_Commission| terminal_code";
    }

}