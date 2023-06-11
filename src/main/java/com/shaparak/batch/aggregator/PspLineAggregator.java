package com.shaparak.batch.aggregator;

import com.shaparak.batch.dto.Record;
import org.springframework.batch.item.file.transform.LineAggregator;

public class PspLineAggregator implements LineAggregator<Record> {


	@Override
	public String aggregate(Record record) {
		try {
			return record.getRowNumber() + "| " + record.getPspCode() + "|" + record.getAcceptorCode() + "| " + record.getTraceCode() + "| " + record.getLocalDate() + "| " + record.getLocalTime()
					+ "| " + record.getReciveDate() + "| " + record.getIban() + "| " + record.getDepositeDate() + "| " + record.getDepositeType() + "| " + record.getDepositeCircleNumber()
					+ "| " + record.getTerminalType() + "| " + record.getProcessType() + "| " + record.getCardType() + "| " + record.getAmountShaparak() + "| " + record.getReferenceCode()
					+ "| " + record.getDepositeFlag() + "| " + record.getAcceptorCommission() + "| " + record.getPspCommission() + "| " + record.getPspNetCommission() + "| " + record.getTerminalCode();
		} catch (Exception e) {
			throw new RuntimeException("Unable to serialize Record", e);
		}
	}


}