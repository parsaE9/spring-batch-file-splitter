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
@XmlRootElement(name="PmtId")
@XmlAccessorType(XmlAccessType.FIELD)
public class PmtIdDto {


    private String InstrId;
    private String EndToEndId;
    private String TxId;


}
