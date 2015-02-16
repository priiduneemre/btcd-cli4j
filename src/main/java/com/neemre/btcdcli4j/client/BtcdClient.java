package com.neemre.btcdcli4j.client;

import java.math.BigDecimal;

import com.neemre.btcdcli4j.domain.Info;
import com.neemre.btcdcli4j.domain.MiningInfo;

public interface BtcdClient {
	
	Info getInfo();
	
	MiningInfo getMiningInfo();
	
	BigDecimal getDifficulty();
	
	Boolean getGenerate();
	
	void setGenerate(Boolean isGenerate);
	
	void setGenerate(Boolean isGenerate, Integer processorCount);
	
	Integer getHashesPerSec();
}