package com.shaparak.batch.classifier.batch;

import com.shaparak.batch.dto.batch.BatchRecord;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

@AllArgsConstructor
public class PspRecordClassifier implements Classifier<BatchRecord, ItemWriter<? super BatchRecord>> {

    private final ItemWriter<BatchRecord> sep2SwitchItemWriter;
    private final ItemWriter<BatchRecord> sep1SwitchItemWriter;
    private final ItemWriter<BatchRecord> sep3SwitchItemWriter;
    private final ItemWriter<BatchRecord> pna1SwitchItemWriter;
    private final ItemWriter<BatchRecord> pna2SwitchItemWriter;
    private final ItemWriter<BatchRecord> pec1SwitchItemWriter;
    private final ItemWriter<BatchRecord> sayn1SwitchItemWriter;
    private final ItemWriter<BatchRecord> sayn2SwitchItemWriter;
    private final ItemWriter<BatchRecord> fanvSwitchItemWriter;
    private final ItemWriter<BatchRecord> kicc1SwitchItemWriter;
    private final ItemWriter<BatchRecord> kicc2SwitchItemWriter;
    private final ItemWriter<BatchRecord> mabnSwitchItemWriter;
    private final ItemWriter<BatchRecord> sada1SwitchItemWriter;
    private final ItemWriter<BatchRecord> sada2SwitchItemWriter;
    private final ItemWriter<BatchRecord> pep1SwitchItemWriter;
    private final ItemWriter<BatchRecord> pep2SwitchItemWriter;
    private final ItemWriter<BatchRecord> persSwitchItemWriter;
    private final ItemWriter<BatchRecord> ecd1SwitchItemWriter;
    private final ItemWriter<BatchRecord> ecd2SwitchItemWriter;
    private final ItemWriter<BatchRecord> bpm1SwitchItemWriter;
    private final ItemWriter<BatchRecord> bpm2SwitchItemWriter;
    private final ItemWriter<BatchRecord> pec2SwitchItemWriter;
    private final ItemWriter<BatchRecord> sshpSwitchItemWriter;
    private final ItemWriter<BatchRecord> hubSwitchItemWriter;


    @Override
    public ItemWriter<? super BatchRecord> classify(BatchRecord batchRecord) {
        int pspCodeLength = batchRecord.getPspCode().length();
        String switchCode = batchRecord.getPspCode().substring(pspCodeLength - 3, pspCodeLength);
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