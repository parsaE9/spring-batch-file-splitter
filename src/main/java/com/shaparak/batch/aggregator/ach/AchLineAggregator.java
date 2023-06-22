package com.shaparak.batch.aggregator.ach;

import com.shaparak.batch.dto.ach.AchRecord;
import org.springframework.batch.item.file.transform.LineAggregator;

public class AchLineAggregator implements LineAggregator<AchRecord> {


	@Override
	public String aggregate(AchRecord achRecord) {
		try {
			return achRecord.getCdtr().getNm() + "%" + achRecord.getCdtrAcct().getId().getIBAN() + "%" + achRecord.getPmtId().getInstrId() + "%" +
					achRecord.getIntrBkSttlmAmt() + "%" + achRecord.getPmtId().getEndToEndId() + "%" + achRecord.getPmtId().getTxId();
		} catch (Exception e) {
			throw new RuntimeException("Unable to serialize Record", e);
		}
	}


}