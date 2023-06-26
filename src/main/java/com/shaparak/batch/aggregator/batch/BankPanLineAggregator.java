package com.shaparak.batch.aggregator.batch;

import com.shaparak.batch.dto.batch.BatchRecord;
import org.springframework.batch.item.file.transform.LineAggregator;

public class BankPanLineAggregator implements LineAggregator<BatchRecord> {


	@Override
	public String aggregate(BatchRecord batchRecord) {
		try {
			return String.format(  "|" + batchRecord.getPspCode() + "|%-15s" + "|" + "%s|" + batchRecord.getLocalDate() + "|" + batchRecord.getLocalTime()
					+ "|" + batchRecord.getReciveDate() + "|%-34s" + "|" + batchRecord.getDepositeDate() + "|" + batchRecord.getDepositeType() + "|" + batchRecord.getDepositeCircleNumber()
					+ "|" + batchRecord.getTerminalType() + "|" + batchRecord.getProcessType() + "|" + batchRecord.getCardType() + "|" + batchRecord.getAmountShaparak() + "|" + batchRecord.getReferenceCode()
					+ "|" + batchRecord.getDepositeFlag() + "|" + batchRecord.getTerminalCode() + "|" + batchRecord.getOrigTxnInfo() + "|" + batchRecord.getCardNumber()
			, batchRecord.getAcceptorCode(), batchRecord.getTraceCode().substring(batchRecord.getTraceCode().length() - 6), batchRecord.getIban());
		} catch (Exception e) {
			throw new RuntimeException("Unable to serialize Record", e);
		}
	}


}