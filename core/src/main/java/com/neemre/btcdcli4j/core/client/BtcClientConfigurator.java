package com.neemre.btcdcli4j.core.client;

import com.neemre.btcdcli4j.core.common.AgentConfigurator;
import com.neemre.btcdcli4j.core.domain.Block;
import org.apache.http.impl.client.CloseableHttpClient;

public abstract class BtcClientConfigurator extends AgentConfigurator {

    public abstract CloseableHttpClient checkHttpProvider(CloseableHttpClient httpProvider);

    public abstract String checkNodeVersion(Integer encodedVersion);

    public abstract String getNodeVersion();

    public abstract boolean checkNodeHealth(Block bestBlock);


}
