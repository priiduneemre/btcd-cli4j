package examples.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.neemre.btcdcli4j.util.CollectionUtils;

public class ExampleUtils {
	
	public static HttpClient getHttpProvider() {
		HttpClient httpProvider = HttpClientBuilder.create().build();
		return httpProvider;
	}
	
	public static Properties getNodeConfig() throws IOException {
		Properties nodeConfig = new Properties();
		InputStream inputStream = new BufferedInputStream(new FileInputStream(
				"src/main/resources/node_config.properties"));
		nodeConfig.load(inputStream);
		inputStream.close();
		return nodeConfig;
	}
	
	public static void printResult(String methodName, String[] paramNames, Object[] paramValues,
			Object result) {
		List<Object> printables = new ArrayList<Object>();
		printables.add(methodName);
		if(!(paramNames == null || paramValues == null)) {
			printables.addAll(CollectionUtils.mergeInterlaced(Arrays.asList(paramNames), 
					Arrays.asList(paramValues)));
		}
		printables.add(result);
		System.out.printf("'bitcoind' response for API call '%s(" + StringUtils.repeat("%s=%s, ", 
				(printables.size() - 2)/2).replaceAll(", $", "") + ")' was: '%s'\n", 
				printables.toArray());
	}
}