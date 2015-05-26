package com.neemre.btcdcli4j.examples.client;

import java.util.Properties;

import org.apache.http.impl.client.CloseableHttpClient;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.client.BtcdClientImpl;
import com.neemre.btcdcli4j.examples.util.ResourceUtils;

/**A list of examples demonstrating the use of <i>bitcoind</i>'s mining RPCs (via the JSON-RPC 
 * API).*/
public class MiningApi {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpProvider = ResourceUtils.getHttpProvider();
		Properties nodeConfig = ResourceUtils.getNodeConfig();
		BtcdClient client = new VerboseBtcdClient(new BtcdClientImpl(httpProvider, nodeConfig));
		
		client.getGenerate();
		client.getMiningInfo();
		client.setGenerate(false);
		client.setGenerate(false, 7);
	}
}