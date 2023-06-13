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
@XmlRootElement(name="CdtrAcct")
@XmlAccessorType(XmlAccessType.FIELD)
public class CdtrAcctDto {


    private CdtrAcctIdDto Id;


}