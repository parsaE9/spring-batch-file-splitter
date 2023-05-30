package com.shaparak.batch.writer.bank;

import com.shaparak.batch.aggregator.BankLineAggregator;
import com.shaparak.batch.aggregator.PspLineAggregator;
import com.shaparak.batch.header.HeaderWriter;
import com.shaparak.batch.model.Record;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
public class BankItemWriter {


    @Bean
    public FlatFileItemWriter<Record> bank1ItemWriter() throws Exception {
        return createWriter("bank1_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank2ItemWriter() throws Exception {
        return createWriter("bank2_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank3ItemWriter() throws Exception {
        return createWriter("bank3_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank4ItemWriter() throws Exception {
        return createWriter("bank4_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank5ItemWriter() throws Exception {
        return createWriter("bank5_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank6ItemWriter() throws Exception {
        return createWriter("bank6_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank7ItemWriter() throws Exception {
        return createWriter("bank7_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank8ItemWriter() throws Exception {
        return createWriter("bank8_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank9ItemWriter() throws Exception {
        return createWriter("bank9_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank10ItemWriter() throws Exception {
        return createWriter("bank10_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank11ItemWriter() throws Exception {
        return createWriter("bank11_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank12ItemWriter() throws Exception {
        return createWriter("bank12_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank13ItemWriter() throws Exception {
        return createWriter("bank13_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank14ItemWriter() throws Exception {
        return createWriter("bank14_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank15ItemWriter() throws Exception {
        return createWriter("bank15_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank16ItemWriter() throws Exception {
        return createWriter("bank16_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank17ItemWriter() throws Exception {
        return createWriter("bank17_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank18ItemWriter() throws Exception {
        return createWriter("bank18_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank19ItemWriter() throws Exception {
        return createWriter("bank19_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank20ItemWriter() throws Exception {
        return createWriter("bank20_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank21ItemWriter() throws Exception {
        return createWriter("bank21_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank22ItemWriter() throws Exception {
        return createWriter("bank22_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank23ItemWriter() throws Exception {
        return createWriter("bank23_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank24ItemWriter() throws Exception {
        return createWriter("bank24_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank25ItemWriter() throws Exception {
        return createWriter("bank25_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank26ItemWriter() throws Exception {
        return createWriter("bank26_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank27ItemWriter() throws Exception {
        return createWriter("bank27_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank28ItemWriter() throws Exception {
        return createWriter("bank28_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank29ItemWriter() throws Exception {
        return createWriter("bank29_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank30ItemWriter() throws Exception {
        return createWriter("bank30_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank31ItemWriter() throws Exception {
        return createWriter("bank31_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank32ItemWriter() throws Exception {
        return createWriter("bank32_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank33ItemWriter() throws Exception {
        return createWriter("bank33_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank34ItemWriter() throws Exception {
        return createWriter("bank34_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank35ItemWriter() throws Exception {
        return createWriter("bank35_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank36ItemWriter() throws Exception {
        return createWriter("bank36_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank37ItemWriter() throws Exception {
        return createWriter("bank37_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank38ItemWriter() throws Exception {
        return createWriter("bank38_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank39ItemWriter() throws Exception {
        return createWriter("bank39_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank40ItemWriter() throws Exception {
        return createWriter("bank40_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank41ItemWriter() throws Exception {
        return createWriter("bank41_result.shap");
    }

    @Bean
    public FlatFileItemWriter<Record> bank42ItemWriter() throws Exception {
        return createWriter("bank42_result.shap");
    }


    private FlatFileItemWriter<Record> createWriter(String fileName) throws Exception {
        String bankOutputPath = new File("C:\\Users\\p.aliesfahani\\Desktop\\batch\\result\\bank\\" + fileName).getAbsolutePath();
        FlatFileItemWriter<Record> writer = new FlatFileItemWriter<>();
        writer.setHeaderCallback(new HeaderWriter(getBankHeader()));
        writer.setLineAggregator(new BankLineAggregator());
        writer.setResource(new FileSystemResource(bankOutputPath));
        writer.afterPropertiesSet();
        return writer;
    }


    private String getBankHeader() {
//        return "row_number, psp_code, acceptor_code, trace_code, local_date, local_time, recive_date, IBAN, deposite_date, deposite_type, deposite_circle_number, terminal_type, proccess_type, card_type, amount_shaparak, reference_code, deposite_flag, terminal_code, orig_txn_info";
        return "row_number, psp_code, acceptor_code, trace_code, local_date, local_time, recive_date, IBAN, deposite_date, deposite_type, deposite_circle_number, terminal_type, proccess_type, card_type, amount_shaparak, reference_code, deposite_flag, terminal_code";
    }


}