package com.neemre.btcd;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.neemre.btcd.domain.Info;
import com.neemre.btcd.jsonrpc.model.JsonRpcRequest;
import com.neemre.btcd.jsonrpc.model.JsonRpcResponse;

public class StaticMain {
	public static String protocol = "http";
	public static String user = "falcon-pc";
	public static String password = "3F4DN9QGqWrB4DCdfYMXp8xdDYL4HDFzpaS9r76DbNhw";
	public static String host = "localhost";
	public static String port = "8332";
	
	
	public static void main(String[] args) {
		ObjectWriter jsonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
		JsonRpcRequest rpcRequest = new JsonRpcRequest();
		rpcRequest.setId(14324);
		rpcRequest.setMethod(Commands.GET_INFO.getName());
		rpcRequest.setParams(null);
		
		
		String credentials = Base64.encodeBase64String((user + ":" + password).getBytes());
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost httpRequest = new HttpPost(String.format("%s://%s:%s/", protocol, host, port));
		httpRequest.setHeader(HttpConst.REQ_HEADER_AUTH, HttpConst.AUTH_SCHEME_BASIC + " " 
				+ credentials);
		httpRequest.setHeader(null);
		httpRequest.setHeader(new BasicHeader("", ""));
		for(int i = 0; i < httpRequest.getAllHeaders().length; i++) {
			System.out.println(httpRequest.getAllHeaders()[i]);
		}
		try {
			httpRequest.setEntity(new StringEntity(jsonWriter.writeValueAsString(rpcRequest)));
			HttpResponse httpResponse = httpClient.execute(httpRequest);
			HttpEntity entity = httpResponse.getEntity();
			System.out.println("Http response code: " + httpResponse.getStatusLine());
			if(entity != null) {
				String responseAsStr = EntityUtils.toString(entity);
				System.out.print("Http response body: " + responseAsStr);
				JsonRpcResponse rpcResponse = new ObjectMapper().readValue(responseAsStr, JsonRpcResponse.class);
				System.out.println("Response as a POJO: " + rpcResponse);
				Info info = new ObjectMapper().readValue(rpcResponse.getResult().toString(), Info.class);
				System.out.println("Response result as a POJO: " + info);
			}
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//			JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(String.format("%s://%s:%s/",
//					protocol, host, port)), headers);
//			System.out.println(client.getHeaders());
		try {
//				Info response = client.invoke("getinfo", null, Info.class);
//				System.out.println(response);
			ArrayList<Integer> f = new ArrayList<Integer>();
			f.add(100000);
//				Block response2 = client.invoke("getblock", new String[]{"000000000003ba27aa200b1cecaad478d2b00432346c3f1f3986da1afd33e506"}, Block.class);
//				System.out.println(response2);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}