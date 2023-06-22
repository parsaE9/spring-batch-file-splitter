package com.shaparak.batch.processor;

import com.shaparak.batch.dto.ach.AchRecord;
import org.springframework.batch.item.ItemProcessor;

public class AchRecordProcessor implements ItemProcessor<AchRecord, AchRecord> {

    public static long totalAmount = 0L;


    @Override
    public synchronized AchRecord process(AchRecord achRecord) throws Exception {
        totalAmount += Long.parseLong(achRecord.getIntrBkSttlmAmt());
        return achRecord;
    }


}