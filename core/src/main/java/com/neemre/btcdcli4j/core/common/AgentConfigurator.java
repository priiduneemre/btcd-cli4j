package com.neemre.btcdcli4j.core.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neemre.btcdcli4j.core.NodeProperties;

/**An abstract superclass containing the core functionality required for constructing 
 * & configuring new <i>bitcoind</i> API consumer instances (<i>i.e.</i> {@code BtcdClient},  
 * {@code BtcdDaemon} etc.).*/
public abstract class AgentConfigurator {
	
	private static final Logger LOG = LoggerFactory.getLogger(AgentConfigurator.class);
	
	
	public abstract Set<NodeProperties> getRequiredProperties();
	
	public Properties checkNodeConfig(Properties nodeConfig) {
		for (NodeProperties property : getRequiredProperties()) {
			if (nodeConfig.getProperty(property.getKey()) == null) {
				LOG.warn("-- checkNodeConfig(..): node property '{}' not set; reverting to "
						+ "default value '{}'", property.getKey(), property.getDefaultValue());
				nodeConfig.setProperty(property.getKey(), property.getDefaultValue());
			}
		}
		return nodeConfig;
	}
	
	public Properties toProperties(Object... values) {
		Properties properties = new Properties();
		List<NodeProperties> requiredProperties = new ArrayList<NodeProperties>(
				getRequiredProperties());
		for (int i = 0; i < requiredProperties.size(); i++) {
			if (values[i] != null) {
				String key = requiredProperties.get(i).getKey();
				properties.setProperty(key, values[i].toString());
			}
		}
		return properties;
	}
}