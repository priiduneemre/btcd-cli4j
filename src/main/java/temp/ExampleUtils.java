package temp;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

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
}