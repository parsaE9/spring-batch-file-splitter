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
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class CsvService {

    public static Map<String, BankDto> bankMap;

    public static Map<String, SwitchDto> switchMap;


    public static void readCsvInputFiles(String switchCsvFilePath, String bankCsvFilePath) throws Exception {
        File switchFile = new File(switchCsvFilePath);
        File bankFile = new File(bankCsvFilePath);

        switchMap = csvToSwitchDtoMap(new FileInputStream(switchFile));
        bankMap = csvToBankDtoMap(new FileInputStream(bankFile));
    }


    private static Map<String, BankDto> csvToBankDtoMap(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            Map<String, BankDto> bankMap = new HashMap<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                BankDto bankDto = new BankDto();
                bankDto.setBankCode(csvRecord.get(0));
                bankDto.setName(csvRecord.get(1));
                bankDto.setBicCode(csvRecord.get(2));
                bankDto.setActive(Boolean.parseBoolean(csvRecord.get(3)));
                bankDto.setAddPan(Boolean.parseBoolean(csvRecord.get(4)));
                bankMap.put(bankDto.getBankCode(), bankDto);
            }

            return bankMap;
        } catch (IOException e) {
            throw new RuntimeException("failed to parse CSV file: " + e.getMessage());
        }
    }


    private static Map<String, SwitchDto> csvToSwitchDtoMap(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            Map<String, SwitchDto> switchMap = new HashMap<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                SwitchDto switchDto = new SwitchDto();
                switchDto.setFileName(csvRecord.get(0));
                switchDto.setFolderName(csvRecord.get(1));
                switchDto.setIin(csvRecord.get(2));
                switchDto.setActive(Boolean.parseBoolean(csvRecord.get(3)));
                switchMap.put(switchDto.getIin(), switchDto);
            }

            return switchMap;
        } catch (IOException e) {
            throw new RuntimeException("failed to parse CSV file: " + e.getMessage());
        }
    }


}