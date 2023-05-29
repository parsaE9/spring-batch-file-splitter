package com.shaparak.batch.classifier;

import com.shaparak.batch.model.Record;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

@AllArgsConstructor
public class PspRecordClassifier implements Classifier<Record, ItemWriter<? super Record>> {

    private final ItemWriter<Record> psp1ItemWriter;
    private final ItemWriter<Record> psp2ItemWriter;
    private final ItemWriter<Record> psp3ItemWriter;
    private final ItemWriter<Record> psp4ItemWriter;
    private final ItemWriter<Record> psp5ItemWriter;
    private final ItemWriter<Record> psp6ItemWriter;
    private final ItemWriter<Record> psp7ItemWriter;
    private final ItemWriter<Record> psp8ItemWriter;
    private final ItemWriter<Record> psp9ItemWriter;
    private final ItemWriter<Record> psp10ItemWriter;
    private final ItemWriter<Record> psp11ItemWriter;
    private final ItemWriter<Record> psp12ItemWriter;
    private final ItemWriter<Record> psp13ItemWriter;
    private final ItemWriter<Record> psp14ItemWriter;
    private final ItemWriter<Record> psp15ItemWriter;
    private final ItemWriter<Record> psp16ItemWriter;
    private final ItemWriter<Record> psp17ItemWriter;
    private final ItemWriter<Record> psp18ItemWriter;
    private final ItemWriter<Record> psp19ItemWriter;
    private final ItemWriter<Record> psp20ItemWriter;
    private final ItemWriter<Record> psp21ItemWriter;
    private final ItemWriter<Record> psp22ItemWriter;
    private final ItemWriter<Record> psp23ItemWriter;
    private final ItemWriter<Record> psp24ItemWriter;


    @Override
    public ItemWriter<? super Record> classify(Record record) {
        int pspCodeLength = record.getPspCode().length();
        String switchCode = record.getPspCode().substring(pspCodeLength - 3, pspCodeLength);
        switch (switchCode) {
            case "042":
                return psp1ItemWriter;
            case "041":
                return psp2ItemWriter;
            case "043":
                return psp3ItemWriter;
            case "051":
                return psp4ItemWriter;
            case "052":
                return psp5ItemWriter;
            case "061":
                return psp6ItemWriter;
            case "082":
                return psp7ItemWriter;
            case "081":
                return psp8ItemWriter;
            case "091":
                return psp9ItemWriter;
            case "111":
                return psp10ItemWriter;
            case "112":
                return psp11ItemWriter;
            case "121":
                return psp12ItemWriter;
            case "131":
                return psp13ItemWriter;
            case "132":
                return psp14ItemWriter;
            case "141":
                return psp15ItemWriter;
            case "142":
                return psp16ItemWriter;
            case "011":
                return psp17ItemWriter;
            case "021":
                return psp18ItemWriter;
            case "022":
                return psp19ItemWriter;
            case "031":
                return psp20ItemWriter;
            case "032":
                return psp21ItemWriter;
            case "062":
                return psp22ItemWriter;
            case "271":
                return psp23ItemWriter;
            case "001":
                return psp24ItemWriter;
            default:
                System.out.println("record classifier error");
                return null;
        }
    }


}