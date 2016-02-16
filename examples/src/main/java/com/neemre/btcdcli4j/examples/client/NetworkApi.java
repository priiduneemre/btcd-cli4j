package com.neemre.btcdcli4j.examples.client;

import java.util.Properties;

import org.apache.http.impl.client.CloseableHttpClient;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.domain.enums.PeerControls;
import com.neemre.btcdcli4j.examples.util.ResourceUtils;

/**A list of examples demonstrating the use of <i>bitcoind</i>'s network RPCs (via the JSON-RPC 
 * API).*/
public class NetworkApi {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpProvider = ResourceUtils.getHttpProvider();
		Properties nodeConfig = ResourceUtils.getNodeConfig();
		BtcdClient client = new VerboseBtcdClientImpl(httpProvider, nodeConfig);

		client.addNode("52.4.156.236:18333", PeerControls.ADD.getName());
		client.getAddedNodeInfo(true);
		client.getAddedNodeInfo(true, "52.4.156.236:18333");
		client.getConnectionCount();
		client.getNetTotals();
		client.getNetworkInfo();
		client.getPeerInfo();
		client.ping();
	}
}