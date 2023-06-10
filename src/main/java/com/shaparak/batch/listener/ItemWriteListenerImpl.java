package com.shaparak.batch.listener;

import com.shaparak.batch.dto.Record;
import org.springframework.batch.core.ItemWriteListener;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ItemWriteListenerImpl implements ItemWriteListener<Record> {

    public AtomicInteger rowNumber = new AtomicInteger(0);


    @Override
    public synchronized void beforeWrite(List<? extends Record> items) {
        for (Record record: items)
            if (record.getPspCode().endsWith("022")){
                record.setRowNumber(rowNumber.incrementAndGet() + "");
            }
    }

    @Override
    public void afterWrite(List<? extends Record> items) {

    }

    @Override
    public void onWriteError(Exception exception, List<? extends Record> items) {

    }


}