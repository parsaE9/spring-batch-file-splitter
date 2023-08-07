package com.shaparak.batch.dto.batch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BatchRecord {

    private String rowNumber;
    private String pspCode;
    private String acceptorCode;
    private String traceCode;
    private String localDate;
    private String localTime;
    private String reciveDate;
    private String iban;
    private String depositeDate;
    private String depositeType;
    private String depositeCircleNumber;
    private String terminalType;
    private String processType;
    private String cardType;
    private String amountShaparak;
    private String referenceCode;
    private String depositeFlag;
    private String acceptorCommission;
    private String pspCommission;
    private String pspNetCommission;
    private String shaparakCommission;
    private String terminalCode;
    private String cardNumber;

    private String origTxnInfo;

    private String acceptorNetCommission;
    private String acceptorBankCommission;
    private String businessCategoryCode;

    private String reserve;


    @Override
    public String toString() {
        return "BatchRecord{" +
                "rowNumber='" + rowNumber + '\'' +
                ", pspCode='" + pspCode + '\'' +
                ", acceptorCode='" + acceptorCode + '\'' +
                ", traceCode='" + traceCode + '\'' +
                ", localDate='" + localDate + '\'' +
                ", localTime='" + localTime + '\'' +
                ", reciveDate='" + reciveDate + '\'' +
                ", iban='" + iban + '\'' +
                ", depositeDate='" + depositeDate + '\'' +
                ", depositeType='" + depositeType + '\'' +
                ", depositeCircleNumber='" + depositeCircleNumber + '\'' +
                ", terminalType='" + terminalType + '\'' +
                ", processType='" + processType + '\'' +
                ", cardType='" + cardType + '\'' +
                ", amountShaparak='" + amountShaparak + '\'' +
                ", referenceCode='" + referenceCode + '\'' +
                ", depositeFlag='" + depositeFlag + '\'' +
                ", acceptorCommission='" + acceptorCommission + '\'' +
                ", pspCommission='" + pspCommission + '\'' +
                ", pspNetCommission='" + pspNetCommission + '\'' +
                ", shaparakCommission='" + shaparakCommission + '\'' +
                ", terminalCode='" + terminalCode + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", origTxnInfo='" + origTxnInfo + '\'' +
                ", acceptorNetCommission='" + acceptorNetCommission + '\'' +
                ", acceptorBankCommission='" + acceptorBankCommission + '\'' +
                ", businessCategoryCode='" + businessCategoryCode + '\'' +
                ", reserve='" + reserve + '\'' +
                '}';
    }
}