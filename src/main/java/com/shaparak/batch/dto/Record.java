package com.shaparak.batch.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Record {

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
    private String extraData1;
    private String extraData2;
    private String extraData3;
    private String extraData4;

    // TODO: we assign origTxnInfo = "" because in the input file, there is no origTxnInfo param.
    private String origTxnInfo = "";

}