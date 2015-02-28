package temp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;

import com.neemre.btcdcli4j.Commands;
import com.neemre.btcdcli4j.client.BtcdClient;
import com.neemre.btcdcli4j.client.BtcdClientImpl;
import com.neemre.btcdcli4j.common.Defaults;
import com.neemre.btcdcli4j.domain.Info;
import com.neemre.btcdcli4j.domain.MemPoolInfo;
import com.neemre.btcdcli4j.domain.MiningInfo;
import com.neemre.btcdcli4j.domain.PeerNode;
import com.neemre.btcdcli4j.domain.WalletInfo;
import com.neemre.btcdcli4j.util.CollectionUtils;

public class ApiUsage {

	/**A list of examples demonstrating the use of all <i>bitcoind</i> API commands currently 
	 * supported by btcd-cli4j (as of 0.1.0). Calling any of the methods below will cause a
	 * short overview (<i>i.e.</i> of the results of the operation) to be written to 
	 * {@code stdout}.*/
	public static void main(String[] args) throws Exception {
		HttpClient httpProvider = ExampleUtils.getHttpProvider();
		Properties nodeConfig = ExampleUtils.getNodeConfig();
		ApiCalls supportedCalls = new ApiCalls(httpProvider, nodeConfig);

		//		supportedCalls.backupWallet("G:\\bitplexus\\data\\wallet_backup_28022015.dat");
		supportedCalls.dumpPrivKey("n2pr9RyfNQdQ6gSWZCX5DGHuHAnNjchAy7");
		//		supportedCalls.dumpWallet("G:\\bitplexus\\data\\wallet_dump_28022015.txt");
		//		supportedCalls.encryptWallet("strawberry");
		//		supportedCalls.getAccount("15eXDukpi27y3WwZK7U23zQyTFQboLD2Qr");
		//		supportedCalls.getAccountAddress("firefly");
		//		supportedCalls.getAddressesByAccount("firefly");
		//		supportedCalls.getBalance();
		//		supportedCalls.getBalance("");
		//		supportedCalls.getBalance("", 6);
		//		supportedCalls.getBalance("", 6, true);
		//		supportedCalls.getBestBlockHash();
		//		supportedCalls.getBlockCount();
		//		supportedCalls.getBlockHash(345168);
		//		supportedCalls.getDifficulty();
		//		supportedCalls.getGenerate();
		//		supportedCalls.getHashesPerSec();
		//		supportedCalls.getInfo();
		//		supportedCalls.getMemPoolInfo();
		//		supportedCalls.getMiningInfo();
		//		supportedCalls.getNewAddress();
		//		supportedCalls.getNewAddress("firefly");
		//		supportedCalls.getPeerInfo();
		//		supportedCalls.getRawChangeAddress();
		//		supportedCalls.getReceivedByAccount("firefly");
		//		supportedCalls.getReceivedByAccount("firefly", 6);
		//		supportedCalls.getReceivedByAddress("1NroLTCuf15y2UYqmhbMgYoVGEfF8QVTA4");
		//		supportedCalls.getReceivedByAddress("1NroLTCuf15y2UYqmhbMgYoVGEfF8QVTA4", 6);
		//		supportedCalls.getUnconfirmedBalance();
		//		supportedCalls.getWalletInfo();
		//		supportedCalls.importAddress("mydXVfvTMgphEU8TnE5MCQ4oksqc4Xhari");
		//		supportedCalls.importAddress("mydXVfvTMgphEU8TnE5MCQ4oksqc4Xhari", "mantis");
		//		supportedCalls.importAddress("mydXVfvTMgphEU8TnE5MCQ4oksqc4Xhari", "mongoose", false);
		//		supportedCalls.importPrivKey("cU8Q2jGeX3GNKNa5etiC8mgEgFSeVUTRQfWE2ZCzszyqYNK4Mepy");
		//		supportedCalls.importPrivKey("cU8Q2jGeX3GNKNa5etiC8mgEgFSeVUTRQfWE2ZCzszyqYNK4Mepy", "cricket");
		//		supportedCalls.importPrivKey("cU8Q2jGeX3GNKNa5etiC8mgEgFSeVUTRQfWE2ZCzszyqYNK4Mepy", "jackal", true);
		//		supportedCalls.importWallet("G:\\bitplexus\\data\\wallet_dump_28022015.txt");
		//		supportedCalls.keyPoolRefill();
		//		supportedCalls.keyPoolRefill(115);
		//		supportedCalls.listAccounts();
		//		supportedCalls.listAccounts(6);
		//		supportedCalls.listAccounts(6, true);
		//		supportedCalls.setAccount("1NRpYDf2GdAL4yLZEAww8uUSEGM7Df6KKc", "aardvark");
		//		supportedCalls.setGenerate(false);
		//		supportedCalls.setGenerate(false, 7);
		//		supportedCalls.setTxFee(new BigDecimal("0.00004900"));
		//		supportedCalls.stop();
		//		supportedCalls.walletLock();
		//		supportedCalls.walletPassphrase("strawberry", Defaults.WALLET_AUTH_TIMEOUT);
		//		supportedCalls.walletPassphraseChange("strawberry", "raspberry");
	}

	static class ApiCalls {

		private BtcdClient btcdClient;


		public ApiCalls(HttpClient httpProvider, Properties nodeConfig) {
			btcdClient = new BtcdClientImpl(httpProvider, nodeConfig);
		}

		public void backupWallet(String filePath) {
			btcdClient.backupWallet(filePath);
			printResult(Commands.BACKUP_WALLET.getName(), new String[]{"filePath"}, 
					new Object[]{filePath}, null);
		}

		public void dumpPrivKey(String address) {
			String privateKey = btcdClient.dumpPrivKey(address);
			printResult(Commands.DUMP_PRIV_KEY.getName(), new String[]{"address"}, 
					new Object[]{address}, privateKey);
		}
		
		public void dumpWallet(String filePath) {
			btcdClient.dumpWallet(filePath);
			printResult(Commands.DUMP_WALLET.getName(), new String[]{"filePath"}, 
					new Object[]{filePath}, null);
		}
		
		private void encryptWallet(String passphrase) {
			String noticeMsg = btcdClient.encryptWallet(passphrase);
			printResult(Commands.ENCRYPT_WALLET.getName(), new String[]{"passphrase"}, 
					new Object[]{passphrase}, noticeMsg);
		}	

		private void getAccount(String address) {
			String account = btcdClient.getAccount(address);
			printResult(Commands.GET_ACCOUNT.getName(), new String[]{"address"}, 
					new Object[]{address}, account);
		}

		public void getAccountAddress(String account) {
			String address = btcdClient.getAccountAddress(account);
			printResult(Commands.GET_ACCOUNT_ADDRESS.getName(), new String[]{"account"}, 
					new Object[]{account}, address);
		}

		public void getAddressesByAccount(String account) {
			List<String> addresses = btcdClient.getAddressesByAccount(account);
			printResult(Commands.GET_ADDRESSES_BY_ACCOUNT.getName(), new String[]{"account"},
					new Object[]{account}, addresses);
		}

		private void getBalance() {
			BigDecimal balance = btcdClient.getBalance();
			printResult(Commands.GET_BALANCE.getName(), null, null, balance);
		}

		private void getBalance(String account) {
			BigDecimal balance = btcdClient.getBalance(account);
			printResult(Commands.GET_BALANCE.getName(), new String[]{"account"}, new Object[]{account},
					balance);
		}

		private void getBalance(String account, int confirmations) {
			BigDecimal balance = btcdClient.getBalance(account, confirmations);
			printResult(Commands.GET_BALANCE.getName(), new String[]{"account", "confirmations"}, 
					new Object[]{account, confirmations}, balance);
		}

		private void getBalance(String account, int confirmations, boolean withWatchOnly) {
			BigDecimal balance = btcdClient.getBalance(account, confirmations, withWatchOnly);
			printResult(Commands.GET_BALANCE.getName(), new String[]{"account", "confirmations", 
			"withWatchOnly"}, new Object[]{account, confirmations, withWatchOnly}, balance);
		}

		private void getBestBlockHash() {
			String blockHash = btcdClient.getBestBlockHash();
			printResult(Commands.GET_BEST_BLOCK_HASH.getName(), null, null, blockHash);
		}

		private void getBlockCount() {
			Integer blockHeight = btcdClient.getBlockCount();
			printResult(Commands.GET_BLOCK_COUNT.getName(), null, null, blockHeight);
		}

		private void getBlockHash(Integer blockHeight) {
			String blockHash = btcdClient.getBlockHash(blockHeight);
			printResult(Commands.GET_BLOCK_HASH.getName(), new String[]{"blockHeight"}, 
					new Object[]{blockHeight}, blockHash);
		}

		private void getDifficulty() {
			BigDecimal difficulty = btcdClient.getDifficulty();
			printResult(Commands.GET_DIFFICULTY.getName(), null, null, difficulty);
		}

		private void getGenerate() {
			Boolean isGenerate = btcdClient.getGenerate();
			printResult(Commands.GET_GENERATE.getName(), null, null, isGenerate);
		}

		private void getHashesPerSec() {
			Long hashesPerSec = btcdClient.getHashesPerSec();
			printResult(Commands.GET_HASHES_PER_SEC.getName(), null, null, hashesPerSec);
		}

		private void getInfo() {
			Info info = btcdClient.getInfo();
			printResult(Commands.GET_INFO.getName(), null, null, info);
		}

		private void getMemPoolInfo() {
			MemPoolInfo memPoolInfo = btcdClient.getMemPoolInfo();
			printResult(Commands.GET_MEM_POOL_INFO.getName(), null, null, memPoolInfo);
		}

		private void getMiningInfo() {
			MiningInfo miningInfo = btcdClient.getMiningInfo();
			printResult(Commands.GET_MINING_INFO.getName(), null, null, miningInfo);
		}

		public void getNewAddress() {
			String address = btcdClient.getNewAddress();
			printResult(Commands.GET_NEW_ADDRESS.getName(), null, null, address);
		}

		public void getNewAddress(String account) {
			String address = btcdClient.getNewAddress(account);
			printResult(Commands.GET_NEW_ADDRESS.getName(), new String[]{"account"}, 
					new Object[]{account}, address);
		}

		public void getPeerInfo() {
			List<PeerNode> peerInfo = btcdClient.getPeerInfo();
			printResult(Commands.GET_PEER_INFO.getName(), null, null, peerInfo);
		}

		public void getRawChangeAddress() {
			String address = btcdClient.getRawChangeAddress();
			printResult(Commands.GET_RAW_CHANGE_ADDRESS.getName(), null, null, address);
		}

		public void getReceivedByAccount(String account) {
			BigDecimal totalReceived = btcdClient.getReceivedByAccount(account);
			printResult(Commands.GET_RECEIVED_BY_ACCOUNT.getName(), new String[]{"account"},
					new Object[]{account}, totalReceived);
		}

		public void getReceivedByAccount(String account, int confirmations) {
			BigDecimal totalReceived = btcdClient.getReceivedByAccount(account, confirmations);
			printResult(Commands.GET_RECEIVED_BY_ACCOUNT.getName(), new String[]{"account", 
			"confirmations"}, new Object[]{account, confirmations}, totalReceived);
		}

		public void getReceivedByAddress(String address) {
			BigDecimal totalReceived = btcdClient.getReceivedByAddress(address);
			printResult(Commands.GET_RECEIVED_BY_ADDRESS.getName(), new String[]{"address"},
					new Object[]{address}, totalReceived);
		}

		public void getReceivedByAddress(String address, int confirmations) {
			BigDecimal totalReceived = btcdClient.getReceivedByAddress(address, confirmations);
			printResult(Commands.GET_RECEIVED_BY_ADDRESS.getName(), new String[]{"address", 
			"confirmations"}, new Object[]{address, confirmations}, totalReceived);
		}

		public void getUnconfirmedBalance() {
			BigDecimal unconfirmedBalance = btcdClient.getUnconfirmedBalance();
			printResult(Commands.GET_UNCONFIRMED_BALANCE.getName(), null, null, unconfirmedBalance);
		}

		public void getWalletInfo() {
			WalletInfo walletInfo = btcdClient.getWalletInfo();
			printResult(Commands.GET_WALLET_INFO.getName(), null, null, walletInfo);
		}

		public void importAddress(String address) {
			btcdClient.importAddress(address);
			printResult(Commands.IMPORT_ADDRESS.getName(), new String[]{"address"}, 
					new Object[]{address}, null);
		}

		public void importAddress(String address, String account) {
			btcdClient.importAddress(address, account);
			printResult(Commands.IMPORT_ADDRESS.getName(), new String[]{"address", "account"},
					new Object[]{address, account}, null);
		}

		public void importAddress(String address, String account, boolean withRescan) {
			btcdClient.importAddress(address, account, withRescan);
			printResult(Commands.IMPORT_ADDRESS.getName(), new String[]{"address", "account", 
			"withRescan"}, new Object[]{address, account, withRescan}, null);
		}

		public void importPrivKey(String privateKey) {
			btcdClient.importPrivKey(privateKey);
			printResult(Commands.IMPORT_PRIV_KEY.getName(), new String[]{"privateKey"}, 
					new Object[]{privateKey}, null);
		}

		public void importPrivKey(String privateKey, String account) {
			btcdClient.importPrivKey(privateKey, account);
			printResult(Commands.IMPORT_PRIV_KEY.getName(), new String[]{"privateKey", "account"},
					new Object[]{privateKey, account}, null);
		}

		public void importPrivKey(String privateKey, String account, boolean withRescan) {
			btcdClient.importPrivKey(privateKey, account, withRescan);
			printResult(Commands.IMPORT_PRIV_KEY.getName(), new String[]{"privateKey", "account", 
					"withRescan"}, new Object[]{privateKey, account, withRescan}, null);
		}

		public void importWallet(String filePath) {
			btcdClient.importWallet(filePath);
			printResult(Commands.IMPORT_WALLET.getName(), new String[]{"filePath"}, 
					new Object[]{filePath}, null);
		}
		
		public void keyPoolRefill() {
			btcdClient.keyPoolRefill();
			printResult(Commands.KEY_POOL_REFILL.getName(), null, null, null);
		}


		public void keyPoolRefill(int keypoolSize) {
			btcdClient.keyPoolRefill(keypoolSize);
			printResult(Commands.KEY_POOL_REFILL.getName(), new String[]{"keypoolSize"}, 
					new Object[]{keypoolSize}, null);
		}

		private void listAccounts() {
			Map<String, BigDecimal> accounts = btcdClient.listAccounts();
			printResult(Commands.LIST_ACCOUNTS.getName(), null, null, accounts);
		}

		private void listAccounts(int confirmations) {
			Map<String, BigDecimal> accounts = btcdClient.listAccounts(confirmations);
			printResult(Commands.LIST_ACCOUNTS.getName(), new String[]{"confirmations"}, 
					new Object[]{confirmations}, accounts);
		}

		private void listAccounts(int confirmations, boolean withWatchOnly) {
			Map<String, BigDecimal> accounts = btcdClient.listAccounts(confirmations, withWatchOnly);
			printResult(Commands.LIST_ACCOUNTS.getName(), new String[]{"confirmations", 
			"withWatchOnly"}, new Object[]{confirmations, withWatchOnly}, accounts);			
		}

		public void setAccount(String address, String account) {
			btcdClient.setAccount(address, account);
			printResult(Commands.SET_ACCOUNT.getName(), new String[]{"address", "account"},
					new Object[]{address, account}, null);
		}

		private void setGenerate(boolean isGenerate) {
			btcdClient.setGenerate(isGenerate);
			printResult(Commands.SET_GENERATE.getName(), new String[]{"isGenerate"}, 
					new Object[]{isGenerate}, null);
		}

		private void setGenerate(boolean isGenerate, int processors) {
			btcdClient.setGenerate(isGenerate, processors);
			printResult(Commands.SET_GENERATE.getName(), new String[]{"isGenerate", "processors"},
					new Object[]{isGenerate, processors}, null);
		}

		private void setTxFee(BigDecimal txFee) {
			Boolean result = btcdClient.setTxFee(txFee);
			printResult(Commands.SET_TX_FEE.getName(), new String[]{"txFee"}, new Object[]{txFee},
					result);
		}

		private void stop() {
			String noticeMsg = btcdClient.stop();
			printResult(Commands.STOP.getName(), null, null, noticeMsg);
		}

		private void walletLock() {
			btcdClient.walletLock();
			printResult(Commands.WALLET_LOCK.getName(), null, null, null);
		}

		private void walletPassphrase(String passphrase, int authTimeout) {
			btcdClient.walletPassphrase(passphrase, authTimeout);
			printResult(Commands.WALLET_PASSPHRASE.getName(), new String[]{"passphrase", "authTimeout"},
					new Object[]{passphrase, authTimeout}, null);
		}

		private void walletPassphraseChange(String curPassphrase, String newPassphrase) {
			btcdClient.walletPassphraseChange(curPassphrase, newPassphrase);
			printResult(Commands.WALLET_PASSPHRASE_CHANGE.getName(), new String[]{"curPassphrase", 
			"newPassphrase"}, new Object[]{curPassphrase, newPassphrase}, null);
		}	
	}

	private static void printResult(String methodName, String[] paramNames, Object[] paramValues,
			Object result) {
		List<Object> printables = new ArrayList<Object>();
		printables.add(methodName);
		if(!(paramNames == null || paramValues == null)) {
			printables.addAll(CollectionUtils.mergeInterlaced(Arrays.asList(paramNames), 
					Arrays.asList(paramValues)));
		}
		printables.add(result);
		System.out.printf("'bitcoind' response for API call '%s(" + StringUtils.repeat("%s=%s, ", 
				(printables.size() - 2)/2).replaceAll(", $", "") + ")' was: '%s'\n", 
				printables.toArray());
	}
}