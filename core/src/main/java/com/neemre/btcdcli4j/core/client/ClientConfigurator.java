package com.neemre.btcdcli4j.core.client;

import java.util.List;
import java.util.Properties;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neemre.btcdcli4j.core.NodeProperties;
import com.neemre.btcdcli4j.core.common.Defaults;
import com.neemre.btcdcli4j.core.common.Errors;
import com.neemre.btcdcli4j.core.util.CollectionUtils;
import com.neemre.btcdcli4j.core.util.StringUtils;

public class ClientConfigurator {

	private static final Logger LOG = LoggerFactory.getLogger(ClientConfigurator.class);
	
	
	public Properties checkNodeConfig(Properties nodeConfig) {
		for (NodeProperties property : NodeProperties.values()) {
			if (nodeConfig.getProperty(property.getKey()) == null) {
				LOG.warn("-- checkNodeConfig(..): node property '{}' not set; reverting to "
						+ "default value '{}'", property.getKey(), property.getDefaultValue());
				nodeConfig.setProperty(property.getKey(), property.getDefaultValue());
			}
		}
		return nodeConfig;
	}
	
	public Properties checkNodeConfig(String rpcProtocol, String rpcHost, Integer rpcPort, 
			String rpcUser, String rpcPassword, String httpAuthScheme) {
		Properties nodeConfig = toNodeConfig(rpcProtocol, rpcHost, rpcPort, rpcUser, rpcPassword,
				httpAuthScheme);
		return checkNodeConfig(nodeConfig);
	}
	
	public CloseableHttpClient checkHttpProvider(CloseableHttpClient httpProvider) {
		if (httpProvider == null) {
			httpProvider = getDefaultHttpProvider();
			LOG.warn("-- checkHttpProvider(..): no preconfigured HTTP provider detected; reverting "
					+ "to library default settings");
		}
		return httpProvider;
	}
	
	public String checkNodeVersion(Integer encodedVersion) {
		String nodeVersion = decodeNodeVersion(encodedVersion);
		for (String supportedVersion : Defaults.NODE_VERSIONS) {
			if (nodeVersion.equals(supportedVersion)) {
				return nodeVersion;
			}
		}
		LOG.warn("-- checkNodeVersion(..): server version mismatch (client optimized for '{}'"
				+ ", node responded with '{}')", Defaults.NODE_VERSIONS, nodeVersion);
		return nodeVersion;
	}

	private Properties toNodeConfig(String rpcProtocol, String rpcHost, Integer rpcPort, 
			String rpcUser, String rpcPassword, String httpAuthScheme) {
		Properties nodeConfig = new Properties();
		if(rpcProtocol != null) {
			nodeConfig.setProperty(NodeProperties.RPC_PROTOCOL.getKey(), rpcProtocol);
		}
		if(rpcHost != null) {
			nodeConfig.setProperty(NodeProperties.RPC_HOST.getKey(), rpcHost);
		}
		if(rpcPort != null) {
			nodeConfig.setProperty(NodeProperties.RPC_PORT.getKey(), String.valueOf(rpcPort));
		}
		if(rpcUser != null) {
			nodeConfig.setProperty(NodeProperties.RPC_USER.getKey(), rpcUser);
		}
		if(rpcPassword != null) {
			nodeConfig.setProperty(NodeProperties.RPC_PASSWORD.getKey(), rpcPassword);
		}
		if(httpAuthScheme != null) {
			nodeConfig.setProperty(NodeProperties.HTTP_AUTH_SCHEME.getKey(), httpAuthScheme);
		}
		return nodeConfig;
	}

	private CloseableHttpClient getDefaultHttpProvider() {
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		CloseableHttpClient httpProvider = HttpClients.custom().setConnectionManager(connManager)
				.build();
		return httpProvider;
	}
	
	private String decodeNodeVersion(Integer extendedFormat) {
		if(extendedFormat == null) {
			throw new IllegalArgumentException(Errors.ARGS_NULL.getDescription());
		}
		String canonicalFormat = StringUtils.pad(extendedFormat.toString(), 8, '0', true);
		List<String> splittedFormat = StringUtils.split(canonicalFormat, 2);
		splittedFormat = StringUtils.replaceAll(splittedFormat, "^0", "");
		List<Object> separatedFormat = CollectionUtils.mergeInterlaced(splittedFormat, 
				CollectionUtils.duplicate(".", 4));
		separatedFormat.remove(separatedFormat.size() - 1);
		if (separatedFormat.get(separatedFormat.size() - 1).equals("0")) {
			separatedFormat.subList(separatedFormat.size() - 2, separatedFormat.size()).clear();
		}
		return StringUtils.join(separatedFormat);    
	}
}