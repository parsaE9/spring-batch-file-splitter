package com.shaparak.batch.classifier;

import com.shaparak.batch.dto.Record;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

@AllArgsConstructor
public class PspRecordClassifier implements Classifier<Record, ItemWriter<? super Record>> {

    private final ItemWriter<Record> sep2SwitchItemWriter;
    private final ItemWriter<Record> sep1SwitchItemWriter;
    private final ItemWriter<Record> sep3SwitchItemWriter;
    private final ItemWriter<Record> pna1SwitchItemWriter;
    private final ItemWriter<Record> pna2SwitchItemWriter;
    private final ItemWriter<Record> pec1SwitchItemWriter;
    private final ItemWriter<Record> sayn1SwitchItemWriter;
    private final ItemWriter<Record> sayn2SwitchItemWriter;
    private final ItemWriter<Record> fanvSwitchItemWriter;
    private final ItemWriter<Record> kicc1SwitchItemWriter;
    private final ItemWriter<Record> kicc2SwitchItemWriter;
    private final ItemWriter<Record> mabnSwitchItemWriter;
    private final ItemWriter<Record> sada1SwitchItemWriter;
    private final ItemWriter<Record> sada2SwitchItemWriter;
    private final ItemWriter<Record> pep1SwitchItemWriter;
    private final ItemWriter<Record> pep2SwitchItemWriter;
    private final ItemWriter<Record> persSwitchItemWriter;
    private final ItemWriter<Record> ecd1SwitchItemWriter;
    private final ItemWriter<Record> ecd2SwitchItemWriter;
    private final ItemWriter<Record> bpm1SwitchItemWriter;
    private final ItemWriter<Record> bpm2SwitchItemWriter;
    private final ItemWriter<Record> pec2SwitchItemWriter;
    private final ItemWriter<Record> sshpSwitchItemWriter;
    private final ItemWriter<Record> hubSwitchItemWriter;


    @Override
    public ItemWriter<? super Record> classify(Record record) {
        int pspCodeLength = record.getPspCode().length();
        String switchCode = record.getPspCode().substring(pspCodeLength - 3, pspCodeLength);
        switch (switchCode) {
            case "042":
                return sep2SwitchItemWriter;
            case "041":
                return sep1SwitchItemWriter;
            case "043":
                return sep3SwitchItemWriter;
            case "051":
                return pna1SwitchItemWriter;
            case "052":
                return pna2SwitchItemWriter;
            case "061":
                return pec1SwitchItemWriter;
            case "082":
                return sayn1SwitchItemWriter;
            case "081":
                return sayn2SwitchItemWriter;
            case "091":
                return fanvSwitchItemWriter;
            case "111":
                return kicc1SwitchItemWriter;
            case "112":
                return kicc2SwitchItemWriter;
            case "121":
                return mabnSwitchItemWriter;
            case "131":
                return sada1SwitchItemWriter;
            case "132":
                return sada2SwitchItemWriter;
            case "141":
                return pep1SwitchItemWriter;
            case "142":
                return pep2SwitchItemWriter;
            case "011":
                return persSwitchItemWriter;
            case "021":
                return ecd1SwitchItemWriter;
            case "022":
                return ecd2SwitchItemWriter;
            case "031":
                return bpm1SwitchItemWriter;
            case "032":
                return bpm2SwitchItemWriter;
            case "062":
                return pec2SwitchItemWriter;
            case "271":
                return sshpSwitchItemWriter;
            case "001":
                return hubSwitchItemWriter;
            default:
                System.out.println("psp record classifier error");
                return null;
        }
    }


}