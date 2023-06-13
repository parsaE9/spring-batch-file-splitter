package com.shaparak.batch.aggregator;

import com.shaparak.batch.dto.BatchRecord;
import org.springframework.batch.item.file.transform.LineAggregator;

public class BankLineAggregator implements LineAggregator<BatchRecord> {


	@Override
	public String aggregate(BatchRecord batchRecord) {
		try {
			return  "| " + batchRecord.getPspCode() + "|" + batchRecord.getAcceptorCode() + "| " + batchRecord.getTraceCode() + "| " + batchRecord.getLocalDate() + "| " + batchRecord.getLocalTime()
					+ "| " + batchRecord.getReciveDate() + "| " + batchRecord.getIban() + "| " + batchRecord.getDepositeDate() + "| " + batchRecord.getDepositeType() + "| " + batchRecord.getDepositeCircleNumber()
					+ "| " + batchRecord.getTerminalType() + "| " + batchRecord.getProcessType() + "| " + batchRecord.getCardType() + "| " + batchRecord.getAmountShaparak() + "| " + batchRecord.getReferenceCode()
					+ "| " + batchRecord.getDepositeFlag() + "| " + batchRecord.getTerminalCode() + "| " + batchRecord.getOrigTxnInfo();
		} catch (Exception e) {
			throw new RuntimeException("Unable to serialize Record", e);
		}
	}


}