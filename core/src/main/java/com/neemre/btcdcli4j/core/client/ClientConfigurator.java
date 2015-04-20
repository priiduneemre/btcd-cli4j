package com.neemre.btcdcli4j.core.client;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neemre.btcdcli4j.core.NodeProperties;
import com.neemre.btcdcli4j.core.common.Defaults;
import com.neemre.btcdcli4j.core.util.CollectionUtils;
import com.neemre.btcdcli4j.core.util.StringUtils;

public class ClientConfigurator {

	private static final Logger LOG = LoggerFactory.getLogger(ClientConfigurator.class);


	public Properties verifyNodeConfig(Properties nodeConfig) {
		for (NodeProperties property : NodeProperties.values()) {
			if (nodeConfig.getProperty(property.getKey()) == null) {
				LOG.warn("-- verifyNodeConfig(..): node property '{}' not set; reverting to "
						+ "default value '{}'", property.getKey(), property.getDefaultValue());
				nodeConfig.setProperty(property.getKey(), property.getDefaultValue());
			}
		}
		return nodeConfig;
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

	private String decodeNodeVersion(Integer extendedFormat) {
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