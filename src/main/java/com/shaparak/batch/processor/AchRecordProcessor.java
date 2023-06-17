package com.shaparak.batch.processor;

import com.shaparak.batch.dto.xml.CdtTrfTxInfDto;
import org.springframework.batch.item.ItemProcessor;

public class AchRecordProcessor implements ItemProcessor<CdtTrfTxInfDto, CdtTrfTxInfDto> {


    @Override
    public CdtTrfTxInfDto process(CdtTrfTxInfDto cdtTrfTxInfDto) throws Exception {
//        System.out.println("ACH");
        return cdtTrfTxInfDto;
    }


}