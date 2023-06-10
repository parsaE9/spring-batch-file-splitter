package com.shaparak.batch.listener;

import com.shaparak.batch.dto.Record;
import org.springframework.batch.core.ItemReadListener;

import java.util.concurrent.atomic.AtomicInteger;

public class ItemReadListenerImpl implements ItemReadListener<Record> {



    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(Record item) {

    }

    @Override
    public void onReadError(Exception ex) {

    }

}
