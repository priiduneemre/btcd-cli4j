package com.neemre.btcdcli4j.examples.api;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

import org.apache.http.impl.client.CloseableHttpClient;

import com.neemre.btcdcli4j.core.common.Defaults;
import com.neemre.btcdcli4j.core.domain.OutputOverview;
import com.neemre.btcdcli4j.examples.util.ResourceUtils;

/**A list of examples demonstrating the use of <i>bitcoind</i>'s 'Wallet API' commands 
 * currently supported by btcd-cli4j. Calling any of the methods below will cause a short overview 
 * (<i>i.e.</i> of the results of the operation) to be written to {@code stdout}.*/
public class WalletApi {

	public static void main(String[] args) throws Exception {
		CloseableHttpClient httpProvider = ResourceUtils.getHttpProvider();
		Properties nodeConfig = ResourceUtils.getNodeConfig();
		ApiCalls supportedCalls = new ApiCalls(httpProvider, nodeConfig);
		
		supportedCalls.addMultiSigAddress(2, Arrays.asList(new String[]{"mwMpStsPJUfhRX5FFs8ppU7fWWfhjuKvL4", 
				"myN6xmzRVHvsAEXv4MRzoNsoqNeuispybG", "mpgyUpBSBvVrPewGz26LmXSfRkVpq1tGV3"}));
		supportedCalls.addMultiSigAddress(3, Arrays.asList(new String[]{"mwMpStsPJUfhRX5FFs8ppU7fWWfhjuKvL4",
				"myN6xmzRVHvsAEXv4MRzoNsoqNeuispybG", "mpgyUpBSBvVrPewGz26LmXSfRkVpq1tGV3"}), 
				"jointAccountB");
		supportedCalls.backupWallet("G:\\bitplexus\\data\\wallet_backup_28022015.dat");
		supportedCalls.dumpPrivKey("n2pr9RyfNQdQ6gSWZCX5DGHuHAnNjchAy7");
		supportedCalls.dumpWallet("G:\\bitplexus\\data\\wallet_dump_28022015.txt");
		supportedCalls.encryptWallet("strawberry");
		supportedCalls.getAccount("15eXDukpi27y3WwZK7U23zQyTFQboLD2Qr");
		supportedCalls.getAccountAddress("firefly");
		supportedCalls.getAddressesByAccount("firefly");
		supportedCalls.getBalance();
		supportedCalls.getBalance("");
		supportedCalls.getBalance("", 6);
		supportedCalls.getBalance("", 6, true);
		supportedCalls.getNewAddress();
		supportedCalls.getNewAddress("firefly");
		supportedCalls.getRawChangeAddress();
		supportedCalls.getReceivedByAccount("firefly");
		supportedCalls.getReceivedByAccount("firefly", 6);
		supportedCalls.getReceivedByAddress("1NroLTCuf15y2UYqmhbMgYoVGEfF8QVTA4");
		supportedCalls.getReceivedByAddress("1NroLTCuf15y2UYqmhbMgYoVGEfF8QVTA4", 6);
		supportedCalls.getTransaction("2d117f97ab76777a195d503f39ade30047abd0b72738ee3cf15c335324051dbb");
		supportedCalls.getTransaction("9a3da847d3117374ccd930810c6db7fb37767ca9a1c969d980f99f475d2aa869", true);
		supportedCalls.getUnconfirmedBalance();
		supportedCalls.getWalletInfo();
		supportedCalls.importAddress("mydXVfvTMgphEU8TnE5MCQ4oksqc4Xhari");
		supportedCalls.importAddress("mydXVfvTMgphEU8TnE5MCQ4oksqc4Xhari", "mantis");
		supportedCalls.importAddress("mydXVfvTMgphEU8TnE5MCQ4oksqc4Xhari", "mongoose", false);
		supportedCalls.importPrivKey("cU8Q2jGeX3GNKNa5etiC8mgEgFSeVUTRQfWE2ZCzszyqYNK4Mepy");
		supportedCalls.importPrivKey("cU8Q2jGeX3GNKNa5etiC8mgEgFSeVUTRQfWE2ZCzszyqYNK4Mepy", "cricket");
		supportedCalls.importPrivKey("cU8Q2jGeX3GNKNa5etiC8mgEgFSeVUTRQfWE2ZCzszyqYNK4Mepy", "jackal", true);
		supportedCalls.importWallet("G:\\bitplexus\\data\\wallet_dump_28022015.txt");
		supportedCalls.keyPoolRefill();
		supportedCalls.keyPoolRefill(115);
		supportedCalls.listAccounts();
		supportedCalls.listAccounts(6);
		supportedCalls.listAccounts(6, true);
		supportedCalls.listAddressGroupings();
		supportedCalls.listLockUnspent();
		supportedCalls.listReceivedByAccount();
		supportedCalls.listReceivedByAccount(900);
		supportedCalls.listReceivedByAccount(900, true);
		supportedCalls.listReceivedByAccount(900, false, true);
		supportedCalls.listReceivedByAddress();
		supportedCalls.listReceivedByAddress(1100);
		supportedCalls.listReceivedByAddress(1100, true);
		supportedCalls.listReceivedByAddress(530, false, true);
		supportedCalls.listSinceBlock();
		supportedCalls.listSinceBlock("00000000000001e811e611af984d97237ce218cfb5e238ec2c73fa1113a4b865");
		supportedCalls.listSinceBlock("00000000000001e811e611af984d97237ce218cfb5e238ec2c73fa1113a4b865", 100);
		supportedCalls.listSinceBlock("00000000a5bc619e8caffc2cc61c2658e389be6b30a5681200745d8c7fffb512", 100, 
				true);
		supportedCalls.listTransactions();
		supportedCalls.listTransactions("jackal");
		supportedCalls.listTransactions("jackal", 3);
		supportedCalls.listTransactions("jackal", 3, 2);
		supportedCalls.listTransactions("friendB", 3, 2, true);
		supportedCalls.listUnspent();
		supportedCalls.listUnspent(310);
		supportedCalls.listUnspent(310, 500);
		supportedCalls.listUnspent(310, 900, Arrays.asList(new String[]{"msrHoyN5Jw1EH7saGxMqJtTKt6qhmyPZMF"}));
		supportedCalls.lockUnspent(true);
		supportedCalls.lockUnspent(false, Arrays.asList(new OutputOverview[]{new OutputOverview(
				"ff534734f74fc4ecffe1588a6554898717bb5bbc58688ddcd9a0dede132bfd13", 1)}));
		supportedCalls.move("accountA", "accountB", new BigDecimal("0.53006000"));
		supportedCalls.move("accountA", "accountB", new BigDecimal("0.21000000"), 0, "Sample move: " 
				+ "an off-chain transfer of 0.002 BTC from 'accountA' to 'accountB'.");
		supportedCalls.sendFrom("treasury", "mxahv57UR2zGXbsiL35kHWpiXkopAubMmi", new BigDecimal("0.002"));
		supportedCalls.sendFrom("treasury", "mo81ruAwDZeQKhn7SiUkR6UjCAropBiCXF", new BigDecimal("0.001"), 2);
		supportedCalls.sendFrom("treasury", "n3HBsBafTktAKMGi9Pjqey68HF42QjfwGz", new BigDecimal("0.003"),
				3, "Sample transaction: a payment of 0.003 BTC to 'supplierE' for services rendered.");
		supportedCalls.sendFrom("treasury", "mz6s3qBsifGLyJMcjxWabJ9z3Zf95Etods", new BigDecimal("0.007"),
				4, "Sample transaction: a payment of 0.007 BTC to 'supplierF' for services rendered.", 
				"supplierF");
		supportedCalls.sendMany("treasury", new HashMap<String, BigDecimal>(){{ 
				put("msrHoyN5Jw1EH7saGxMqJtTKt6qhmyPZMF", new BigDecimal("0.004")); 
				put("n3y8BpckkDDGMtSq7d2Yx46EYenyUit3Jc", new BigDecimal("0.005"));}});
		supportedCalls.sendMany("treasury", new HashMap<String, BigDecimal>(){{
				put("msrHoyN5Jw1EH7saGxMqJtTKt6qhmyPZMF", new BigDecimal("0.006")); 
				put("n3y8BpckkDDGMtSq7d2Yx46EYenyUit3Jc", new BigDecimal("0.007"));}}, 100);
		supportedCalls.sendMany("treasury", new HashMap<String, BigDecimal>(){{
				put("msrHoyN5Jw1EH7saGxMqJtTKt6qhmyPZMF", new BigDecimal("0.0015"));
				put("n3y8BpckkDDGMtSq7d2Yx46EYenyUit3Jc", new BigDecimal("0.0015"));}}, 100, 
				"Sample transaction: a payment of 0.0015 BTC to both 'supplierA' and 'supplierB' for"
						+ " services rendered.");
		supportedCalls.sendToAddress("msrHoyN5Jw1EH7saGxMqJtTKt6qhmyPZMF", new BigDecimal("0.0005"));
		supportedCalls.sendToAddress("n3y8BpckkDDGMtSq7d2Yx46EYenyUit3Jc", new BigDecimal("0.0035"), 
				"Sample transaction: a payment of 0.0035 BTC to 'supplierB' for services rendered.");
		supportedCalls.sendToAddress("mu9YMgJzJkoA6ZwhCFDkTjRfXF2BakTGw1", new BigDecimal("0.0045"), 
				"Sample transaction: a payment of 0.0045 BTC to 'supplierC' for services rendered.", 
				"supplierC");
		supportedCalls.setAccount("1NRpYDf2GdAL4yLZEAww8uUSEGM7Df6KKc", "aardvark");
		supportedCalls.setTxFee(new BigDecimal("0.00004900"));
		supportedCalls.signMessage("mixnciYh9dar2CwywYYZTHZqS4kyZWkvoV", "I like liquorice.");
		supportedCalls.walletLock();
		supportedCalls.walletPassphrase("strawberry", Defaults.WALLET_AUTH_TIMEOUT);
		supportedCalls.walletPassphraseChange("strawberry", "raspberry");
	}
}