package com.neemre.btcdcli4j.examples.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.client.BtcdClientImpl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResourceUtils {
	
	public static CloseableHttpClient getHttpProvider() {
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		CloseableHttpClient httpProvider = HttpClients.custom().setConnectionManager(connManager)
				.build();
		return httpProvider;
	}
	
	public static BtcdClient getBtcdProvider() throws BitcoindException, CommunicationException, 
			IOException {
		return new BtcdClientImpl(getHttpProvider(), getNodeConfig());
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