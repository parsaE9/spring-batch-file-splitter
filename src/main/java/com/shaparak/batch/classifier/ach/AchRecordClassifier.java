package com.shaparak.batch.classifier.ach;

import com.shaparak.batch.dto.xml.CdtTrfTxInfDto;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

@AllArgsConstructor
public class AchRecordClassifier implements Classifier<CdtTrfTxInfDto, ItemWriter<? super CdtTrfTxInfDto>> {

    private final ItemWriter<CdtTrfTxInfDto> sep2SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> sep1SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> sep3SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> pna1SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> pna2SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> pec1SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> sayn1SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> sayn2SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> fanvSwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> kicc1SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> kicc2SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> mabnSwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> sada1SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> sada2SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> pep1SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> pep2SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> persSwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> ecd1SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> ecd2SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> bpm1SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> bpm2SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> pec2SwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> sshpSwitchAchItemWriter;
    private final ItemWriter<CdtTrfTxInfDto> hubSwitchAchItemWriter;


    @Override
    public ItemWriter<? super CdtTrfTxInfDto> classify(CdtTrfTxInfDto batchRecord) {
        String switchCode = batchRecord.getPmtId().getEndToEndId().substring(6, 9);
        switch (switchCode) {
            case "042":
                return sep2SwitchAchItemWriter;
            case "041":
                return sep1SwitchAchItemWriter;
            case "043":
                return sep3SwitchAchItemWriter;
            case "051":
                return pna1SwitchAchItemWriter;
            case "052":
                return pna2SwitchAchItemWriter;
            case "061":
                return pec1SwitchAchItemWriter;
            case "082":
                return sayn1SwitchAchItemWriter;
            case "081":
                return sayn2SwitchAchItemWriter;
            case "091":
                return fanvSwitchAchItemWriter;
            case "111":
                return kicc1SwitchAchItemWriter;
            case "112":
                return kicc2SwitchAchItemWriter;
            case "121":
                return mabnSwitchAchItemWriter;
            case "131":
                return sada1SwitchAchItemWriter;
            case "132":
                return sada2SwitchAchItemWriter;
            case "141":
                return pep1SwitchAchItemWriter;
            case "142":
                return pep2SwitchAchItemWriter;
            case "011":
                return persSwitchAchItemWriter;
            case "021":
                return ecd1SwitchAchItemWriter;
            case "022":
                return ecd2SwitchAchItemWriter;
            case "031":
                return bpm1SwitchAchItemWriter;
            case "032":
                return bpm2SwitchAchItemWriter;
            case "062":
                return pec2SwitchAchItemWriter;
            case "271":
                return sshpSwitchAchItemWriter;
            case "001":
                return hubSwitchAchItemWriter;
            default:
                System.out.println("Ach record classifier error");
                return null;
        }
    }


}