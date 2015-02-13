package com.neemre.btcd.client;

import java.math.BigDecimal;

import com.neemre.btcd.domain.Info;

public interface BtcdClient {
	
	Info getInfo();
	
	BigDecimal getDifficulty();
}