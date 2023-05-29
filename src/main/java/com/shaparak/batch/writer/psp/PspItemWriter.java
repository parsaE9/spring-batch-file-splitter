package com.shaparak.batch.writer.psp;

import com.shaparak.batch.aggregator.PspLineAggregator;
import com.shaparak.batch.header.HeaderWriter;
import com.shaparak.batch.model.Record;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
public class PspItemWriter {

    @Bean
    public FlatFileItemWriter<Record> pspItemWriter() throws Exception {
        return createWriter("psp1_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp2ItemWriter() throws Exception {
        return createWriter("psp2_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp3ItemWriter() throws Exception {
        return createWriter("psp3_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp4ItemWriter() throws Exception {
        return createWriter("psp4_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp5ItemWriter() throws Exception {
        return createWriter("psp5_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp6ItemWriter() throws Exception {
        return createWriter("psp6_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp7ItemWriter() throws Exception {
        return createWriter("psp7_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp8ItemWriter() throws Exception {
        return createWriter("psp8_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp9ItemWriter() throws Exception {
        return createWriter("psp9_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp10ItemWriter() throws Exception {
        return createWriter("psp10_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp11ItemWriter() throws Exception {
        return createWriter("psp11_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp12ItemWriter() throws Exception {
        return createWriter("psp12_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp13ItemWriter() throws Exception {
        return createWriter("psp13_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp14ItemWriter() throws Exception {
        return createWriter("psp14_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp15ItemWriter() throws Exception {
        return createWriter("psp15_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp16ItemWriter() throws Exception {
        return createWriter("psp16_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp17ItemWriter() throws Exception {
        return createWriter("psp17_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp18ItemWriter() throws Exception {
        return createWriter("psp18_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp19ItemWriter() throws Exception {
        return createWriter("psp19_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp20ItemWriter() throws Exception {
        return createWriter("psp20_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp21ItemWriter() throws Exception {
        return createWriter("psp21_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp22ItemWriter() throws Exception {
        return createWriter("psp22_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp23ItemWriter() throws Exception {
        return createWriter("psp23_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> psp24ItemWriter() throws Exception {
        return createWriter("psp24_result.shap");
    }


    private FlatFileItemWriter<Record> createWriter(String fileName) throws Exception {
        String pspOutputPath = new File("C:\\Users\\p.aliesfahani\\Desktop\\batch\\result\\psp\\" + fileName).getAbsolutePath();
        FlatFileItemWriter<Record> writer = new FlatFileItemWriter<>();
        writer.setHeaderCallback(new HeaderWriter(getPspHeader()));
        writer.setLineAggregator(new PspLineAggregator());
        writer.setResource(new FileSystemResource(pspOutputPath));
        writer.afterPropertiesSet();
        return writer;
    }


    private String getPspHeader() {
        return "row_number, psp_code, acceptor_code, trace_code, local_date, local_time, recive_date, IBAN, deposite_date, deposite_type, deposite_circle_number, terminal_type, proccess_type, card_type, amount_shaparak, reference_code, deposite_flag, acceptor_Commission, PSP_Commission, PSP_NET_Commission, terminal_code";
    }

}