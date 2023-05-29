package com.shaparak.batch.classifier;

import com.shaparak.batch.model.Record;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

@AllArgsConstructor
public class BankRecordClassifier implements Classifier<Record, ItemWriter<? super Record>> {

    private final ItemWriter<Record> bank1ItemWriter;
    private final ItemWriter<Record> bank2ItemWriter;
    private final ItemWriter<Record> bank3ItemWriter;
    private final ItemWriter<Record> bank4ItemWriter;
    private final ItemWriter<Record> bank5ItemWriter;
    private final ItemWriter<Record> bank6ItemWriter;
    private final ItemWriter<Record> bank7ItemWriter;
    private final ItemWriter<Record> bank8ItemWriter;
    private final ItemWriter<Record> bank9ItemWriter;
    private final ItemWriter<Record> bank10ItemWriter;
    private final ItemWriter<Record> bank11ItemWriter;
    private final ItemWriter<Record> bank12ItemWriter;
    private final ItemWriter<Record> bank13ItemWriter;
    private final ItemWriter<Record> bank14ItemWriter;
    private final ItemWriter<Record> bank15ItemWriter;
    private final ItemWriter<Record> bank16ItemWriter;
    private final ItemWriter<Record> bank17ItemWriter;
    private final ItemWriter<Record> bank18ItemWriter;
    private final ItemWriter<Record> bank19ItemWriter;
    private final ItemWriter<Record> bank20ItemWriter;
    private final ItemWriter<Record> bank21ItemWriter;
    private final ItemWriter<Record> bank22ItemWriter;
    private final ItemWriter<Record> bank23ItemWriter;
    private final ItemWriter<Record> bank24ItemWriter;


    @Override
    public ItemWriter<? super Record> classify(Record record) {
        int pspCodeLength = record.getPspCode().length();
        String switchCode = record.getPspCode().substring(pspCodeLength - 3, pspCodeLength);
        switch (switchCode) {
            case "042":
                return bank1ItemWriter;
            case "041":
                return bank2ItemWriter;
            case "043":
                return bank3ItemWriter;
            case "051":
                return bank4ItemWriter;
            case "052":
                return bank5ItemWriter;
            case "061":
                return bank6ItemWriter;
            case "082":
                return bank7ItemWriter;
            case "081":
                return bank8ItemWriter;
            case "091":
                return bank9ItemWriter;
            case "111":
                return bank10ItemWriter;
            case "112":
                return bank11ItemWriter;
            case "121":
                return bank12ItemWriter;
            case "131":
                return bank13ItemWriter;
            case "132":
                return bank14ItemWriter;
            case "141":
                return bank15ItemWriter;
            case "142":
                return bank16ItemWriter;
            case "011":
                return bank17ItemWriter;
            case "021":
                return bank18ItemWriter;
            case "022":
                return bank19ItemWriter;
            case "031":
                return bank20ItemWriter;
            case "032":
                return bank21ItemWriter;
            case "062":
                return bank22ItemWriter;
            case "271":
                return bank23ItemWriter;
            case "001":
                return bank24ItemWriter;
            default:
                System.out.println("record classifier error");
                return null;
        }
    }


}