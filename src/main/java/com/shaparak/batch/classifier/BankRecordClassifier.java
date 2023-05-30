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
    private final ItemWriter<Record> bank25ItemWriter;
    private final ItemWriter<Record> bank26ItemWriter;
    private final ItemWriter<Record> bank27ItemWriter;
    private final ItemWriter<Record> bank28ItemWriter;
    private final ItemWriter<Record> bank29ItemWriter;
    private final ItemWriter<Record> bank30ItemWriter;
    private final ItemWriter<Record> bank31ItemWriter;
    private final ItemWriter<Record> bank32ItemWriter;
    private final ItemWriter<Record> bank33ItemWriter;
    private final ItemWriter<Record> bank34ItemWriter;
    private final ItemWriter<Record> bank35ItemWriter;
    private final ItemWriter<Record> bank36ItemWriter;
    private final ItemWriter<Record> bank37ItemWriter;
    private final ItemWriter<Record> bank38ItemWriter;
    private final ItemWriter<Record> bank39ItemWriter;
    private final ItemWriter<Record> bank40ItemWriter;
    private final ItemWriter<Record> bank41ItemWriter;
    private final ItemWriter<Record> bank42ItemWriter;


    @Override
    public ItemWriter<? super Record> classify(Record record) {
        String bankCode = record.getIban().substring(4, 7);
        switch (bankCode) {
            case "011":
                return bank1ItemWriter;
            case "012":
                return bank2ItemWriter;
            case "013":
                return bank3ItemWriter;
            case "014":
                return bank4ItemWriter;
            case "015":
                return bank5ItemWriter;
            case "016":
                return bank6ItemWriter;
            case "017":
                return bank7ItemWriter;
            case "018":
                return bank8ItemWriter;
            case "019":
                return bank9ItemWriter;
            case "020":
                return bank10ItemWriter;
            case "051":
                return bank11ItemWriter;
            case "052":
                return bank12ItemWriter;
            case "053":
                return bank13ItemWriter;
            case "054":
                return bank14ItemWriter;
            case "055":
                return bank15ItemWriter;
            case "056":
                return bank16ItemWriter;
            case "057":
                return bank17ItemWriter;
            case "058":
                return bank18ItemWriter;
            case "059":
                return bank19ItemWriter;
            case "060":
                return bank20ItemWriter;
            case "061":
                return bank21ItemWriter;
            case "062":
                return bank22ItemWriter;
            case "063":
                return bank23ItemWriter;
            case "064":
                return bank24ItemWriter;
            case "065":
                return bank25ItemWriter;
            case "066":
                return bank26ItemWriter;
            case "069":
                return bank27ItemWriter;
            case "070":
                return bank28ItemWriter;
            case "073":
                return bank29ItemWriter;
            case "075":
                return bank30ItemWriter;
            case "078":
                return bank31ItemWriter;
            case "095":
                return bank32ItemWriter;
            case "080":
                return bank33ItemWriter;
            case "093":
                return bank34ItemWriter;
            case "079":
                return bank35ItemWriter;
//            case "001":
//                return bank36ItemWriter;
//            case "001":
//                return bank37ItemWriter;
//            case "001":
//                return bank38ItemWriter;
//            case "001":
//                return bank39ItemWriter;
//            case "001":
//                return bank40ItemWriter;
//            case "001":
//                return bank41ItemWriter;
//            case "001":
//                return bank42ItemWriter;
            default:
                System.out.println("bank record classifier error");
                return null;
        }
    }


}