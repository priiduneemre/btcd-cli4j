package com.neemre.btcdcli4j.core.http.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neemre.btcdcli4j.core.NodeProperties;
import com.neemre.btcdcli4j.core.common.Constants;
import com.neemre.btcdcli4j.core.common.DataFormats;
import com.neemre.btcdcli4j.core.common.Defaults;
import com.neemre.btcdcli4j.core.common.Errors;
import com.neemre.btcdcli4j.core.http.HttpConstants;
import com.neemre.btcdcli4j.core.http.HttpLayerException;

public class SimpleHttpClientImpl implements SimpleHttpClient {
	
	private static final Logger LOG = LoggerFactory.getLogger(SimpleHttpClientImpl.class);
	
	private CloseableHttpClient provider;
	private Properties nodeConfig;


	public SimpleHttpClientImpl(CloseableHttpClient provider, Properties nodeConfig) {
		LOG.info("** SimpleHttpClientImpl(): initiating the HTTP communication layer");
		this.provider = provider;
		this.nodeConfig = nodeConfig;
	}

	@Override
	public String execute(String reqMethod, String reqPayload) throws HttpLayerException {
		CloseableHttpResponse response = null;
		try {
			response = provider.execute(getNewRequest(reqMethod, reqPayload), new BasicHttpContext());
			response = verifyResponse(response);
			HttpEntity respPayloadEntity = response.getEntity();
			String respPayload = Constants.STRING_EMPTY;
			if(respPayloadEntity != null) {
				respPayload = EntityUtils.toString(respPayloadEntity);
				EntityUtils.consume(respPayloadEntity);
			}
			LOG.debug("-- execute(..): '{}' response payload received for HTTP '{}' request with " 
					+ "status line '{}'", ((respPayloadEntity == null) ? "null" : "non-null"), 
					reqMethod, response.getStatusLine());
			return respPayload;
		} catch (ClientProtocolException e) {
			throw new HttpLayerException(Errors.REQUEST_HTTP_FAULT, e);
		} catch (IOException e) {
			throw new HttpLayerException(Errors.IO_UNKNOWN, e);
		} catch (URISyntaxException e) {
			throw new HttpLayerException(Errors.PARSE_URI_FAILED, e);
		} finally {
			try {
				if(response != null) {
					LOG.debug("-- execute(..): attempting to recycle old HTTP response (reply to a "
							+ "'{}' request) with status line '{}'", reqMethod, response
							.getStatusLine());
					response.close();
				}
			} catch (IOException e) {
				throw new HttpLayerException(Errors.IO_UNKNOWN, e);
			}
		}
	}

	private HttpRequestBase getNewRequest(String reqMethod, String reqPayload) 
			throws URISyntaxException, UnsupportedEncodingException {
		HttpRequestBase request;
		if(reqMethod.equals(HttpConstants.REQ_METHOD_POST)) {
			HttpPost postRequest = new HttpPost();
			postRequest.setHeader(HttpConstants.HEADER_CONTENT_TYPE, DataFormats.JSON.getMediaType());
			postRequest.setEntity(new StringEntity(reqPayload));
			request = postRequest;
		} else {
			throw new IllegalArgumentException(Errors.ARGS_HTTP_METHOD_UNSUPPORTED.getDescription());
		}
		request.setURI(new URI(String.format("%s://%s:%s/", 
					nodeConfig.get(NodeProperties.RPC_PROTOCOL.getKey()), 
					nodeConfig.get(NodeProperties.RPC_HOST.getKey()), 
					nodeConfig.get(NodeProperties.RPC_PORT.getKey()))));
		String authScheme = nodeConfig.get(NodeProperties.HTTP_AUTH_SCHEME.getKey()).toString();
		request.setHeader(resolveAuthHeader(authScheme));
		LOG.debug("<< getNewRequest(..): returning a new HTTP '{}' request with target endpoint "
				+ "'{}' and headers '{}'", reqMethod, request.getURI(), request.getAllHeaders());
		return request;
	}

	private Header resolveAuthHeader(String authScheme) {
		if(authScheme.equals(HttpConstants.AUTH_SCHEME_NONE)) {
			return null;
		}
		if(authScheme.equals(HttpConstants.AUTH_SCHEME_BASIC)) {
			return new BasicHeader(HttpConstants.HEADER_AUTH, HttpConstants.AUTH_SCHEME_BASIC 
					+ " " + getCredentials(HttpConstants.AUTH_SCHEME_BASIC));
		}
		return null;
	}

	private String getCredentials(String authScheme) {
		if(authScheme.equals(HttpConstants.AUTH_SCHEME_NONE)){
			return Constants.STRING_EMPTY;
		} else if(authScheme.equals(HttpConstants.AUTH_SCHEME_BASIC)) {
			return Base64.encodeBase64String((nodeConfig.get(NodeProperties.RPC_USER.getKey()) 
					+ ":" + nodeConfig.get(NodeProperties.RPC_PASSWORD.getKey())).getBytes());
		}
		throw new IllegalArgumentException(Errors.ARGS_HTTP_AUTHSCHEME_UNSUPPORTED.getDescription());
	}
	
	private CloseableHttpResponse verifyResponse(CloseableHttpResponse response) {
		Header serverHeader = response.getFirstHeader(HttpConstants.HEADER_SERVER);
		if((serverHeader != null) && (serverHeader.getValue() != null)) {
			String serverVersion = serverHeader.getValue();
			if(!serverVersion.equals(Defaults.SERVER_VERSION)) {
				LOG.warn("-- verifyResponse(..): server version mismatch (library optimized for '{}'"
						+ ", node responded with '{}')", Defaults.SERVER_VERSION, serverVersion);
			}
		}
		return response;
	}
}