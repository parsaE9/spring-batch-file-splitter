package com.shaparak.batch.processor;

import com.shaparak.batch.dto.ach.AchRecord;
import org.springframework.batch.item.ItemProcessor;

public class AchRecordProcessor implements ItemProcessor<AchRecord, AchRecord> {


    @Override
    public synchronized AchRecord process(AchRecord achRecord) throws Exception {
        return achRecord;
    }


}