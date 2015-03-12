package examples.api;

import java.util.Properties;

import org.apache.http.impl.client.CloseableHttpClient;

import examples.util.ExampleUtils;

/**A list of examples demonstrating the use of <i>bitcoind</i>'s 'Mining API' commands 
 * currently supported by btcd-cli4j. Calling any of the methods below will cause a short overview 
 * (<i>i.e.</i> of the results of the operation) to be written to {@code stdout}.*/
public class MiningApi {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpProvider = ExampleUtils.getHttpProvider();
		Properties nodeConfig = ExampleUtils.getNodeConfig();
		ApiCalls supportedCalls = new ApiCalls(httpProvider, nodeConfig);
		
//		supportedCalls.getGenerate();
//		supportedCalls.getMiningInfo();
//		supportedCalls.setGenerate(false);
//		supportedCalls.setGenerate(false, 7);
	}
}