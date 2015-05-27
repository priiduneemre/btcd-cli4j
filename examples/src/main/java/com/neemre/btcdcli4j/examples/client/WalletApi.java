package com.neemre.btcdcli4j.examples.client;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

import org.apache.http.impl.client.CloseableHttpClient;

import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.domain.OutputOverview;
import com.neemre.btcdcli4j.examples.util.ResourceUtils;

/**A list of examples demonstrating the use of <i>bitcoind</i>'s wallet RPCs (via the JSON-RPC 
 * API).*/
public class WalletApi {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpProvider = ResourceUtils.getHttpProvider();
		Properties nodeConfig = ResourceUtils.getNodeConfig();
		BtcdClient client = new VerboseBtcdClientImpl(httpProvider, nodeConfig);
		
		client.addMultiSigAddress(2, Arrays.asList(new String[]{"mwMpStsPJUfhRX5FFs8ppU7fWWfhjuKvL4", 
				"myN6xmzRVHvsAEXv4MRzoNsoqNeuispybG", "mpgyUpBSBvVrPewGz26LmXSfRkVpq1tGV3"}));
		client.addMultiSigAddress(3, Arrays.asList(new String[]{"mwMpStsPJUfhRX5FFs8ppU7fWWfhjuKvL4",
				"myN6xmzRVHvsAEXv4MRzoNsoqNeuispybG", "mpgyUpBSBvVrPewGz26LmXSfRkVpq1tGV3"}), 
				"jointAccountB");
		client.backupWallet("G:\\bitplexus\\data\\wallet_backup_28022015.dat");
		client.dumpPrivKey("n2pr9RyfNQdQ6gSWZCX5DGHuHAnNjchAy7");
		client.dumpWallet("G:\\bitplexus\\data\\wallet_dump_28022015.txt");
		client.encryptWallet("strawberry");
		client.getAccount("15eXDukpi27y3WwZK7U23zQyTFQboLD2Qr");
		client.getAccountAddress("firefly");
		client.getAddressesByAccount("firefly");
		client.getBalance();
		client.getBalance("");
		client.getBalance("", 6);
		client.getBalance("", 6, true);
		client.getNewAddress();
		client.getNewAddress("firefly");
		client.getRawChangeAddress();
		client.getReceivedByAccount("firefly");
		client.getReceivedByAccount("firefly", 6);
		client.getReceivedByAddress("1NroLTCuf15y2UYqmhbMgYoVGEfF8QVTA4");
		client.getReceivedByAddress("1NroLTCuf15y2UYqmhbMgYoVGEfF8QVTA4", 6);
		client.getTransaction("2d117f97ab76777a195d503f39ade30047abd0b72738ee3cf15c335324051dbb");
		client.getTransaction("9a3da847d3117374ccd930810c6db7fb37767ca9a1c969d980f99f475d2aa869", true);
		client.getUnconfirmedBalance();
		client.getWalletInfo();
		client.importAddress("mydXVfvTMgphEU8TnE5MCQ4oksqc4Xhari");
		client.importAddress("mydXVfvTMgphEU8TnE5MCQ4oksqc4Xhari", "mantis");
		client.importAddress("mydXVfvTMgphEU8TnE5MCQ4oksqc4Xhari", "mongoose", false);
		client.importPrivKey("cU8Q2jGeX3GNKNa5etiC8mgEgFSeVUTRQfWE2ZCzszyqYNK4Mepy");
		client.importPrivKey("cU8Q2jGeX3GNKNa5etiC8mgEgFSeVUTRQfWE2ZCzszyqYNK4Mepy", "cricket");
		client.importPrivKey("cU8Q2jGeX3GNKNa5etiC8mgEgFSeVUTRQfWE2ZCzszyqYNK4Mepy", "jackal", true);
		client.importWallet("G:\\bitplexus\\data\\wallet_dump_28022015.txt");
		client.keyPoolRefill();
		client.keyPoolRefill(115);
		client.listAccounts();
		client.listAccounts(6);
		client.listAccounts(6, true);
		client.listAddressGroupings();
		client.listLockUnspent();
		client.listReceivedByAccount();
		client.listReceivedByAccount(900);
		client.listReceivedByAccount(900, true);
		client.listReceivedByAccount(900, false, true);
		client.listReceivedByAddress();
		client.listReceivedByAddress(1100);
		client.listReceivedByAddress(1100, true);
		client.listReceivedByAddress(530, false, true);
		client.listSinceBlock();
		client.listSinceBlock("00000000000001e811e611af984d97237ce218cfb5e238ec2c73fa1113a4b865");
		client.listSinceBlock("00000000000001e811e611af984d97237ce218cfb5e238ec2c73fa1113a4b865", 100);
		client.listSinceBlock("00000000a5bc619e8caffc2cc61c2658e389be6b30a5681200745d8c7fffb512", 100, true);
		client.listTransactions();
		client.listTransactions("jackal");
		client.listTransactions("jackal", 3);
		client.listTransactions("jackal", 3, 2);
		client.listTransactions("friendB", 3, 2, true);
		client.listUnspent();
		client.listUnspent(310);
		client.listUnspent(310, 500);
		client.listUnspent(310, 900, Arrays.asList(new String[]{"msrHoyN5Jw1EH7saGxMqJtTKt6qhmyPZMF"}));
		client.lockUnspent(true);
		client.lockUnspent(false, Arrays.asList(new OutputOverview[]{new OutputOverview(
				"ff534734f74fc4ecffe1588a6554898717bb5bbc58688ddcd9a0dede132bfd13", 1)}));
		client.move("accountA", "accountB", new BigDecimal("0.53006000"));
		client.move("accountA", "accountB", new BigDecimal("0.00200000"), 0, "Sample move: " 
				+ "an off-chain transfer of 0.002 BTC from 'accountA' to 'accountB'.");
		client.sendFrom("treasury", "mxahv57UR2zGXbsiL35kHWpiXkopAubMmi", new BigDecimal("0.002"));
		client.sendFrom("treasury", "mo81ruAwDZeQKhn7SiUkR6UjCAropBiCXF", new BigDecimal("0.001"), 2);
		client.sendFrom("treasury", "n3HBsBafTktAKMGi9Pjqey68HF42QjfwGz", new BigDecimal("0.003"), 3, 
				"Sample transaction: a payment of 0.003 BTC to 'supplierE' for services rendered.");
		client.sendFrom("treasury", "mz6s3qBsifGLyJMcjxWabJ9z3Zf95Etods", new BigDecimal("0.007"), 4, 
				"Sample transaction: a payment of 0.007 BTC to 'supplierF' for services rendered.", 
				"supplierF");
		client.sendMany("treasury", new HashMap<String, BigDecimal>(){{ 
				put("msrHoyN5Jw1EH7saGxMqJtTKt6qhmyPZMF", new BigDecimal("0.004")); 
				put("n3y8BpckkDDGMtSq7d2Yx46EYenyUit3Jc", new BigDecimal("0.005"));}});
		client.sendMany("treasury", new HashMap<String, BigDecimal>(){{
				put("msrHoyN5Jw1EH7saGxMqJtTKt6qhmyPZMF", new BigDecimal("0.006")); 
				put("n3y8BpckkDDGMtSq7d2Yx46EYenyUit3Jc", new BigDecimal("0.007"));}}, 100);
		client.sendMany("treasury", new HashMap<String, BigDecimal>(){{
				put("msrHoyN5Jw1EH7saGxMqJtTKt6qhmyPZMF", new BigDecimal("0.0015"));
				put("n3y8BpckkDDGMtSq7d2Yx46EYenyUit3Jc", new BigDecimal("0.0015"));}}, 100, 
				"Sample transaction: a payment of 0.0015 BTC to both 'supplierA' and 'supplierB' for"
						+ " services rendered.");
		client.sendToAddress("msrHoyN5Jw1EH7saGxMqJtTKt6qhmyPZMF", new BigDecimal("0.0005"));
		client.sendToAddress("n3y8BpckkDDGMtSq7d2Yx46EYenyUit3Jc", new BigDecimal("0.0035"), 
				"Sample transaction: a payment of 0.0035 BTC to 'supplierB' for services rendered.");
		client.sendToAddress("mu9YMgJzJkoA6ZwhCFDkTjRfXF2BakTGw1", new BigDecimal("0.0045"), 
				"Sample transaction: a payment of 0.0045 BTC to 'supplierC' for services rendered.", 
				"supplierC");
		client.setAccount("1NRpYDf2GdAL4yLZEAww8uUSEGM7Df6KKc", "aardvark");
		client.setTxFee(new BigDecimal("0.00004900"));
		client.signMessage("mixnciYh9dar2CwywYYZTHZqS4kyZWkvoV", "I like liquorice.");
		client.walletLock();
		client.walletPassphrase("strawberry", 60);
		client.walletPassphraseChange("strawberry", "raspberry");
	}
}