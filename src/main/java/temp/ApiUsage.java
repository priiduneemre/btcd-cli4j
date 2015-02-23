package temp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import com.neemre.btcdcli4j.util.CollectionUtils;

public class ApiUsage {
	
	/**A list of examples for invoking any of the <i>bitcoind</i> API commands currently supported 
	 * by btcd-cli4j (as of 0.1.0). Calling any of the methods below will cause a short overview 
	 * (<i>i.e.</i> of the results) to be written to {@code stdout}.*/
	public static void main(String[] args) throws Exception {
		HttpClient httpProvider = ExampleUtils.getHttpProvider();
		Properties nodeConfig = ExampleUtils.getNodeConfig();
		ApiCalls sampleCalls = new ApiCalls(httpProvider, nodeConfig);
		
		sampleCalls.encryptWallet("strawberry");
		sampleCalls.getBalance();
		sampleCalls.getBalance("");
		sampleCalls.getBalance("", 6);
		sampleCalls.getBalance("", 6, true);
		sampleCalls.getDifficulty();
		sampleCalls.getGenerate();
		sampleCalls.getHashesPerSec();
		sampleCalls.getInfo();
		sampleCalls.getMemPoolInfo();
		sampleCalls.getMiningInfo();
		sampleCalls.setGenerate(false);
		sampleCalls.setGenerate(false, 7);
		//apiCalls.stop();
		sampleCalls.walletLock();
		sampleCalls.walletPassphrase("strawberry", Defaults.WALLET_AUTH_TIMEOUT);
		sampleCalls.walletPassphraseChange("strawberry", "raspberry");
	}

	static class ApiCalls {

		private BtcdClient btcdClient;


		public ApiCalls(HttpClient httpProvider, Properties nodeConfig) {
			btcdClient = new BtcdClientImpl(httpProvider, nodeConfig);
		}

		private void encryptWallet(String passphrase) {
			String noticeMsg = btcdClient.encryptWallet(passphrase);
			printResult(Commands.ENCRYPT_WALLET.getName(), new String[]{"passphrase"}, 
					new Object[]{passphrase}, noticeMsg);
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

		private void getBalance(String account, int confirmations, boolean hasWatchOnly) {
			BigDecimal balance = btcdClient.getBalance(account, confirmations, hasWatchOnly);
			printResult(Commands.GET_BALANCE.getName(), new String[]{"account", "confirmations", 
			"hasWatchOnly"}, new Object[]{account, confirmations, hasWatchOnly}, balance);
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