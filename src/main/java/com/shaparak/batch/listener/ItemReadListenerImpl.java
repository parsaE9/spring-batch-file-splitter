package com.shaparak.batch.listener;

import com.shaparak.batch.dto.batch.BatchRecord;
import org.springframework.batch.core.ItemReadListener;

public class ItemReadListenerImpl implements ItemReadListener<BatchRecord> {



    @Override
    public void beforeRead() {

    }

    @Override
    public void afterRead(BatchRecord item) {

    }

    @Override
    public void onReadError(Exception ex) {

    }

}
