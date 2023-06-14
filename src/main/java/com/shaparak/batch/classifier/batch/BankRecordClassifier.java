package com.shaparak.batch.classifier.batch;

import com.shaparak.batch.dto.BatchRecord;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

@AllArgsConstructor
public class BankRecordClassifier implements Classifier<BatchRecord, ItemWriter<? super BatchRecord>> {

    private final ItemWriter<BatchRecord> markaziBankItemWriter;
    private final ItemWriter<BatchRecord> sanatBankItemWriter;
    private final ItemWriter<BatchRecord> mellatBankItemWriter;
    private final ItemWriter<BatchRecord> refahBankItemWriter;
    private final ItemWriter<BatchRecord> maskanBankItemWriter;
    private final ItemWriter<BatchRecord> sepahBankItemWriter;
    private final ItemWriter<BatchRecord> keshavarziBankItemWriter;
    private final ItemWriter<BatchRecord> melliBankItemWriter;
    private final ItemWriter<BatchRecord> tejaratBankItemWriter;
    private final ItemWriter<BatchRecord> saderatBankItemWriter;
    private final ItemWriter<BatchRecord> toseeSaderatBankItemWriter;
    private final ItemWriter<BatchRecord> postBankItemWriter;
    private final ItemWriter<BatchRecord> toseeTaavonItemWriter;
    private final ItemWriter<BatchRecord> etebariToseeeBankItemWriter;
    private final ItemWriter<BatchRecord> ghavaminBankItemWriter;
    private final ItemWriter<BatchRecord> karafarinBankItemWriter;
    private final ItemWriter<BatchRecord> parsianBankItemWriter;
    private final ItemWriter<BatchRecord> eghtesadNovinBankItemWriter;
    private final ItemWriter<BatchRecord> samanBankItemWriter;
    private final ItemWriter<BatchRecord> pasargadBankItemWriter;
    private final ItemWriter<BatchRecord> sarmayeBankItemWriter;
    private final ItemWriter<BatchRecord> sinaBankItemWriter;
    private final ItemWriter<BatchRecord> mehrBankItemWriter;
    private final ItemWriter<BatchRecord> shahrBankItemWriter;
    private final ItemWriter<BatchRecord> ayandeBankItemWriter;
    private final ItemWriter<BatchRecord> ansarBankItemWriter;
    private final ItemWriter<BatchRecord> gardeshgariBankItemWriter;
    private final ItemWriter<BatchRecord> hekmatIranianBankItemWriter;
    private final ItemWriter<BatchRecord> dayBankItemWriter;
    private final ItemWriter<BatchRecord> iranZaminBankItemWriter;
    private final ItemWriter<BatchRecord> resalatBankItemWriter;
    private final ItemWriter<BatchRecord> kosarBankItemWriter;
    private final ItemWriter<BatchRecord> asgariyeBankItemWriter;
    private final ItemWriter<BatchRecord> khavarmianeBankItemWriter;
    private final ItemWriter<BatchRecord> iranVenezuelaBankItemWriter;
    private final ItemWriter<BatchRecord> noorBankItemWriter;
    private final ItemWriter<BatchRecord> shaparakItemWriter;
    private final ItemWriter<BatchRecord> mehreEghtesadBankItemWriter;

    private final ItemWriter<BatchRecord> shaparakPaymentFacilitatorBankItemWriter;
    private final ItemWriter<BatchRecord> refahiBankItemWriter;
    private final ItemWriter<BatchRecord> fuelBankItemWriter;


    @Override
    public ItemWriter<? super BatchRecord> classify(BatchRecord batchRecord) {
        if (batchRecord.getCardNumber().startsWith("989"))
            return fuelBankItemWriter;
        else if (batchRecord.getCardNumber().startsWith("9"))
            return refahiBankItemWriter;


        String bankCode = batchRecord.getIban().substring(4, 7);
        switch (bankCode) {
            case "010":
                return markaziBankItemWriter;
            case "011":
                return sanatBankItemWriter;
            case "012":
                return mellatBankItemWriter;
            case "013":
                return refahBankItemWriter;
            case "014":
                return maskanBankItemWriter;
            case "015":
                return sepahBankItemWriter;
            case "016":
                return keshavarziBankItemWriter;
            case "017":
                return melliBankItemWriter;
            case "018":
                return tejaratBankItemWriter;
            case "019":
                return saderatBankItemWriter;
            case "020":
                return toseeSaderatBankItemWriter;
            case "021":
                return postBankItemWriter;
            case "022":
                return toseeTaavonItemWriter;
            case "051":
                return etebariToseeeBankItemWriter;
            case "052":
                return ghavaminBankItemWriter;
            case "053":
                return karafarinBankItemWriter;
            case "054":
                return parsianBankItemWriter;
            case "055":
                return eghtesadNovinBankItemWriter;
            case "056":
                return samanBankItemWriter;
            case "057":
                return pasargadBankItemWriter;
            case "058":
                return sarmayeBankItemWriter;
            case "059":
                return sinaBankItemWriter;
            case "060":
                return mehrBankItemWriter;
            case "061":
                return shahrBankItemWriter;
            case "062":
                return ayandeBankItemWriter;
            case "063":
                return ansarBankItemWriter;
            case "064":
                return gardeshgariBankItemWriter;
            case "065":
                return hekmatIranianBankItemWriter;
            case "066":
                return dayBankItemWriter;
            case "069":
                return iranZaminBankItemWriter;
            case "070":
                return resalatBankItemWriter;
            case "073":
                return kosarBankItemWriter;
            case "075":
                return asgariyeBankItemWriter;
            case "078":
                return khavarmianeBankItemWriter;
            case "095":
                return iranVenezuelaBankItemWriter;
            case "080":
                return noorBankItemWriter;
            case "093":
                return shaparakItemWriter;
            case "079":
                return mehreEghtesadBankItemWriter;
            case "092":
                return shaparakPaymentFacilitatorBankItemWriter;
            default:
                System.out.println("bank record classifier error");
                return null;
        }
    }


}