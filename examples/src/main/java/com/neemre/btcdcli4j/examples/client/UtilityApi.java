package com.neemre.btcdcli4j.examples.client;

import java.util.Arrays;
import java.util.Properties;

import org.apache.http.impl.client.CloseableHttpClient;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.examples.util.ResourceUtils;

/**A list of examples demonstrating the use of <i>bitcoind</i>'s utility RPCs (via the JSON-RPC 
 * API).*/
public class UtilityApi {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpProvider = ResourceUtils.getHttpProvider();
		Properties nodeConfig = ResourceUtils.getNodeConfig();
		BtcdClient client = new VerboseBtcdClientImpl(httpProvider, nodeConfig);
		
		client.createMultiSig(2, Arrays.asList(new String[]{"mhgPHX4kmzV8NgfoUtfhUwWEMZHQEZeMbH",
				"mmfPHrvaoqqQLGkStcYgrbgiBFTvsjFzgx", "mxPop5NWu8ok5wbGv46wsASPKyC7yKYix3"}));
		client.estimateFee(10);
		client.validateAddress("2MyVxxgNBk5zHRPRY2iVjGRJHYZEp1pMCSq");
		client.verifyMessage("mixnciYh9dar2CwywYYZTHZqS4kyZWkvoV", "INXVUmzGIh+VnkiFAVgNiw1t35oSxxv"
				+ "wc6e53hrjMtBdVm/GoTyDY+TelMV64pVrdMY0s9fW5M1bWZl+kcnCQ0g=", "I like liquorice.");
	}
}