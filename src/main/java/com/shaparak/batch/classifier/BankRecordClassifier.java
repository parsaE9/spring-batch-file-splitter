package com.shaparak.batch.classifier;

import com.shaparak.batch.dto.Record;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

@AllArgsConstructor
public class BankRecordClassifier implements Classifier<Record, ItemWriter<? super Record>> {

    private final ItemWriter<Record> markaziBankItemWriter;
    private final ItemWriter<Record> sanatBankItemWriter;
    private final ItemWriter<Record> mellatBankItemWriter;
    private final ItemWriter<Record> refahBankItemWriter;
    private final ItemWriter<Record> maskanBankItemWriter;
    private final ItemWriter<Record> sepahBankItemWriter;
    private final ItemWriter<Record> keshavarziBankItemWriter;
    private final ItemWriter<Record> melliBankItemWriter;
    private final ItemWriter<Record> tejaratBankItemWriter;
    private final ItemWriter<Record> saderatBankItemWriter;
    private final ItemWriter<Record> toseeSaderatBankItemWriter;
    private final ItemWriter<Record> postBankItemWriter;
    private final ItemWriter<Record> toseeTaavonItemWriter;
    private final ItemWriter<Record> etebariToseeeBankItemWriter;
    private final ItemWriter<Record> ghavaminBankItemWriter;
    private final ItemWriter<Record> karafarinBankItemWriter;
    private final ItemWriter<Record> parsianBankItemWriter;
    private final ItemWriter<Record> eghtesadNovinBankItemWriter;
    private final ItemWriter<Record> samanBankItemWriter;
    private final ItemWriter<Record> pasargadBankItemWriter;
    private final ItemWriter<Record> sarmayeBankItemWriter;
    private final ItemWriter<Record> sinaBankItemWriter;
    private final ItemWriter<Record> mehrBankItemWriter;
    private final ItemWriter<Record> shahrBankItemWriter;
    private final ItemWriter<Record> ayandeBankItemWriter;
    private final ItemWriter<Record> ansarBankItemWriter;
    private final ItemWriter<Record> gardeshgariBankItemWriter;
    private final ItemWriter<Record> hekmatIranianBankItemWriter;
    private final ItemWriter<Record> dayBankItemWriter;
    private final ItemWriter<Record> iranZaminBankItemWriter;
    private final ItemWriter<Record> resalatBankItemWriter;
    private final ItemWriter<Record> kosarBankItemWriter;
    private final ItemWriter<Record> asgariyeBankItemWriter;
    private final ItemWriter<Record> khavarmianeBankItemWriter;
    private final ItemWriter<Record> iranVenezuelaBankItemWriter;
    private final ItemWriter<Record> noorBankItemWriter;
    private final ItemWriter<Record> shaparakItemWriter;
    private final ItemWriter<Record> mehreEghtesadBankItemWriter;

    private final ItemWriter<Record> shaparakPaymentFacilitatorBankItemWriter;
    private final ItemWriter<Record> refahiBankItemWriter;
    private final ItemWriter<Record> fuelBankItemWriter;


    @Override
    public ItemWriter<? super Record> classify(Record record) {
        if (record.getCardNumber().substring(0, 3).equals("989"))
            return fuelBankItemWriter;


        String bankCode = record.getIban().substring(4, 7);
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