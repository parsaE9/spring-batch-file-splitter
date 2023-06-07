package com.shaparak.batch.processor;

import com.shaparak.batch.dto.Record;
import org.springframework.batch.item.ItemProcessor;

public class RecordProcessor implements ItemProcessor<Record, Record> {


    @Override
    public Record process(Record record) throws Exception {
        return record;
    }


}