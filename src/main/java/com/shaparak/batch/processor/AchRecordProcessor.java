package com.shaparak.batch.processor;

import com.shaparak.batch.dto.xml.CdtTrfTxInfDto;
import org.springframework.batch.item.ItemProcessor;

public class AchRecordProcessor implements ItemProcessor<CdtTrfTxInfDto, CdtTrfTxInfDto> {

    public static long totalAmount = 0L;


    @Override
    public synchronized CdtTrfTxInfDto process(CdtTrfTxInfDto cdtTrfTxInfDto) throws Exception {
        totalAmount += Long.parseLong(cdtTrfTxInfDto.getIntrBkSttlmAmt());
        return cdtTrfTxInfDto;
    }


}