package com.shaparak.batch.processor;

import com.shaparak.batch.dto.BatchRecord;
import org.springframework.batch.item.ItemProcessor;

public class BatchRecordProcessor implements ItemProcessor<BatchRecord, BatchRecord> {


    @Override
    public BatchRecord process(BatchRecord batchRecord) throws Exception {
        if (batchRecord.getOrigTxnInfo() == null)
            batchRecord.setOrigTxnInfo("");
        return batchRecord;
    }


}