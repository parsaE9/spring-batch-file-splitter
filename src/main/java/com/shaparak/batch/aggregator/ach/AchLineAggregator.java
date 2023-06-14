package com.shaparak.batch.aggregator.ach;

import com.shaparak.batch.dto.BatchRecord;
import com.shaparak.batch.dto.xml.CdtTrfTxInfDto;
import org.springframework.batch.item.file.transform.LineAggregator;

public class AchLineAggregator implements LineAggregator<CdtTrfTxInfDto> {


	@Override
	public String aggregate(CdtTrfTxInfDto cdtTrfTxInfDto) {
		try {
			return cdtTrfTxInfDto.getCdtr().getNm() + "%" + cdtTrfTxInfDto.getCdtrAcct().getId().getIBAN() + "%" + cdtTrfTxInfDto.getPmtId().getInstrId() + "%" +
					cdtTrfTxInfDto.getIntrBkSttlmAmt() + "%" + cdtTrfTxInfDto.getPmtId().getEndToEndId() + "%" + cdtTrfTxInfDto.getPmtId().getTxId();
		} catch (Exception e) {
			throw new RuntimeException("Unable to serialize Record", e);
		}
	}


}