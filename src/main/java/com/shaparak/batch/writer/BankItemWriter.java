package com.shaparak.batch.writer;

import com.github.mfathi91.time.PersianDate;
import com.shaparak.batch.aggregator.BankLineAggregator;
import com.shaparak.batch.dto.BankDto;
import com.shaparak.batch.header.HeaderWriter;
import com.shaparak.batch.dto.Record;
import com.shaparak.batch.service.CsvService;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.annotation.PostConstruct;
import java.io.File;

@Configuration
public class BankItemWriter {

    @Value("${output.directory.path}")
    private String outputDirectoryPath;

    private String todayDate;


    @PostConstruct
    private void init() {
        String[] todayDate = (PersianDate.now() + "").split("-");
        this.todayDate = todayDate[0] + todayDate[1] + todayDate[2];
    }


    @Bean
    public FlatFileItemWriter<Record> markaziBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("10");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> sanatBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("11");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> mellatBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("12");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> refahBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("13");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> maskanBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("14");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> sepahBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("15");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> keshavarziBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("16");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> melliBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("17");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> tejaratBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("18");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> saderatBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("19");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> toseeSaderatBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("20");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> postBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("21");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> toseeTaavonItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("22");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> etebariToseeeBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("51");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> ghavaminBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("52");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> karafarinBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("53");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> parsianBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("54");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> eghtesadNovinBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("55");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> samanBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("56");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> pasargadBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("57");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> sarmayeBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("58");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> sinaBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("59");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> mehrBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("60");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> shahrBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("61");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> ayandeBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("62");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> ansarBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("63");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> gardeshgariBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("64");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> hekmatIranianBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("65");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> dayBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("66");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> iranZaminBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("69");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> resalatBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("70");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> kosarBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("73");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> asgariyeBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("75");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> khavarmianeBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("78");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> iranVenezuelaBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("95");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> noorBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("80");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> shaparakItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("93");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> mehreEghtesadBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("79");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> shaparakPaymentFacilitatorBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("92");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> refahiBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("0");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }

    @Bean
    public FlatFileItemWriter<Record> fuelBankItemWriter() throws Exception {
        BankDto bankDto = CsvService.bankMap.get("989");
        return createWriter(bankDto.getName(), bankDto.getBicCode(), bankDto.getBankCode());
    }


    private FlatFileItemWriter<Record> createWriter(String folderName, String bicCode, String bankCode) throws Exception {
        String bankOutputPath = new File(outputDirectoryPath + "/Banks/" + folderName + "/batch_" + todayDate + "_cycle_01_details.shap_" + bicCode + "_" + bankCode + ".txt").getAbsolutePath();
        FlatFileItemWriter<Record> writer = new FlatFileItemWriter<>();
        writer.setHeaderCallback(new HeaderWriter(getBankHeader()));
        writer.setLineAggregator(new BankLineAggregator());
        writer.setResource(new FileSystemResource(bankOutputPath));
        writer.afterPropertiesSet();
        return writer;
    }


    private String getBankHeader() {
        return "row_number| psp_code| acceptor_code| trace_code| local_date| local_time| recive_date| IBAN| deposite_date| deposite_type| deposite_circle_number| terminal_type| proccess_type| card_type| amount_shaparak| reference_code| deposite_flag| terminal_code| orig_txn_info";
    }


}