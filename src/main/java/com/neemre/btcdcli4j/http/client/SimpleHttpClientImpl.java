package com.neemre.btcdcli4j.http.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.neemre.btcdcli4j.NodeProperties;
import com.neemre.btcdcli4j.common.Constants;
import com.neemre.btcdcli4j.http.HttpConst;

public class SimpleHttpClientImpl implements SimpleHttpClient {
	
	private HttpClient rawClient;
	private Properties nodeConfig;
	
	
	public SimpleHttpClientImpl(HttpClient rawClient, Properties nodeConfig) {
		this.rawClient = rawClient;
		this.nodeConfig = nodeConfig;
	}
	
	public String execute(String reqPayload) {
		try {
			HttpResponse response = rawClient.execute(getNewRequest(HttpConst.REQ_METHOD_POST, 
					reqPayload));
			HttpEntity respPayloadEntity = response.getEntity();
			if(respPayloadEntity != null) {
				return EntityUtils.toString(respPayloadEntity);
			}
			return Constants.EMPTY;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private HttpRequestBase getNewRequest(String reqMethod, String reqPayload) 
			throws URISyntaxException, ClientProtocolException, IOException {
		if(reqMethod.equals(HttpConst.REQ_METHOD_POST)) {
				HttpPost request = new HttpPost(new URI(String.format("%s://%s:%s/", 
						nodeConfig.get(NodeProperties.RPC_PROTOCOL.getKey()), 
						nodeConfig.get(NodeProperties.RPC_HOST.getKey()), 
						nodeConfig.get(NodeProperties.RPC_PORT.getKey()))));
				String authScheme = nodeConfig.get(NodeProperties.HTTP_AUTH_SCHEME.getKey())
						.toString();
				request.setHeader(resolveAuthHeader(authScheme));
				request.setEntity(new StringEntity(reqPayload));
				return request;			
		}
		return null;
	}
	
	private Header resolveAuthHeader(String authScheme) {
		if(authScheme.equals(HttpConst.AUTH_SCHEME_NONE)) {
			return null;
		}
		if(authScheme.equals(HttpConst.AUTH_SCHEME_BASIC)) {
			return new BasicHeader(HttpConst.REQ_HEADER_AUTH, HttpConst.AUTH_SCHEME_BASIC + " " 
					+ getCredentials(HttpConst.AUTH_SCHEME_BASIC));
		}
		return null;
	}
	
	private String getCredentials(String authScheme) {
		if(authScheme.equals(HttpConst.AUTH_SCHEME_NONE)){
			return Constants.EMPTY;
		} else if(authScheme.equals(HttpConst.AUTH_SCHEME_BASIC)) {
			return Base64.encodeBase64String((nodeConfig.get(NodeProperties.RPC_USER.getKey()) 
					+ ":" + nodeConfig.get(NodeProperties.RPC_PASSWORD.getKey())).getBytes());
		}
		return null;
	}
}