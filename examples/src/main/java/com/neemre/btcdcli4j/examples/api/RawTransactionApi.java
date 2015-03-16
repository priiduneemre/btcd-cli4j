package com.neemre.btcdcli4j.examples.api;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

import org.apache.http.impl.client.CloseableHttpClient;

import com.neemre.btcdcli4j.core.domain.Output;
import com.neemre.btcdcli4j.core.domain.OutputOverview;
import com.neemre.btcdcli4j.core.domain.enums.SigHashTypes;
import com.neemre.btcdcli4j.examples.util.ResourceUtils;

/**A list of examples demonstrating the use of <i>bitcoind</i>'s 'Raw Transaction API' commands 
 * currently supported by btcd-cli4j. Calling any of the methods below will cause a short overview 
 * (<i>i.e.</i> of the results of the operation) to be written to {@code stdout}.*/
public class RawTransactionApi {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpProvider = ResourceUtils.getHttpProvider();
		Properties nodeConfig = ResourceUtils.getNodeConfig();
		ApiCalls supportedCalls = new ApiCalls(httpProvider, nodeConfig);
		
		/*supportedCalls.createRawTransaction(Arrays.asList(new OutputOverview[]{ 
				new OutputOverview("656fd6e21867bfda44d33d62e464f7994ebcbf8e7de329c107aa6e856fe45198", 0)}), 
				new HashMap<String, BigDecimal>(){{put("mrenwZx2eMy1F7KPuBfyDdHqwkF4VcgSNX", 
						new BigDecimal("0.0035"));}});
		supportedCalls.decodeRawTransaction("");*/
//		supportedCalls.decodeScript("5321021a971b74e19dbfa8d72fba6900bb49f6301cc8b7f86d1a5dd59397d3bdce887821"
//				+ "0385fdc5e1adec9636cb1930e653da92024d3d26834306b6785a0b206995ccb7c121028d60b26dfdb6c92dac52"
//				+ "958106b0477a661f3eb562bde36448e37231e252e9a3410450fed2184797eef3ce649df3adccb19adc02916c3d" 
//				+ "565cb02c50b2aaabd9394eb2bd024692b57602c95a86a9c3dabbbaa163db340cbd58506c11ec9bf757c88854ae");
		/*supportedCalls.getRawTransaction("656fd6e21867bfda44d33d62e464f7994ebcbf8e7de329c107aa6e856fe45198");
		supportedCalls.getRawTransaction("656fd6e21867bfda44d33d62e464f7994ebcbf8e7de329c107aa6e856fe45198", 1);
		supportedCalls.sendRawTransaction("");
		supportedCalls.sendRawTransaction("", false);
		supportedCalls.signRawTransaction("");
		supportedCalls.signRawTransaction("", Arrays.asList(new Output[]{}));
		supportedCalls.signRawTransaction("", Arrays.asList(new Output[]{}), Arrays.asList(new String[]{}));
		supportedCalls.signRawTransaction("", Arrays.asList(new Output[]{}), Arrays.asList(new String[]{}), 
				SigHashTypes.ALL.getName());*/
	}
}