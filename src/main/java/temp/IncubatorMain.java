package temp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.http.client.HttpClient;

import com.neemre.btcdcli4j.Commands;
import com.neemre.btcdcli4j.client.BtcdClient;
import com.neemre.btcdcli4j.client.BtcdClientImpl;
import com.neemre.btcdcli4j.domain.PeerNode;
import com.neemre.btcdcli4j.jsonrpc.JsonPrimitiveParser;
import com.neemre.btcdcli4j.jsonrpc.client.JsonRpcClient;
import com.neemre.btcdcli4j.jsonrpc.client.JsonRpcClientImpl;
import com.neemre.btcdcli4j.util.CollectionUtils;

public class IncubatorMain {
	
	public static void main(String[] args) throws IOException {
		JsonPrimitiveParser parser = new JsonPrimitiveParser();
		System.out.println(parser.parseString("\"nul\"\""));
		
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.printf("Unique ID: '%s'\n", id);
		
		List<String> listA = new ArrayList<String>();
		listA.add("listA: I like bitcoin");
		listA.add("listA: I like litecoin");
		listA.add("listA: I like dogecoin");
		List<String> listB = new ArrayList<String>();
		listB.add("listB: I like bitcoin");
		listB.add("listB: I like litecoin");
		listB.add("listB: I like dogecoin");
		List<String> listC = new ArrayList<String>();
		listC.add("listC: I like bitcoin");
		listC.add("listC: I like litecoin");
		listC.add("listC: I like dogecoin");
		List<String> listD = new ArrayList<String>();
		listD.add("listD: I like bitcoin");
		listD.add("listD: I like litecoin");
		listD.add("listD: I like dogecoin");

		System.out.printf("CollectionUtils.equalsSize(..) result is: '%s'\n", 
				CollectionUtils.equalsSize(listA, listB, listC, listD, null));
		System.out.printf("CollectionUtils.mergeInterlaced(..) result is: '%s'\n",
				CollectionUtils.mergeInterlaced(listA, listB, listC, listD));
		
		String[] arrayA = new String[3];
		arrayA[0] = "Hey, (A)";
		arrayA[1] = "what's (A)";
		arrayA[2] = "up? (A)";
		String[] arrayB = new String[3];
		arrayB[0] = "Hey, (B)";
		arrayB[1] = "what's (B)";
		arrayB[2] = "up? (B)";
		System.out.println(CollectionUtils.mergeInterlaced(Arrays.asList(arrayA), Arrays.asList(arrayB)));
		
		HttpClient httpProvider = ExampleUtils.getHttpProvider();
		Properties nodeConfig = ExampleUtils.getNodeConfig();
		JsonRpcClient rpcClient = new JsonRpcClientImpl(httpProvider, nodeConfig);
		String peerInfoJson = "[{\"id\":1093,\"addr\":"
				+ "\"194.71.109.94:8333\",\"addrlocal\":\"46.166.161.166:37578\",\"services\":"
				+ "\"0000000000000001\",\"lastsend\":1424883192,\"lastrecv\":1424883192,\"bytessent\":"
				+ "284293,\"bytesrecv\":10845237,\"conntime\":1424878706,\"pingtime\":0.24801400,"
				+ "\"version\":70001,\"subver\":\"/Satoshi:0.8.5/\",\"inbound\":false,\"startingheight\":"
				+ "345108,\"banscore\":0,\"synced_headers\":345116,\"synced_blocks\":345116,\"inflight\":"
				+ "[345112,345113,345114],\"whitelisted\":false},{\"id\":1094,\"addr\":"
				+ "\"128.199.254.244:8333\",\"addrlocal\":\"46.166.161.166:37662\",\"services\":"
				+ "\"0000000000000001\",\"lastsend\":1424883192,\"lastrecv\":1424883192,\"bytessent\":"
				+ "280158,\"bytesrecv\":10365354,\"conntime\":1424878774,\"pingtime\":0.58703400,"
				+ "\"version\":70002,\"subver\":\"/Satoshi:0.10.0/\",\"inbound\":false,\"startingheight\":"
				+ "345108,\"banscore\":0,\"synced_headers\":345114,\"synced_blocks\":345114,\"inflight\":"
				+ "[345109,345110,34511],\"whitelisted\":false}]";
		List<PeerNode> peerInfo = rpcClient.getMapper().mapToList(peerInfoJson, PeerNode.class);
		System.out.println("Test 'peerInfo': " + peerInfo);
		
		//System.out.println("Testing 'CollectionUtils.asMap(..)' #1: " + CollectionUtils.asMap(
		//		"addressA", new BigDecimal("0.03"), "addressB", new BigDecimal("0.05"), "addressC"));
		System.out.println("Testing 'CollectionUtils.asMap(..)' #2: " + CollectionUtils.asMap(
				"addressA", new BigDecimal("0.03"), "addressB", new BigDecimal("0.05"), "addressC", 
				new BigDecimal("0.09")));
		
	}
}
