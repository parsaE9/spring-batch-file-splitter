package com.shaparak.batch.dto.xml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name="CdtTrfTxInf")
@XmlAccessorType(XmlAccessType.FIELD)
public class CdtTrfTxInf {

    private PmtIdDto PmtId;

    private String IntrBkSttlmAmt;

    private CdtrDto Cdtr;
//
    private CdtrAcctDto CdtrAcct;
//
    private RmtInfDto RmtInf;


}