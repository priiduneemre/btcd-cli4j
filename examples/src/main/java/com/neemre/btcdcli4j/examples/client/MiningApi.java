package com.neemre.btcdcli4j.examples.client;

import java.math.BigDecimal;
import java.util.HashMap;
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
		client.prioritiseTransaction("4ed1f44942405610af66c01f417d2adb0531bff42dba0dac98864c0ff09d220e",
				new BigDecimal("189233000.51"), 50000000L);
		client.setGenerate(false);
		client.setGenerate(false, 7);
		client.submitBlock("0400000056ed3621936549216e1cd6b82b07abe29138963c6afc7f64240b00000000000057d"
				+ "6991e2a3de157eeee27ac769f5271ad9824d71e0cdcee7781a73dd3e950b27f74d956ffff001d093e075"
				+ "d0101000000010000000000000000000000000000000000000000000000000000000000000000fffffff"
				+ "f29032a080b1c4b6e434d696e6572544553544e455422220f056a36b48a56d974bf0200000000c77d0d0"
				+ "0ffffffff0140be4025000000001976a91470568a7d32ec60b5e27718c4b9d21bebe9cc178988ac00000"
				+ "000");
		client.submitBlock("0400000056ed3621936549216e1cd6b82b07abe29138963c6afc7f64240b00000000000057d"
				+ "6991e2a3de157eeee27ac769f5271ad9824d71e0cdcee7781a73dd3e950b27f74d956ffff001d093e075"
				+ "d0101000000010000000000000000000000000000000000000000000000000000000000000000fffffff"
				+ "f29032a080b1c4b6e434d696e6572544553544e455422220f056a36b48a56d974bf0200000000c77d0d0"
				+ "0ffffffff0140be4025000000001976a91470568a7d32ec60b5e27718c4b9d21bebe9cc178988ac00000"
				+ "000", new HashMap<String, Object>() {{put("workid", "3f"); put("prevworkid", 93);}});
	}
}