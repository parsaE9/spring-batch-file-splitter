package com.shaparak.batch.listener;

import com.shaparak.batch.dto.batch.BatchRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

public class BatchItemWriterListener implements ItemWriteListener<BatchRecord> {

    Logger logger = LoggerFactory.getLogger(BatchItemWriterListener.class);

    public static long totalPspAmount = 0L;
    public static long totalCommission = 0L;


    @Override
    public synchronized void beforeWrite(List<? extends BatchRecord> records) {
        for (BatchRecord batchRecord : records) {
            try {
                totalPspAmount += Long.parseLong(batchRecord.getAmountShaparak());
                totalCommission += Long.parseLong(batchRecord.getAcceptorCommission().substring(1));
            } catch (Exception e) {
                logger.error("error in BatchItemWriterListener Begin");
                System.out.println(e.getMessage());
                System.out.println("amountShaparak: " + batchRecord.getAmountShaparak());
                System.out.println("acceptorCommission: " + batchRecord.getAcceptorCommission());
                batchRecord.toString();
                logger.error("error in BatchItemWriterListener end");
            }
        }
    }


    @Override
    public void afterWrite(List<? extends BatchRecord> records) {

    }

    @Override
    public void onWriteError(Exception exception, List<? extends BatchRecord> records) {

    }


}