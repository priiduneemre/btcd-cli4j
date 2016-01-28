package com.neemre.btcdcli4j.examples.client;

import java.util.Properties;

import org.apache.http.impl.client.CloseableHttpClient;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.examples.util.ResourceUtils;

/**A list of examples demonstrating the use of <i>bitcoind</i>'s mining RPCs (via the JSON-RPC 
 * API).*/
public class MiningApi {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpProvider = ResourceUtils.getHttpProvider();
		Properties nodeConfig = ResourceUtils.getNodeConfig();
		BtcdClient client = new VerboseBtcdClientImpl(httpProvider, nodeConfig);

		client.getGenerate();
		client.getMiningInfo();
		client.getNetworkHashPs();
		client.getNetworkHashPs(1008);
		client.getNetworkHashPs(2016, 278106);
		client.setGenerate(false);
		client.setGenerate(false, 7);
	}
}