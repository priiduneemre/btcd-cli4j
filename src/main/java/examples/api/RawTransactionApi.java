package examples.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

import org.apache.http.client.HttpClient;

import com.neemre.btcdcli4j.domain.Output;
import com.neemre.btcdcli4j.domain.OutputOverview;
import com.neemre.btcdcli4j.domain.enums.SigHashTypes;

import examples.util.ExampleUtils;

/**A list of examples demonstrating the use of <i>bitcoind</i>'s 'Raw Transaction API' commands 
 * currently supported by btcd-cli4j. Calling any of the methods below will cause a short overview 
 * (<i>i.e.</i> of the results of the operation) to be written to {@code stdout}.*/
public class RawTransactionApi {

	public static void main(String[] args) throws IOException {
		HttpClient httpProvider = ExampleUtils.getHttpProvider();
		Properties nodeConfig = ExampleUtils.getNodeConfig();
		ApiCalls supportedCalls = new ApiCalls(httpProvider, nodeConfig);
		
		supportedCalls.createRawTransaction(Arrays.asList(new OutputOverview[]{ 
				new OutputOverview("656fd6e21867bfda44d33d62e464f7994ebcbf8e7de329c107aa6e856fe45198", 0)}), 
				new HashMap<String, BigDecimal>(){{put("mrenwZx2eMy1F7KPuBfyDdHqwkF4VcgSNX", 
						new BigDecimal("0,0035"));}});
		supportedCalls.decodeRawTransaction("");
		supportedCalls.decodeScript("");
		supportedCalls.getRawTransaction("656fd6e21867bfda44d33d62e464f7994ebcbf8e7de329c107aa6e856fe45198");
		supportedCalls.getRawTransaction("656fd6e21867bfda44d33d62e464f7994ebcbf8e7de329c107aa6e856fe45198", 1);
		supportedCalls.sendRawTransaction("");
		supportedCalls.sendRawTransaction("", false);
		supportedCalls.signRawTransaction("");
		supportedCalls.signRawTransaction("", Arrays.asList(new Output[]{}));
		supportedCalls.signRawTransaction("", Arrays.asList(new Output[]{}), Arrays.asList(new String[]{}));
		supportedCalls.signRawTransaction("", Arrays.asList(new Output[]{}), Arrays.asList(new String[]{}), 
				SigHashTypes.ALL.getName());
	}
}