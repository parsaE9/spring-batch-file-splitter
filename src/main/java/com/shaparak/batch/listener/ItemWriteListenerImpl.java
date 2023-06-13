package com.shaparak.batch.listener;

import com.shaparak.batch.dto.BatchRecord;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class ItemWriteListenerImpl implements ItemWriteListener<BatchRecord> {

    public static long totalPspAmount = 0L;
    public static long totalCommission = 0L;


    @Override
    public synchronized void beforeWrite(List<? extends BatchRecord> records) {
        for (BatchRecord batchRecord : records) {
            totalPspAmount += Long.parseLong(batchRecord.getAmountShaparak());
            totalCommission += Long.parseLong(batchRecord.getAcceptorCommission().substring(1));
        }
    }


    @Override
    public void afterWrite(List<? extends BatchRecord> records) {

    }

    @Override
    public void onWriteError(Exception exception, List<? extends BatchRecord> records) {

    }


}