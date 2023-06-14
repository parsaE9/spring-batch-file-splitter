package com.shaparak.batch.service;

import com.shaparak.batch.dto.csv.BankDto;
import com.shaparak.batch.dto.csv.SwitchDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class CsvService {

    public static Map<String, BankDto> bankMap;

    public static Map<String, SwitchDto> switchMap;

    @Value("${input.csv.file.path.switch}")
    private String switchCsvFilePath;

    @Value("${input.csv.file.path.bank}")
    private String bankCsvFilePath;

    @Autowired
    private UnzipService unzipService;


    @PostConstruct
    private void init() throws Exception {
        File switchFile = new File(switchCsvFilePath);
        File bankFile = new File(bankCsvFilePath);

        switchMap = csvToSwitchDtoMap(new FileInputStream(switchFile));
        bankMap = csvToBankDtoMap(new FileInputStream(bankFile));


//        unzipService.clearFolders();
//        unzipService.unzip();
    }


    private Map<String, BankDto> csvToBankDtoMap(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            Map<String, BankDto> bankMap = new HashMap<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                BankDto bankDto = new BankDto();
                bankDto.setName(csvRecord.get(1));
                bankDto.setBankCode(csvRecord.get(0));
                bankDto.setBicCode(csvRecord.get(2));
                bankMap.put(bankDto.getBankCode(), bankDto);
            }

            return bankMap;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }


    private Map<String, SwitchDto> csvToSwitchDtoMap(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            Map<String, SwitchDto> switchMap = new HashMap<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                SwitchDto switchDto = new SwitchDto();
                switchDto.setFileName(csvRecord.get(0));
                switchDto.setFolderName(csvRecord.get(1));
                switchDto.setIin(csvRecord.get(2));
                switchMap.put(switchDto.getIin(), switchDto);
            }

            return switchMap;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }


}