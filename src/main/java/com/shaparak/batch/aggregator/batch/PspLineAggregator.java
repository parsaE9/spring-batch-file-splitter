package com.shaparak.batch.aggregator.batch;

import com.shaparak.batch.dto.batch.BatchRecord;
import org.springframework.batch.item.file.transform.LineAggregator;

public class PspLineAggregator implements LineAggregator<BatchRecord> {


    @Override
    public String aggregate(BatchRecord batchRecord) {
        try {
            return String.format("|" + batchRecord.getPspCode() + "|%-15s" + "|" + batchRecord.getTraceCode() + "|" + batchRecord.getLocalDate() + "|" + batchRecord.getLocalTime()
                            + "|" + batchRecord.getReciveDate() + "|%-34s" + "|" + batchRecord.getDepositeDate() + "|" + batchRecord.getDepositeType() + "|" + batchRecord.getDepositeCircleNumber()
                            + "|" + batchRecord.getTerminalType() + "|" + batchRecord.getProcessType() + "|" + batchRecord.getCardType() + "|" + batchRecord.getAmountShaparak() + "|" + batchRecord.getReferenceCode()
                            + "|" + batchRecord.getDepositeFlag() + "|" + batchRecord.getAcceptorCommission() + "|" + batchRecord.getPspCommission() + "|" + batchRecord.getPspNetCommission() + "|" + batchRecord.getTerminalCode()
                            + "|" + batchRecord.getOrigTxnInfo() + "|" + batchRecord.getAcceptorNetCommission() + "|" + batchRecord.getAcceptorBankCommission()
                            + "|" + batchRecord.getBusinessCategoryCode() + "|" + batchRecord.getReserve()
                    , batchRecord.getAcceptorCode(), batchRecord.getIban());
        } catch (Exception e) {
            throw new RuntimeException("Unable to serialize Record", e);
        }
    }


}