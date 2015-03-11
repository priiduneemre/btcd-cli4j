package examples.api;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.client.HttpClient;

import examples.util.ExampleUtils;

/**A list of examples demonstrating the use of <i>bitcoind</i>'s 'Block Chain API' commands 
 * currently supported by btcd-cli4j. Calling any of the methods below will cause a short overview 
 * (<i>i.e.</i> of the results of the operation) to be written to {@code stdout}.*/
public class BlockChainApi {
 
	public static void main(String[] args) throws IOException {
		HttpClient httpProvider = ExampleUtils.getHttpProvider();
		Properties nodeConfig = ExampleUtils.getNodeConfig();
		ApiCalls supportedCalls = new ApiCalls(httpProvider, nodeConfig);
		
//		supportedCalls.getBestBlockHash();
//		supportedCalls.getBlock("00000000000000e8cf3d4fab91c642f5d5bb13339613aa915a42a7f1c91ab5ba");
//		supportedCalls.getBlock("00000000000000e8cf3d4fab91c642f5d5bb13339613aa915a42a7f1c91ab5ba",
//				true);
//		supportedCalls.getBlockCount();
//		supportedCalls.getBlockHash(345168);
//		supportedCalls.getDifficulty();
//		supportedCalls.getMemPoolInfo();
	}
}