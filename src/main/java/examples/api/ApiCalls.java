package examples.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.client.HttpClient;

import com.neemre.btcdcli4j.Commands;
import com.neemre.btcdcli4j.client.BtcdClient;
import com.neemre.btcdcli4j.client.BtcdClientImpl;
import com.neemre.btcdcli4j.domain.Account;
import com.neemre.btcdcli4j.domain.Address;
import com.neemre.btcdcli4j.domain.AddressInfo;
import com.neemre.btcdcli4j.domain.AddressOverview;
import com.neemre.btcdcli4j.domain.Block;
import com.neemre.btcdcli4j.domain.Info;
import com.neemre.btcdcli4j.domain.MemPoolInfo;
import com.neemre.btcdcli4j.domain.MiningInfo;
import com.neemre.btcdcli4j.domain.MultiSigAddress;
import com.neemre.btcdcli4j.domain.Output;
import com.neemre.btcdcli4j.domain.OutputOverview;
import com.neemre.btcdcli4j.domain.Payment;
import com.neemre.btcdcli4j.domain.PeerNode;
import com.neemre.btcdcli4j.domain.RawTransaction;
import com.neemre.btcdcli4j.domain.RedeemScript;
import com.neemre.btcdcli4j.domain.SinceBlock;
import com.neemre.btcdcli4j.domain.Transaction;
import com.neemre.btcdcli4j.domain.WalletInfo;

import static examples.util.ExampleUtils.printResult;

public class ApiCalls {

	private BtcdClient btcdClient;


	public ApiCalls(HttpClient httpProvider, Properties nodeConfig) {
		btcdClient = new BtcdClientImpl(httpProvider, nodeConfig);
	}

	public void addMultiSigAddress(int minSignatures, List<String> addresses) {
		String multiSigAddress = btcdClient.addMultiSigAddress(minSignatures, addresses);
		printResult(Commands.ADD_MULTI_SIG_ADDRESS.getName(), new String[]{"minSignatures",
			"addresses"}, new Object[]{minSignatures, addresses}, multiSigAddress);
	}
	
	public void addMultiSigAddress(int minSignatures, List<String> addresses, String account) {
		String multiSigAddress = btcdClient.addMultiSigAddress(minSignatures, addresses, 
				account);
		printResult(Commands.ADD_MULTI_SIG_ADDRESS.getName(), new String[]{"minSignatures",
			"addresses", "account"}, new Object[]{minSignatures, addresses, account}, 
			multiSigAddress);
	}
	
	public void backupWallet(String filePath) {
		btcdClient.backupWallet(filePath);
		printResult(Commands.BACKUP_WALLET.getName(), new String[]{"filePath"}, 
				new Object[]{filePath}, null);
	}
	
	public void createMultiSig(int minSignatures, List<String> addresses) {
		MultiSigAddress multiSigAddress = btcdClient.createMultiSig(minSignatures, addresses);
		printResult(Commands.CREATE_MULTI_SIG.getName(), new String[]{"minSignatures", 
				"addresses"}, new Object[]{minSignatures, addresses}, multiSigAddress);
	}
	
	public void createRawTransaction(List<OutputOverview> outputs, 
			Map<String, BigDecimal> toAddresses) {
		String hexTransaction = btcdClient.createRawTransaction(outputs, toAddresses);
		printResult(Commands.CREATE_RAW_TRANSACTION.getName(), new String[]{"outputs", 
			"toAddresses"}, new Object[]{outputs, toAddresses}, hexTransaction);
	}
	
	public void decodeRawTransaction(String hexTransaction) {
		RawTransaction rawTransaction = btcdClient.decodeRawTransaction(hexTransaction);
		printResult(Commands.DECODE_RAW_TRANSACTION.getName(), new String[]{"hexTransaction"},
				new Object[]{hexTransaction}, rawTransaction);
	}
	
	public void decodeScript(String hexRedeemScript) {
		RedeemScript redeemScript = btcdClient.decodeScript(hexRedeemScript);
		printResult(Commands.DECODE_SCRIPT.getName(), new String[]{"hexRedeemScript"}, 
				new Object[]{hexRedeemScript}, redeemScript);
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
	
	public void encryptWallet(String passphrase) {
		String noticeMsg = btcdClient.encryptWallet(passphrase);
		printResult(Commands.ENCRYPT_WALLET.getName(), new String[]{"passphrase"}, 
				new Object[]{passphrase}, noticeMsg);
	}	

	public void getAccount(String address) {
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

	public void getBalance() {
		BigDecimal balance = btcdClient.getBalance();
		printResult(Commands.GET_BALANCE.getName(), null, null, balance);
	}

	public void getBalance(String account) {
		BigDecimal balance = btcdClient.getBalance(account);
		printResult(Commands.GET_BALANCE.getName(), new String[]{"account"}, new Object[]{
				account}, balance);
	}

	public void getBalance(String account, int confirmations) {
		BigDecimal balance = btcdClient.getBalance(account, confirmations);
		printResult(Commands.GET_BALANCE.getName(), new String[]{"account", "confirmations"}, 
				new Object[]{account, confirmations}, balance);
	}

	public void getBalance(String account, int confirmations, boolean withWatchOnly) {
		BigDecimal balance = btcdClient.getBalance(account, confirmations, withWatchOnly);
		printResult(Commands.GET_BALANCE.getName(), new String[]{"account", "confirmations", 
		"withWatchOnly"}, new Object[]{account, confirmations, withWatchOnly}, balance);
	}

	public void getBestBlockHash() {
		String headerHash = btcdClient.getBestBlockHash();
		printResult(Commands.GET_BEST_BLOCK_HASH.getName(), null, null, headerHash);
	}

	public void getBlock(String headerHash) {
		Block block = btcdClient.getBlock(headerHash);
		printResult(Commands.GET_BLOCK.getName(), new String[]{"headerHash"}, 
				new Object[]{headerHash}, block);
	}
	
	public void getBlock(String headerHash, boolean isDecoded) {
		Object block = btcdClient.getBlock(headerHash, isDecoded);
		printResult(Commands.GET_BLOCK.getName(), new String[]{"headerHash", "isDecoded"}, 
				new Object[]{headerHash, isDecoded}, block);
	}

	public void getBlockCount() {
		Integer blockHeight = btcdClient.getBlockCount();
		printResult(Commands.GET_BLOCK_COUNT.getName(), null, null, blockHeight);
	}

	public void getBlockHash(Integer blockHeight) {
		String headerHash = btcdClient.getBlockHash(blockHeight);
		printResult(Commands.GET_BLOCK_HASH.getName(), new String[]{"blockHeight"}, 
				new Object[]{blockHeight}, headerHash);
	}

	public void getDifficulty() {
		BigDecimal difficulty = btcdClient.getDifficulty();
		printResult(Commands.GET_DIFFICULTY.getName(), null, null, difficulty);
	}

	public void getGenerate() {
		Boolean isGenerate = btcdClient.getGenerate();
		printResult(Commands.GET_GENERATE.getName(), null, null, isGenerate);
	}

	public void getHashesPerSec() {
		Long hashesPerSec = btcdClient.getHashesPerSec();
		printResult(Commands.GET_HASHES_PER_SEC.getName(), null, null, hashesPerSec);
	}

	public void getInfo() {
		Info info = btcdClient.getInfo();
		printResult(Commands.GET_INFO.getName(), null, null, info);
	}

	public void getMemPoolInfo() {
		MemPoolInfo memPoolInfo = btcdClient.getMemPoolInfo();
		printResult(Commands.GET_MEM_POOL_INFO.getName(), null, null, memPoolInfo);
	}

	public void getMiningInfo() {
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
	
	public void getRawTransaction(String txId) {
		String hexTransaction = btcdClient.getRawTransaction(txId);
		printResult(Commands.GET_RAW_TRANSACTION.getName(), new String[]{"txId"}, 
				new Object[]{txId}, hexTransaction);
	}
	
	public void getRawTransaction(String txId, int verbosity) {
		Object transaction = btcdClient.getRawTransaction(txId, verbosity);
		printResult(Commands.GET_RAW_TRANSACTION.getName(), new String[]{"txId", "verbosity"}, 
				new Object[]{txId, verbosity}, transaction);
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

	public void getTransaction(String txId) {
		Transaction transaction = btcdClient.getTransaction(txId);
		printResult(Commands.GET_TRANSACTION.getName(), new String[]{"txId"}, new Object[]{txId},
				transaction);
	}
	
	public void getTransaction(String txId, boolean withWatchOnly) {
		Transaction transaction = btcdClient.getTransaction(txId, withWatchOnly);
		printResult(Commands.GET_TRANSACTION.getName(), new String[]{"txId", "withWatchOnly"},
				new Object[]{txId, withWatchOnly}, transaction);
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

	public void listAccounts() {
		Map<String, BigDecimal> accounts = btcdClient.listAccounts();
		printResult(Commands.LIST_ACCOUNTS.getName(), null, null, accounts);
	}

	public void listAccounts(int confirmations) {
		Map<String, BigDecimal> accounts = btcdClient.listAccounts(confirmations);
		printResult(Commands.LIST_ACCOUNTS.getName(), new String[]{"confirmations"}, 
				new Object[]{confirmations}, accounts);
	}

	public void listAccounts(int confirmations, boolean withWatchOnly) {
		Map<String, BigDecimal> accounts = btcdClient.listAccounts(confirmations, withWatchOnly);
		printResult(Commands.LIST_ACCOUNTS.getName(), new String[]{"confirmations", 
				"withWatchOnly"}, new Object[]{confirmations, withWatchOnly}, accounts);			
	}
	
	public void listAddressGroupings() {
		List<List<AddressOverview>> groupings = btcdClient.listAddressGroupings();
		printResult(Commands.LIST_ADDRESS_GROUPINGS.getName(), null, null, groupings);
	}
	
	public void listLockUnspent() {
		List<OutputOverview> lockedOutputs = btcdClient.listLockUnspent();
		printResult(Commands.LIST_LOCK_UNSPENT.getName(), null, null, lockedOutputs);
	}
	
	public void listReceivedByAccount() {
		List<Account> accounts = btcdClient.listReceivedByAccount();
		printResult(Commands.LIST_RECEIVED_BY_ACCOUNT.getName(), null, null, accounts);
	}
	
	public void listReceivedByAccount(int confirmations) {
		List<Account> accounts = btcdClient.listReceivedByAccount(confirmations);
		printResult(Commands.LIST_RECEIVED_BY_ACCOUNT.getName(), new String[]{"confirmations"},
				new Object[]{confirmations}, accounts);
	}
	
	public void listReceivedByAccount(int confirmations, boolean withUnused) {
		List<Account> accounts = btcdClient.listReceivedByAccount(confirmations, withUnused);
		printResult(Commands.LIST_RECEIVED_BY_ACCOUNT.getName(), new String[]{"confirmations", 
				"withUnused"}, new Object[]{confirmations, withUnused}, accounts);
	}
	
	public void listReceivedByAccount(int confirmations, boolean withUnused, 
			boolean withWatchOnly) {
		List<Account> accounts = btcdClient.listReceivedByAccount(confirmations, withUnused, 
				withWatchOnly);
		printResult(Commands.LIST_RECEIVED_BY_ACCOUNT.getName(), new String[]{"confirmations", 
				"withUnused", "withWatchOnly"}, new Object[]{confirmations, withUnused, 
				withWatchOnly}, accounts);
	}		
	
	public void listReceivedByAddress() {
		List<Address> addresses = btcdClient.listReceivedByAddress();
		printResult(Commands.LIST_RECEIVED_BY_ADDRESS.getName(), null, null, addresses);
	}

	public void listReceivedByAddress(int confirmations) {
		List<Address> addresses = btcdClient.listReceivedByAddress(confirmations);
		printResult(Commands.LIST_RECEIVED_BY_ADDRESS.getName(), new String[]{"confirmations"},
				new Object[]{confirmations}, addresses);
	}
	
	public void listReceivedByAddress(int confirmations, boolean withUnused) {
		List<Address> addresses = btcdClient.listReceivedByAddress(confirmations, withUnused);
		printResult(Commands.LIST_RECEIVED_BY_ADDRESS.getName(), new String[]{"confirmations",
				"withUnused"}, new Object[]{confirmations, withUnused}, addresses);
	}

	public void listReceivedByAddress(int confirmations, boolean withUnused, 
			boolean withWatchOnly) {
		List<Address> addresses = btcdClient.listReceivedByAddress(confirmations, withUnused,
				withWatchOnly);
		printResult(Commands.LIST_RECEIVED_BY_ADDRESS.getName(), new String[]{"confirmations", 
				"withUnused", "withWatchOnly"}, new Object[]{confirmations, withUnused, 
				withWatchOnly}, addresses);
	}
	
	public void listSinceBlock() {
		SinceBlock sinceBlock = btcdClient.listSinceBlock();
		printResult(Commands.LIST_SINCE_BLOCK.getName(), null, null, sinceBlock);
	}
	
	public void listSinceBlock(String headerHash) {
		SinceBlock sinceBlock = btcdClient.listSinceBlock(headerHash);
		printResult(Commands.LIST_SINCE_BLOCK.getName(), new String[]{"headerHash"}, 
				new Object[]{headerHash}, sinceBlock);
	}
	
	public void listSinceBlock(String headerHash, int confirmations) {
		SinceBlock sinceBlock = btcdClient.listSinceBlock(headerHash, confirmations);
		printResult(Commands.LIST_SINCE_BLOCK.getName(), new String[]{"headerHash", 
				"confirmations"}, new Object[]{headerHash, confirmations}, sinceBlock);
	}
	
	public void listSinceBlock(String headerHash, int confirmations, boolean withWatchOnly) {
		SinceBlock sinceBlock = btcdClient.listSinceBlock(headerHash, confirmations, 
				withWatchOnly);
		printResult(Commands.LIST_SINCE_BLOCK.getName(), new String[]{"headerHash", 
				"confirmations", "withWatchOnly"}, new Object[]{headerHash, confirmations, 
				withWatchOnly}, sinceBlock);
	}

	public void listTransactions() {
		List<Payment> payments = btcdClient.listTransactions();
		printResult(Commands.LIST_TRANSACTIONS.getName(), null, null, payments);
	}

	public void listTransactions(String account) {
		List<Payment> payments = btcdClient.listTransactions(account);
		printResult(Commands.LIST_TRANSACTIONS.getName(), new String[]{"account"}, 
				new Object[]{account}, payments);
	}

	public void listTransactions(String account, int count) {
		List<Payment> payments = btcdClient.listTransactions(account, count);
		printResult(Commands.LIST_TRANSACTIONS.getName(), new String[]{"account", "count"},
				new Object[]{account, count}, payments);
	}

	public void listTransactions(String account, int count, int offset) {
		List<Payment> payments = btcdClient.listTransactions(account, count, offset);
		printResult(Commands.LIST_TRANSACTIONS.getName(), new String[]{"account", "count", 
				"offset"}, new Object[]{account, count, offset}, payments);
	}

	public void listTransactions(String account, int count, int offset, boolean withWatchOnly) {
		List<Payment> payments = btcdClient.listTransactions(account, count, offset,
				withWatchOnly);
		printResult(Commands.LIST_TRANSACTIONS.getName(), new String[]{"account", "count",
				"offset", "withWatchOnly"}, new Object[]{account, count, offset, withWatchOnly},
				payments);
	}
	
	public void listUnspent() {
		List<Output> unspentOutputs = btcdClient.listUnspent();
		printResult(Commands.LIST_UNSPENT.getName(), null, null, unspentOutputs);
	}

	public void listUnspent(int minConfirmations) {
		List<Output> unspentOutputs = btcdClient.listUnspent(minConfirmations);
		printResult(Commands.LIST_UNSPENT.getName(), new String[]{"minConfirmations"}, 
				new Object[]{minConfirmations}, unspentOutputs);
	}

	public void listUnspent(int minConfirmations, int maxConfirmations) {
		List<Output> unspentOutputs = btcdClient.listUnspent(minConfirmations, maxConfirmations);
		printResult(Commands.LIST_UNSPENT.getName(), new String[]{"minConfirmations", 
				"maxConfirmations"}, new Object[]{minConfirmations, maxConfirmations}, 
				unspentOutputs);
	}

	public void listUnspent(int minConfirmations, int maxConfirmations, List<String> addresses) {
		List<Output> unspentOutputs = btcdClient.listUnspent(minConfirmations, maxConfirmations,
				addresses);
		printResult(Commands.LIST_UNSPENT.getName(), new String[]{"minConfirmations",
				"maxConfirmations", "addresses"}, new Object[]{minConfirmations, 
				maxConfirmations, addresses}, unspentOutputs);
	}
	
	public void lockUnspent(Boolean isUnlocked) {
		Boolean isSuccess = btcdClient.lockUnspent(isUnlocked);
		printResult(Commands.LOCK_UNSPENT.getName(), new String[]{"isUnlocked"}, 
				new Object[]{isUnlocked}, isSuccess);
	}

	public void lockUnspent(boolean isUnlocked, List<OutputOverview> outputs) {
		Boolean isSuccess = btcdClient.lockUnspent(isUnlocked, outputs);
		printResult(Commands.LOCK_UNSPENT.getName(), new String[]{"isUnlocked", "outputs"}, 
				new Object[]{isUnlocked, outputs}, isSuccess);
	}
	
	public void move(String fromAccount, String toAccount, BigDecimal amount) {
		Boolean isSuccess = btcdClient.move(fromAccount, toAccount, amount);
		printResult(Commands.MOVE.getName(), new String[]{"fromAccount", "toAccount", "amount"},
				new Object[]{fromAccount, toAccount, amount}, isSuccess);
	}
	
	public void move(String fromAccount, String toAccount, BigDecimal amount, Integer dummy,
			String comment) {
		Boolean isSuccess = btcdClient.move(fromAccount, toAccount, amount, dummy, comment);
		printResult(Commands.MOVE.getName(), new String[]{"fromAccount", "toAccount", "amount", 
				"dummy", "comment"}, new Object[]{fromAccount, toAccount, amount, dummy, comment},
				isSuccess);
	}
	
	public void ping() {
		btcdClient.ping();
		printResult(Commands.PING.getName(), null, null, null);
	}
	
	public void sendFrom(String fromAccount, String toAddress, BigDecimal amount) {
		String transactionId = btcdClient.sendFrom(fromAccount, toAddress, amount);
		printResult(Commands.SEND_FROM.getName(), new String[]{"fromAccount", "toAddress", 
				"amount"}, new Object[]{fromAccount, toAddress, amount}, transactionId);
	}
	
	public void sendFrom(String fromAccount, String toAddress, BigDecimal amount, 
			Integer confirmations) {
		String transactionId = btcdClient.sendFrom(fromAccount, toAddress, amount, confirmations);
		printResult(Commands.SEND_FROM.getName(), new String[]{"fromAccount", "toAddress", 
				"amount", "confirmations"}, new Object[]{fromAccount, toAddress, amount, 
				confirmations}, transactionId);
	}
	
	public void sendFrom(String fromAccount, String toAddress, BigDecimal amount, 
			Integer confirmations, String comment) {
		String transactionId = btcdClient.sendFrom(fromAccount, toAddress, amount, confirmations,
				comment);
		printResult(Commands.SEND_FROM.getName(), new String[]{"fromAccount", "toAddress", 
				"amount", "confirmations", "comment"}, new Object[]{fromAccount, toAddress, 
				amount, confirmations, comment}, transactionId);
	}
	
	public void sendFrom(String fromAccount, String toAddress, BigDecimal amount, 
			Integer confirmations, String comment, String commentTo) {
		String transactionId = btcdClient.sendFrom(fromAccount, toAddress, amount, confirmations,
				comment, commentTo);
		printResult(Commands.SEND_FROM.getName(), new String[]{"fromAccount", "toAddress", 
				"amount", "confirmations", "comment", "commentTo"}, new Object[]{fromAccount, 
				toAddress, amount, confirmations, comment, commentTo}, transactionId);
	}

	public void sendMany(String fromAccount, Map<String, BigDecimal> toAddresses) {
		String transactionId = btcdClient.sendMany(fromAccount, toAddresses);
		printResult(Commands.SEND_MANY.getName(), new String[]{"fromAccount", "toAddresses"}, 
				new Object[]{fromAccount, toAddresses}, transactionId);
	}
	
	public void sendMany(String fromAccount, Map<String, BigDecimal> toAddresses, 
			int confirmations) {
		String transactionId = btcdClient.sendMany(fromAccount, toAddresses, confirmations);
		printResult(Commands.SEND_MANY.getName(), new String[]{"fromAccount", "toAddresses", 
			"confirmations"}, new Object[]{fromAccount, toAddresses, confirmations},
			transactionId);
	}
	
	public void sendMany(String fromAccount, Map<String, BigDecimal> toAddresses, 
			int confirmations, String comment) {
		String transactionId = btcdClient.sendMany(fromAccount, toAddresses, confirmations, 
				comment);
		printResult(Commands.SEND_MANY.getName(), new String[]{"fromAccount", "toAddresses", 
			"confirmations", "comment"}, new Object[]{fromAccount, toAddresses, confirmations, 
			comment}, transactionId);
	}
	
	public void sendRawTransaction(String hexTransaction) {
		String transactionId = btcdClient.sendRawTransaction(hexTransaction);
		printResult(Commands.SEND_RAW_TRANSACTION.getName(), new String[]{"hexTransaction"}, 
				new Object[]{hexTransaction}, transactionId);
	}

	public void sendRawTransaction(String hexTransaction, boolean withHighFees) {
		String transactionId = btcdClient.sendRawTransaction(hexTransaction, withHighFees);
		printResult(Commands.SEND_RAW_TRANSACTION.getName(), new String[]{"hexTransaction", 
				"withHighFees"}, new Object[]{hexTransaction, withHighFees}, transactionId);
	}
	
	public void sendToAddress(String toAddress, BigDecimal amount) {
		String transactionId = btcdClient.sendToAddress(toAddress, amount);
		printResult(Commands.SEND_TO_ADDRESS.getName(), new String[]{"toAddress", "amount"},
				new Object[]{toAddress, amount}, transactionId);
	}

	public void sendToAddress(String toAddress, BigDecimal amount, String comment) {
		String transactionId = btcdClient.sendToAddress(toAddress, amount, comment);
		printResult(Commands.SEND_TO_ADDRESS.getName(), new String[]{"toAddress", "amount", 
				"comment"}, new Object[]{toAddress, amount, comment}, transactionId);
	}

	public void sendToAddress(String toAddress, BigDecimal amount, String comment, 
			String commentTo) {
		String transactionId = btcdClient.sendToAddress(toAddress, amount, comment, commentTo);
		printResult(Commands.SEND_TO_ADDRESS.getName(), new String[]{"toAddress", "amount",
				"comment", "commentTo"}, new Object[]{toAddress, amount, comment, commentTo}, 
				transactionId);
	}

	public void setAccount(String address, String account) {
		btcdClient.setAccount(address, account);
		printResult(Commands.SET_ACCOUNT.getName(), new String[]{"address", "account"},
				new Object[]{address, account}, null);
	}

	public void setGenerate(boolean isGenerate) {
		btcdClient.setGenerate(isGenerate);
		printResult(Commands.SET_GENERATE.getName(), new String[]{"isGenerate"}, 
				new Object[]{isGenerate}, null);
	}

	public void setGenerate(boolean isGenerate, int processors) {
		btcdClient.setGenerate(isGenerate, processors);
		printResult(Commands.SET_GENERATE.getName(), new String[]{"isGenerate", "processors"},
				new Object[]{isGenerate, processors}, null);
	}

	public void setTxFee(BigDecimal txFee) {
		Boolean isSuccess = btcdClient.setTxFee(txFee);
		printResult(Commands.SET_TX_FEE.getName(), new String[]{"txFee"}, new Object[]{txFee},
				isSuccess);
	}
	
	public void signMessage(String address, String message) {
		String signature = btcdClient.signMessage(address, message);
		printResult(Commands.SIGN_MESSAGE.getName(), new String[]{"address", "message"}, 
				new Object[]{address, message}, signature);
	}
	
	public void signRawTransaction(String hexTransaction) {
		// TODO Auto-generated method stub
		
	}

	public void signRawTransaction(String hexTransaction, List<Output> outputs) {
		// TODO Auto-generated method stub
		
	}

	public void signRawTransaction(String hexTransaction, List<Output> outputs, 
			List<String> privateKeys) {
		// TODO Auto-generated method stub
		
	}

	public void signRawTransaction(String hexTransaction, List<Output> outputs, 
			List<String> privateKeys, String sigHashType) {
		// TODO Auto-generated method stub
		
	}
	
	public void stop() {
		String noticeMsg = btcdClient.stop();
		printResult(Commands.STOP.getName(), null, null, noticeMsg);
	}

	public void validateAddress(String address) {
		AddressInfo addressInfo = btcdClient.validateAddress(address);
		printResult(Commands.VALIDATE_ADDRESS.getName(), new String[]{"address"}, 
				new Object[]{address}, addressInfo);
	}
	
	public void verifyMessage(String address, String signature, String message) {
		Boolean isSigValid = btcdClient.verifyMessage(address, signature, message);
		printResult(Commands.VERIFY_MESSAGE.getName(), new String[]{"address", "signature", 
				"message"}, new Object[]{address, signature, message}, isSigValid);
	}
	
	public void walletLock() {
		btcdClient.walletLock();
		printResult(Commands.WALLET_LOCK.getName(), null, null, null);
	}

	public void walletPassphrase(String passphrase, int authTimeout) {
		btcdClient.walletPassphrase(passphrase, authTimeout);
		printResult(Commands.WALLET_PASSPHRASE.getName(), new String[]{"passphrase", "authTimeout"},
				new Object[]{passphrase, authTimeout}, null);
	}

	public void walletPassphraseChange(String curPassphrase, String newPassphrase) {
		btcdClient.walletPassphraseChange(curPassphrase, newPassphrase);
		printResult(Commands.WALLET_PASSPHRASE_CHANGE.getName(), new String[]{"curPassphrase", 
				"newPassphrase"}, new Object[]{curPassphrase, newPassphrase}, null);
	}	
}