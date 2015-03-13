package examples.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public final class ResourceUtils {
	
	private ResourceUtils() {}
	
	public static CloseableHttpClient getHttpProvider() {
		CloseableHttpClient httpProvider = HttpClients.createDefault();
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