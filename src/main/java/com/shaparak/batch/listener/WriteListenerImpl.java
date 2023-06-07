package com.shaparak.batch.listener;

import com.shaparak.batch.dto.Record;
import org.springframework.batch.core.ItemWriteListener;
import java.util.List;

public class  WriteListenerImpl implements ItemWriteListener<Record> {


    @Override
    public void beforeWrite(List<? extends Record> items) {
        System.out.println("hi");
    }

    @Override
    public void afterWrite(List<? extends Record> items) {

    }

    @Override
    public void onWriteError(Exception exception, List<? extends Record> items) {

    }
}