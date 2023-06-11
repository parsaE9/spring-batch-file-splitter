package com.shaparak.batch.listener;

import com.shaparak.batch.dto.Record;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ItemWriteListenerImpl implements ItemWriteListener<Record> {

    public static long totalPspAmount = 0L;
    public static long totalCommission = 0L;


    @Override
    public synchronized void beforeWrite(List<? extends Record> records) {
        for (Record record: records) {
            totalPspAmount += Long.parseLong(record.getAmountShaparak());


            totalCommission += Long.parseLong(record.getAcceptorCommission().substring(1));
        }
    }


    @Override
    public void afterWrite(List<? extends Record> records) {

    }

    @Override
    public void onWriteError(Exception exception, List<? extends Record> records) {

    }


}