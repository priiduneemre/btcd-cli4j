package com.neemre.btcdcli4j.client;

import java.math.BigDecimal;

import com.neemre.btcdcli4j.domain.Info;

public interface BtcdClient {
	
	Info getInfo();
	
	BigDecimal getDifficulty();
	
	Boolean getGenerate();
}