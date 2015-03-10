package examples;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

import org.apache.http.client.HttpClient;

import com.neemre.btcdcli4j.domain.OutputOverview;
import com.neemre.btcdcli4j.domain.Output;
import com.neemre.btcdcli4j.domain.enums.SigHashTypes;

import examples.api.ApiCalls;
import examples.util.ExampleUtils;

public class ApiUsage {

	/**A list of examples demonstrating the use of all <i>bitcoind</i> API commands currently 
	 * supported by btcd-cli4j (as of 0.2.8). Calling any of the methods below will cause a
	 * short overview (<i>i.e.</i> of the results of the operation) to be written to 
	 * {@code stdout}.*/
	public static void main(String[] args) throws Exception {
		HttpClient httpProvider = ExampleUtils.getHttpProvider();
		Properties nodeConfig = ExampleUtils.getNodeConfig();
		ApiCalls supportedCalls = new ApiCalls(httpProvider, nodeConfig);



		//		supportedCalls.getHashesPerSec();










		//		supportedCalls.sendToAddress("msrHoyN5Jw1EH7saGxMqJtTKt6qhmyPZMF", new BigDecimal("0.0005"));
		//		supportedCalls.sendToAddress("n3y8BpckkDDGMtSq7d2Yx46EYenyUit3Jc", new BigDecimal("0.0035"), 
		//				"Sample transaction: a payment of 0.0035 BTC to 'supplierB' for services rendered.");
		//		supportedCalls.sendToAddress("mu9YMgJzJkoA6ZwhCFDkTjRfXF2BakTGw1", new BigDecimal("0.0045"), 
		//				"Sample transaction: a payment of 0.0045 BTC to 'supplierC' for services rendered.", 
		//				"supplierC");
		//		supportedCalls.setAccount("1NRpYDf2GdAL4yLZEAww8uUSEGM7Df6KKc", "aardvark");


		//		supportedCalls.setTxFee(new BigDecimal("0.00004900"));
		//		supportedCalls.signMessage("mixnciYh9dar2CwywYYZTHZqS4kyZWkvoV", "I like liquorice.");



		//		supportedCalls.walletLock();
		//		supportedCalls.walletPassphrase("strawberry", Defaults.WALLET_AUTH_TIMEOUT);
		//		supportedCalls.walletPassphraseChange("strawberry", "raspberry");
	}
}