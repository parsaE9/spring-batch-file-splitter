package com.shaparak.batch.dto.ach;

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
public class AchRecord {

    private PmtIdDto PmtId = new PmtIdDto();

    private String IntrBkSttlmAmt;

    private CdtrDto Cdtr = new CdtrDto();

    private CdtrAcctDto CdtrAcct = new CdtrAcctDto();

    private RmtInfDto RmtInf = new RmtInfDto();


    @Override
    public String toString() {
        return "AchRecord{" +
                "PmtId=" + PmtId +
                ", IntrBkSttlmAmt='" + IntrBkSttlmAmt + '\'' +
                ", Cdtr=" + Cdtr +
                ", CdtrAcct=" + CdtrAcct +
                ", RmtInf=" + RmtInf +
                '}';
    }

}