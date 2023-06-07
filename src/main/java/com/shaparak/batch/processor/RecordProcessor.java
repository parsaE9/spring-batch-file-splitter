package com.shaparak.batch.processor;

import com.shaparak.batch.dto.Record;
import org.springframework.batch.item.ItemProcessor;

public class RecordProcessor implements ItemProcessor<Record, Record> {


    @Override
    public Record process(Record record) throws Exception {
        if (record.getCardNumber().substring(0, 3).equals("989"))
            System.out.println("hey");
        return record;
    }


}