package com.neemre.btcdcli4j.core.common;

import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neemre.btcdcli4j.core.NodeProperties;

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
}