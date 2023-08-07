package com.shaparak.batch.listener;

import com.shaparak.batch.dto.ach.AchRecord;
import com.shaparak.batch.dto.batch.BatchRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class AchItemWriterListener implements ItemWriteListener<AchRecord> {

    Logger logger = LoggerFactory.getLogger(AchItemWriterListener.class);

    public static long totalAmount = 0L;


    @Override
    public synchronized void beforeWrite(List<? extends AchRecord> records) {
        for (AchRecord achRecord : records) {
            try {
                totalAmount += Long.parseLong(achRecord.getIntrBkSttlmAmt());
            } catch (Exception e) {
                logger.error("error in AchItemWriterListener Begin");
                System.out.println("error message: " + e.getMessage());
                System.out.println("IntrBkSttlmAmt: " + achRecord.getIntrBkSttlmAmt());
                System.out.println("Ach Record: " + achRecord.toString());
                logger.error("error in AchItemWriterListener end");
            }
        }
    }


    @Override
    public void afterWrite(List<? extends AchRecord> records) {

    }

    @Override
    public void onWriteError(Exception exception, List<? extends AchRecord> records) {

    }


}