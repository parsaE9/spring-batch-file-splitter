package com.shaparak.batch.writer.batch;

import com.shaparak.batch.aggregator.batch.BankLineAggregator;
import com.shaparak.batch.dto.csv.BankDto;
import com.shaparak.batch.header.HeaderWriter;
import com.shaparak.batch.dto.batch.BatchRecord;
import com.shaparak.batch.service.CsvService;
import com.shaparak.batch.service.UnzipService;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

@Configuration
public class BankItemWriter {

    @Value("${output.directory.path}")
    private String outputDirectoryPath;


    @Bean
    public FlatFileItemWriter<BatchRecord> markaziBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("10");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> sanatBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("11");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> mellatBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("12");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> refahBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("13");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> maskanBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("14");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> sepahBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("15");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> keshavarziBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("16");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> melliBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("17");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> tejaratBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("18");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> saderatBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("19");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> toseeSaderatBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("20");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> postBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("21");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> toseeTaavonItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("22");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> etebariToseeeBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("51");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> ghavaminBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("52");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> karafarinBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("53");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> parsianBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("54");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> eghtesadNovinBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("55");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> samanBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("56");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> pasargadBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("57");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> sarmayeBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("58");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> sinaBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("59");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> mehrBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("60");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> shahrBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("61");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> ayandeBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("62");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> ansarBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("63");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> gardeshgariBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("64");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> hekmatIranianBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("65");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> dayBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("66");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> iranZaminBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("69");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> resalatBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("70");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> kosarBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("73");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> asgariyeBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("75");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> khavarmianeBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("78");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> iranVenezuelaBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("95");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> noorBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("80");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> shaparakItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("93");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> mehreEghtesadBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("79");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> shaparakPaymentFacilitatorBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("92");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> refahiBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("0");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<BatchRecord> fuelBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("989");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }


    private FlatFileItemWriter<BatchRecord> createWriter(String folderName, String bicCode, String bankCode) throws Exception {
        String bankOutputPath = new File(outputDirectoryPath + "/Banks/" + folderName + "/batch_" + UnzipService.fileDate + "_cycle_01_details.shap_" + bicCode + "_" + bankCode + ".txt").getAbsolutePath();
        FlatFileItemWriter<BatchRecord> writer = new FlatFileItemWriter<>();
        writer.setHeaderCallback(new HeaderWriter(getBankHeader()));
        writer.setLineAggregator(new BankLineAggregator());
        writer.setResource(new FileSystemResource(bankOutputPath));
        writer.afterPropertiesSet();
        return writer;
    }


    private String getBankHeader() {
        return "row_number|psp_code|acceptor_code|trace_code|local_date|local_time|recive_date|IBAN|deposite_date|deposite_type|deposite_circle_number|terminal_type|proccess_type|card_type|amount_shaparak|reference_code|deposite_flag|terminal_code|orig_txn_info";
    }


}