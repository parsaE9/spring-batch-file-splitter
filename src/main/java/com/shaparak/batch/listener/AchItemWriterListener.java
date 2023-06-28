package com.shaparak.batch.listener;

import com.shaparak.batch.dto.ach.AchRecord;
import com.shaparak.batch.dto.batch.BatchRecord;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class AchItemWriterListener implements ItemWriteListener<AchRecord> {

    public static long totalAmount = 0L;


    @Override
    public synchronized void beforeWrite(List<? extends AchRecord> records) {
        for (AchRecord achRecord : records) {
            totalAmount += Long.parseLong(achRecord.getIntrBkSttlmAmt());
        }
    }


    @Override
    public void afterWrite(List<? extends AchRecord> records) {

    }

    @Override
    public void onWriteError(Exception exception, List<? extends AchRecord> records) {

    }


}