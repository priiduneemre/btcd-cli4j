package com.neemre.btcdcli4j.examples.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.impl.client.CloseableHttpClient;

import com.neemre.btcdcli4j.core.BitcoindException;
import com.neemre.btcdcli4j.core.Commands;
import com.neemre.btcdcli4j.core.CommunicationException;
import com.neemre.btcdcli4j.core.client.BtcdClient;
import com.neemre.btcdcli4j.core.client.BtcdClientImpl;
import com.neemre.btcdcli4j.core.domain.Account;
import com.neemre.btcdcli4j.core.domain.Address;
import com.neemre.btcdcli4j.core.domain.AddressInfo;
import com.neemre.btcdcli4j.core.domain.AddressOverview;
import com.neemre.btcdcli4j.core.domain.Block;
import com.neemre.btcdcli4j.core.domain.Info;
import com.neemre.btcdcli4j.core.domain.MemPoolInfo;
import com.neemre.btcdcli4j.core.domain.MiningInfo;
import com.neemre.btcdcli4j.core.domain.MultiSigAddress;
import com.neemre.btcdcli4j.core.domain.Output;
import com.neemre.btcdcli4j.core.domain.OutputOverview;
import com.neemre.btcdcli4j.core.domain.Payment;
import com.neemre.btcdcli4j.core.domain.PeerNode;
import com.neemre.btcdcli4j.core.domain.RawTransactionOverview;
import com.neemre.btcdcli4j.core.domain.RedeemScript;
import com.neemre.btcdcli4j.core.domain.SignatureResult;
import com.neemre.btcdcli4j.core.domain.SinceBlock;
import com.neemre.btcdcli4j.core.domain.Transaction;
import com.neemre.btcdcli4j.core.domain.WalletInfo;

import static com.neemre.btcdcli4j.examples.util.OutputUtils.printResult;

public class ApiCalls {

	private BtcdClient btcdClient;


	public ApiCalls(CloseableHttpClient httpProvider, Properties nodeConfig) {
		btcdClient = new BtcdClientImpl(httpProvider, nodeConfig);
	}

	public void addMultiSigAddress(int minSignatures, List<String> addresses) 
			throws BitcoindException, CommunicationException {
		String multiSigAddress = btcdClient.addMultiSigAddress(minSignatures, addresses);
		printResult(Commands.ADD_MULTI_SIG_ADDRESS.getName(), new String[]{"minSignatures",
				"addresses"}, new Object[]{minSignatures, addresses}, multiSigAddress);
	}
	
	public void addMultiSigAddress(int minSignatures, List<String> addresses, String account)
			throws BitcoindException, CommunicationException {
		String multiSigAddress = btcdClient.addMultiSigAddress(minSignatures, addresses, 
				account);
		printResult(Commands.ADD_MULTI_SIG_ADDRESS.getName(), new String[]{"minSignatures",
				"addresses", "account"}, new Object[]{minSignatures, addresses, account}, 
				multiSigAddress);
	}
	
	public void backupWallet(String filePath) throws BitcoindException, CommunicationException {
		btcdClient.backupWallet(filePath);
		printResult(Commands.BACKUP_WALLET.getName(), new String[]{"filePath"}, 
				new Object[]{filePath}, null);
	}
	
	public void createMultiSig(int minSignatures, List<String> addresses)
			throws BitcoindException, CommunicationException {
		MultiSigAddress multiSigAddress = btcdClient.createMultiSig(minSignatures, addresses);
		printResult(Commands.CREATE_MULTI_SIG.getName(), new String[]{"minSignatures", 
				"addresses"}, new Object[]{minSignatures, addresses}, multiSigAddress);
	}
	
	public void createRawTransaction(List<OutputOverview> outputs, 
			Map<String, BigDecimal> toAddresses) throws BitcoindException, CommunicationException {
		String hexTransaction = btcdClient.createRawTransaction(outputs, toAddresses);
		printResult(Commands.CREATE_RAW_TRANSACTION.getName(), new String[]{"outputs", 
				"toAddresses"}, new Object[]{outputs, toAddresses}, hexTransaction);
	}
	
	public void decodeRawTransaction(String hexTransaction) throws BitcoindException, 
			CommunicationException {
		RawTransactionOverview rawTransaction = btcdClient.decodeRawTransaction(hexTransaction);
		printResult(Commands.DECODE_RAW_TRANSACTION.getName(), new String[]{"hexTransaction"},
				new Object[]{hexTransaction}, rawTransaction);
	}
	
	public void decodeScript(String hexRedeemScript) throws BitcoindException, 
			CommunicationException {
		RedeemScript redeemScript = btcdClient.decodeScript(hexRedeemScript);
		printResult(Commands.DECODE_SCRIPT.getName(), new String[]{"hexRedeemScript"}, 
				new Object[]{hexRedeemScript}, redeemScript);
	}
	
	public void dumpPrivKey(String address) throws BitcoindException, CommunicationException {
		String privateKey = btcdClient.dumpPrivKey(address);
		printResult(Commands.DUMP_PRIV_KEY.getName(), new String[]{"address"}, 
				new Object[]{address}, privateKey);
	}
	
	public void dumpWallet(String filePath) throws BitcoindException, CommunicationException {
		btcdClient.dumpWallet(filePath);
		printResult(Commands.DUMP_WALLET.getName(), new String[]{"filePath"}, 
				new Object[]{filePath}, null);
	}
	
	public void encryptWallet(String passphrase) throws BitcoindException, CommunicationException {
		String noticeMsg = btcdClient.encryptWallet(passphrase);
		printResult(Commands.ENCRYPT_WALLET.getName(), new String[]{"passphrase"}, 
				new Object[]{passphrase}, noticeMsg);
	}	

	public void estimateFee(int maxBlocks) throws BitcoindException, CommunicationException {
		BigDecimal estimatedFee = btcdClient.estimateFee(maxBlocks);
		printResult(Commands.ESTIMATE_FEE.getName(), new String[]{"maxBlocks"}, 
				new Object[]{maxBlocks}, estimatedFee);
	}	
	
	public void getAccount(String address) throws BitcoindException, CommunicationException {
		String account = btcdClient.getAccount(address);
		printResult(Commands.GET_ACCOUNT.getName(), new String[]{"address"}, new Object[]{address},
				account);
	}

	public void getAccountAddress(String account) throws BitcoindException, CommunicationException {
		String address = btcdClient.getAccountAddress(account);
		printResult(Commands.GET_ACCOUNT_ADDRESS.getName(), new String[]{"account"}, 
				new Object[]{account}, address);
	}

	public void getAddressesByAccount(String account) throws BitcoindException, 
			CommunicationException {
		List<String> addresses = btcdClient.getAddressesByAccount(account);
		printResult(Commands.GET_ADDRESSES_BY_ACCOUNT.getName(), new String[]{"account"},
				new Object[]{account}, addresses);
	}

	public void getBalance() throws BitcoindException, CommunicationException {
		BigDecimal balance = btcdClient.getBalance();
		printResult(Commands.GET_BALANCE.getName(), null, null, balance);
	}

	public void getBalance(String account) throws BitcoindException, CommunicationException {
		BigDecimal balance = btcdClient.getBalance(account);
		printResult(Commands.GET_BALANCE.getName(), new String[]{"account"}, new Object[]{
				account}, balance);
	}

	public void getBalance(String account, int confirmations) throws BitcoindException, 
			CommunicationException {
		BigDecimal balance = btcdClient.getBalance(account, confirmations);
		printResult(Commands.GET_BALANCE.getName(), new String[]{"account", "confirmations"}, 
				new Object[]{account, confirmations}, balance);
	}

	public void getBalance(String account, int confirmations, boolean withWatchOnly) 
			throws BitcoindException, CommunicationException {
		BigDecimal balance = btcdClient.getBalance(account, confirmations, withWatchOnly);
		printResult(Commands.GET_BALANCE.getName(), new String[]{"account", "confirmations", 
				"withWatchOnly"}, new Object[]{account, confirmations, withWatchOnly}, balance);
	}

	public void getBestBlockHash() throws BitcoindException, CommunicationException {
		String headerHash = btcdClient.getBestBlockHash();
		printResult(Commands.GET_BEST_BLOCK_HASH.getName(), null, null, headerHash);
	}

	public void getBlock(String headerHash) throws BitcoindException, CommunicationException {
		Block block = btcdClient.getBlock(headerHash);
		printResult(Commands.GET_BLOCK.getName(), new String[]{"headerHash"}, 
				new Object[]{headerHash}, block);
	}
	
	public void getBlock(String headerHash, boolean isDecoded) throws BitcoindException, 
			CommunicationException {
		Object block = btcdClient.getBlock(headerHash, isDecoded);
		printResult(Commands.GET_BLOCK.getName(), new String[]{"headerHash", "isDecoded"}, 
				new Object[]{headerHash, isDecoded}, block);
	}

	public void getBlockCount() throws BitcoindException, CommunicationException {
		Integer blockHeight = btcdClient.getBlockCount();
		printResult(Commands.GET_BLOCK_COUNT.getName(), null, null, blockHeight);
	}

	public void getBlockHash(int blockHeight) throws BitcoindException, CommunicationException {
		String headerHash = btcdClient.getBlockHash(blockHeight);
		printResult(Commands.GET_BLOCK_HASH.getName(), new String[]{"blockHeight"}, 
				new Object[]{blockHeight}, headerHash);
	}

	public void getDifficulty() throws BitcoindException, CommunicationException {
		BigDecimal difficulty = btcdClient.getDifficulty();
		printResult(Commands.GET_DIFFICULTY.getName(), null, null, difficulty);
	}

	public void getGenerate() throws BitcoindException, CommunicationException {
		Boolean isGenerate = btcdClient.getGenerate();
		printResult(Commands.GET_GENERATE.getName(), null, null, isGenerate);
	}

	public void getHashesPerSec() throws BitcoindException, CommunicationException {
		Long hashesPerSec = btcdClient.getHashesPerSec();
		printResult(Commands.GET_HASHES_PER_SEC.getName(), null, null, hashesPerSec);
	}

	public void getInfo() throws BitcoindException, CommunicationException {
		Info info = btcdClient.getInfo();
		printResult(Commands.GET_INFO.getName(), null, null, info);
	}

	public void getMemPoolInfo() throws BitcoindException, CommunicationException {
		MemPoolInfo memPoolInfo = btcdClient.getMemPoolInfo();
		printResult(Commands.GET_MEM_POOL_INFO.getName(), null, null, memPoolInfo);
	}

	public void getMiningInfo() throws BitcoindException, CommunicationException {
		MiningInfo miningInfo = btcdClient.getMiningInfo();
		printResult(Commands.GET_MINING_INFO.getName(), null, null, miningInfo);
	}

	public void getNewAddress() throws BitcoindException, CommunicationException {
		String address = btcdClient.getNewAddress();
		printResult(Commands.GET_NEW_ADDRESS.getName(), null, null, address);
	}

	public void getNewAddress(String account) throws BitcoindException, CommunicationException {
		String address = btcdClient.getNewAddress(account);
		printResult(Commands.GET_NEW_ADDRESS.getName(), new String[]{"account"}, 
				new Object[]{account}, address);
	}

	public void getPeerInfo() throws BitcoindException, CommunicationException {
		List<PeerNode> peerInfo = btcdClient.getPeerInfo();
		printResult(Commands.GET_PEER_INFO.getName(), null, null, peerInfo);
	}

	public void getRawChangeAddress() throws BitcoindException, CommunicationException {
		String address = btcdClient.getRawChangeAddress();
		printResult(Commands.GET_RAW_CHANGE_ADDRESS.getName(), null, null, address);
	}
	
	public void getRawTransaction(String txId) throws BitcoindException, CommunicationException {
		String hexTransaction = btcdClient.getRawTransaction(txId);
		printResult(Commands.GET_RAW_TRANSACTION.getName(), new String[]{"txId"}, 
				new Object[]{txId}, hexTransaction);
	}
	
	public void getRawTransaction(String txId, int verbosity) throws BitcoindException, 
			CommunicationException {
		Object transaction = btcdClient.getRawTransaction(txId, verbosity);
		printResult(Commands.GET_RAW_TRANSACTION.getName(), new String[]{"txId", "verbosity"}, 
				new Object[]{txId, verbosity}, transaction);
	}

	public void getReceivedByAccount(String account) throws BitcoindException, 
			CommunicationException {
		BigDecimal totalReceived = btcdClient.getReceivedByAccount(account);
		printResult(Commands.GET_RECEIVED_BY_ACCOUNT.getName(), new String[]{"account"},
				new Object[]{account}, totalReceived);
	}

	public void getReceivedByAccount(String account, int confirmations) throws BitcoindException, 
			CommunicationException {
		BigDecimal totalReceived = btcdClient.getReceivedByAccount(account, confirmations);
		printResult(Commands.GET_RECEIVED_BY_ACCOUNT.getName(), new String[]{"account", 
				"confirmations"}, new Object[]{account, confirmations}, totalReceived);
	}

	public void getReceivedByAddress(String address) throws BitcoindException, 
			CommunicationException {
		BigDecimal totalReceived = btcdClient.getReceivedByAddress(address);
		printResult(Commands.GET_RECEIVED_BY_ADDRESS.getName(), new String[]{"address"},
				new Object[]{address}, totalReceived);
	}

	public void getReceivedByAddress(String address, int confirmations) throws BitcoindException, 
			CommunicationException {
		BigDecimal totalReceived = btcdClient.getReceivedByAddress(address, confirmations);
		printResult(Commands.GET_RECEIVED_BY_ADDRESS.getName(), new String[]{"address", 
				"confirmations"}, new Object[]{address, confirmations}, totalReceived);
	}

	public void getTransaction(String txId) throws BitcoindException, CommunicationException {
		Transaction transaction = btcdClient.getTransaction(txId);
		printResult(Commands.GET_TRANSACTION.getName(), new String[]{"txId"}, new Object[]{txId},
				transaction);
	}
	
	public void getTransaction(String txId, boolean withWatchOnly) throws BitcoindException, 
			CommunicationException {
		Transaction transaction = btcdClient.getTransaction(txId, withWatchOnly);
		printResult(Commands.GET_TRANSACTION.getName(), new String[]{"txId", "withWatchOnly"},
				new Object[]{txId, withWatchOnly}, transaction);
	}

	public void getUnconfirmedBalance() throws BitcoindException, CommunicationException {
		BigDecimal unconfirmedBalance = btcdClient.getUnconfirmedBalance();
		printResult(Commands.GET_UNCONFIRMED_BALANCE.getName(), null, null, unconfirmedBalance);
	}

	public void getWalletInfo() throws BitcoindException, CommunicationException {
		WalletInfo walletInfo = btcdClient.getWalletInfo();
		printResult(Commands.GET_WALLET_INFO.getName(), null, null, walletInfo);
	}

	public void importAddress(String address) throws BitcoindException, CommunicationException {
		btcdClient.importAddress(address);
		printResult(Commands.IMPORT_ADDRESS.getName(), new String[]{"address"}, 
				new Object[]{address}, null);
	}

	public void importAddress(String address, String account) throws BitcoindException, 
			CommunicationException {
		btcdClient.importAddress(address, account);
		printResult(Commands.IMPORT_ADDRESS.getName(), new String[]{"address", "account"},
				new Object[]{address, account}, null);
	}

	public void importAddress(String address, String account, boolean withRescan) 
			throws BitcoindException, CommunicationException {
		btcdClient.importAddress(address, account, withRescan);
		printResult(Commands.IMPORT_ADDRESS.getName(), new String[]{"address", "account", 
				"withRescan"}, new Object[]{address, account, withRescan}, null);
	}

	public void importPrivKey(String privateKey) throws BitcoindException, CommunicationException {
		btcdClient.importPrivKey(privateKey);
		printResult(Commands.IMPORT_PRIV_KEY.getName(), new String[]{"privateKey"}, 
				new Object[]{privateKey}, null);
	}

	public void importPrivKey(String privateKey, String account) throws BitcoindException, 
			CommunicationException {
		btcdClient.importPrivKey(privateKey, account);
		printResult(Commands.IMPORT_PRIV_KEY.getName(), new String[]{"privateKey", "account"},
				new Object[]{privateKey, account}, null);
	}

	public void importPrivKey(String privateKey, String account, boolean withRescan) 
			throws BitcoindException, CommunicationException {
		btcdClient.importPrivKey(privateKey, account, withRescan);
		printResult(Commands.IMPORT_PRIV_KEY.getName(), new String[]{"privateKey", "account", 
				"withRescan"}, new Object[]{privateKey, account, withRescan}, null);
	}

	public void importWallet(String filePath) throws BitcoindException, CommunicationException {
		btcdClient.importWallet(filePath);
		printResult(Commands.IMPORT_WALLET.getName(), new String[]{"filePath"}, 
				new Object[]{filePath}, null);
	}
	
	public void keyPoolRefill() throws BitcoindException, CommunicationException {
		btcdClient.keyPoolRefill();
		printResult(Commands.KEY_POOL_REFILL.getName(), null, null, null);
	}

	public void keyPoolRefill(int keypoolSize) throws BitcoindException, CommunicationException {
		btcdClient.keyPoolRefill(keypoolSize);
		printResult(Commands.KEY_POOL_REFILL.getName(), new String[]{"keypoolSize"}, 
				new Object[]{keypoolSize}, null);
	}

	public void listAccounts() throws BitcoindException, CommunicationException {
		Map<String, BigDecimal> accounts = btcdClient.listAccounts();
		printResult(Commands.LIST_ACCOUNTS.getName(), null, null, accounts);
	}

	public void listAccounts(int confirmations) throws BitcoindException, CommunicationException {
		Map<String, BigDecimal> accounts = btcdClient.listAccounts(confirmations);
		printResult(Commands.LIST_ACCOUNTS.getName(), new String[]{"confirmations"}, 
				new Object[]{confirmations}, accounts);
	}

	public void listAccounts(int confirmations, boolean withWatchOnly) throws BitcoindException, 
			CommunicationException {
		Map<String, BigDecimal> accounts = btcdClient.listAccounts(confirmations, withWatchOnly);
		printResult(Commands.LIST_ACCOUNTS.getName(), new String[]{"confirmations", 
				"withWatchOnly"}, new Object[]{confirmations, withWatchOnly}, accounts);
	}
	
	public void listAddressGroupings() throws BitcoindException, CommunicationException {
		List<List<AddressOverview>> groupings = btcdClient.listAddressGroupings();
		printResult(Commands.LIST_ADDRESS_GROUPINGS.getName(), null, null, groupings);
	}
	
	public void listLockUnspent() throws BitcoindException, CommunicationException {
		List<OutputOverview> lockedOutputs = btcdClient.listLockUnspent();
		printResult(Commands.LIST_LOCK_UNSPENT.getName(), null, null, lockedOutputs);
	}
	
	public void listReceivedByAccount() throws BitcoindException, CommunicationException {
		List<Account> accounts = btcdClient.listReceivedByAccount();
		printResult(Commands.LIST_RECEIVED_BY_ACCOUNT.getName(), null, null, accounts);
	}
	
	public void listReceivedByAccount(int confirmations) throws BitcoindException, 
			CommunicationException {
		List<Account> accounts = btcdClient.listReceivedByAccount(confirmations);
		printResult(Commands.LIST_RECEIVED_BY_ACCOUNT.getName(), new String[]{"confirmations"},
				new Object[]{confirmations}, accounts);
	}
	
	public void listReceivedByAccount(int confirmations, boolean withUnused) 
			throws BitcoindException, CommunicationException {
		List<Account> accounts = btcdClient.listReceivedByAccount(confirmations, withUnused);
		printResult(Commands.LIST_RECEIVED_BY_ACCOUNT.getName(), new String[]{"confirmations", 
				"withUnused"}, new Object[]{confirmations, withUnused}, accounts);
	}
	
	public void listReceivedByAccount(int confirmations, boolean withUnused, 
			boolean withWatchOnly) throws BitcoindException, CommunicationException {
		List<Account> accounts = btcdClient.listReceivedByAccount(confirmations, withUnused, 
				withWatchOnly);
		printResult(Commands.LIST_RECEIVED_BY_ACCOUNT.getName(), new String[]{"confirmations", 
				"withUnused", "withWatchOnly"}, new Object[]{confirmations, withUnused, 
				withWatchOnly}, accounts);
	}		
	
	public void listReceivedByAddress() throws BitcoindException, CommunicationException {
		List<Address> addresses = btcdClient.listReceivedByAddress();
		printResult(Commands.LIST_RECEIVED_BY_ADDRESS.getName(), null, null, addresses);
	}

	public void listReceivedByAddress(int confirmations) throws BitcoindException, 
			CommunicationException {
		List<Address> addresses = btcdClient.listReceivedByAddress(confirmations);
		printResult(Commands.LIST_RECEIVED_BY_ADDRESS.getName(), new String[]{"confirmations"},
				new Object[]{confirmations}, addresses);
	}
	
	public void listReceivedByAddress(int confirmations, boolean withUnused) 
			throws BitcoindException, CommunicationException {
		List<Address> addresses = btcdClient.listReceivedByAddress(confirmations, withUnused);
		printResult(Commands.LIST_RECEIVED_BY_ADDRESS.getName(), new String[]{"confirmations",
				"withUnused"}, new Object[]{confirmations, withUnused}, addresses);
	}

	public void listReceivedByAddress(int confirmations, boolean withUnused, 
			boolean withWatchOnly) throws BitcoindException, CommunicationException {
		List<Address> addresses = btcdClient.listReceivedByAddress(confirmations, withUnused,
				withWatchOnly);
		printResult(Commands.LIST_RECEIVED_BY_ADDRESS.getName(), new String[]{"confirmations", 
				"withUnused", "withWatchOnly"}, new Object[]{confirmations, withUnused, 
				withWatchOnly}, addresses);
	}
	
	public void listSinceBlock() throws BitcoindException, CommunicationException {
		SinceBlock sinceBlock = btcdClient.listSinceBlock();
		printResult(Commands.LIST_SINCE_BLOCK.getName(), null, null, sinceBlock);
	}
	
	public void listSinceBlock(String headerHash) throws BitcoindException, CommunicationException {
		SinceBlock sinceBlock = btcdClient.listSinceBlock(headerHash);
		printResult(Commands.LIST_SINCE_BLOCK.getName(), new String[]{"headerHash"}, 
				new Object[]{headerHash}, sinceBlock);
	}
	
	public void listSinceBlock(String headerHash, int confirmations) throws BitcoindException, 
			CommunicationException {
		SinceBlock sinceBlock = btcdClient.listSinceBlock(headerHash, confirmations);
		printResult(Commands.LIST_SINCE_BLOCK.getName(), new String[]{"headerHash", 
				"confirmations"}, new Object[]{headerHash, confirmations}, sinceBlock);
	}
	
	public void listSinceBlock(String headerHash, int confirmations, boolean withWatchOnly) 
			throws BitcoindException, CommunicationException {
		SinceBlock sinceBlock = btcdClient.listSinceBlock(headerHash, confirmations, withWatchOnly);
		printResult(Commands.LIST_SINCE_BLOCK.getName(), new String[]{"headerHash", 
				"confirmations", "withWatchOnly"}, new Object[]{headerHash, confirmations, 
				withWatchOnly}, sinceBlock);
	}

	public void listTransactions() throws BitcoindException, CommunicationException {
		List<Payment> payments = btcdClient.listTransactions();
		printResult(Commands.LIST_TRANSACTIONS.getName(), null, null, payments);
	}

	public void listTransactions(String account) throws BitcoindException, CommunicationException {
		List<Payment> payments = btcdClient.listTransactions(account);
		printResult(Commands.LIST_TRANSACTIONS.getName(), new String[]{"account"}, 
				new Object[]{account}, payments);
	}

	public void listTransactions(String account, int count) throws BitcoindException, 
			CommunicationException {
		List<Payment> payments = btcdClient.listTransactions(account, count);
		printResult(Commands.LIST_TRANSACTIONS.getName(), new String[]{"account", "count"},
				new Object[]{account, count}, payments);
	}

	public void listTransactions(String account, int count, int offset) throws BitcoindException, 
			CommunicationException {
		List<Payment> payments = btcdClient.listTransactions(account, count, offset);
		printResult(Commands.LIST_TRANSACTIONS.getName(), new String[]{"account", "count", 
				"offset"}, new Object[]{account, count, offset}, payments);
	}

	public void listTransactions(String account, int count, int offset, boolean withWatchOnly) 
			throws BitcoindException, CommunicationException {
		List<Payment> payments = btcdClient.listTransactions(account, count, offset, withWatchOnly);
		printResult(Commands.LIST_TRANSACTIONS.getName(), new String[]{"account", "count",
				"offset", "withWatchOnly"}, new Object[]{account, count, offset, withWatchOnly},
				payments);
	}
	
	public void listUnspent() throws BitcoindException, CommunicationException {
		List<Output> unspentOutputs = btcdClient.listUnspent();
		printResult(Commands.LIST_UNSPENT.getName(), null, null, unspentOutputs);
	}

	public void listUnspent(int minConfirmations) throws BitcoindException, CommunicationException {
		List<Output> unspentOutputs = btcdClient.listUnspent(minConfirmations);
		printResult(Commands.LIST_UNSPENT.getName(), new String[]{"minConfirmations"}, 
				new Object[]{minConfirmations}, unspentOutputs);
	}

	public void listUnspent(int minConfirmations, int maxConfirmations) throws BitcoindException, 
			CommunicationException {
		List<Output> unspentOutputs = btcdClient.listUnspent(minConfirmations, maxConfirmations);
		printResult(Commands.LIST_UNSPENT.getName(), new String[]{"minConfirmations", 
				"maxConfirmations"}, new Object[]{minConfirmations, maxConfirmations}, 
				unspentOutputs);
	}

	public void listUnspent(int minConfirmations, int maxConfirmations, List<String> addresses) 
			throws BitcoindException, CommunicationException {
		List<Output> unspentOutputs = btcdClient.listUnspent(minConfirmations, maxConfirmations,
				addresses);
		printResult(Commands.LIST_UNSPENT.getName(), new String[]{"minConfirmations",
				"maxConfirmations", "addresses"}, new Object[]{minConfirmations, 
				maxConfirmations, addresses}, unspentOutputs);
	}
	
	public void lockUnspent(boolean isUnlocked) throws BitcoindException, CommunicationException {
		Boolean isSuccess = btcdClient.lockUnspent(isUnlocked);
		printResult(Commands.LOCK_UNSPENT.getName(), new String[]{"isUnlocked"}, 
				new Object[]{isUnlocked}, isSuccess);
	}

	public void lockUnspent(boolean isUnlocked, List<OutputOverview> outputs) 
			throws BitcoindException, CommunicationException {
		Boolean isSuccess = btcdClient.lockUnspent(isUnlocked, outputs);
		printResult(Commands.LOCK_UNSPENT.getName(), new String[]{"isUnlocked", "outputs"}, 
				new Object[]{isUnlocked, outputs}, isSuccess);
	}
	
	public void move(String fromAccount, String toAccount, BigDecimal amount) 
			throws BitcoindException, CommunicationException {
		Boolean isSuccess = btcdClient.move(fromAccount, toAccount, amount);
		printResult(Commands.MOVE.getName(), new String[]{"fromAccount", "toAccount", "amount"},
				new Object[]{fromAccount, toAccount, amount}, isSuccess);
	}
	
	public void move(String fromAccount, String toAccount, BigDecimal amount, int dummy,
			String comment) throws BitcoindException, CommunicationException {
		Boolean isSuccess = btcdClient.move(fromAccount, toAccount, amount, dummy, comment);
		printResult(Commands.MOVE.getName(), new String[]{"fromAccount", "toAccount", "amount", 
				"dummy", "comment"}, new Object[]{fromAccount, toAccount, amount, dummy, comment},
				isSuccess);
	}
	
	public void ping() throws BitcoindException, CommunicationException {
		btcdClient.ping();
		printResult(Commands.PING.getName(), null, null, null);
	}
	
	public void sendFrom(String fromAccount, String toAddress, BigDecimal amount) 
			throws BitcoindException, CommunicationException {
		String transactionId = btcdClient.sendFrom(fromAccount, toAddress, amount);
		printResult(Commands.SEND_FROM.getName(), new String[]{"fromAccount", "toAddress", 
				"amount"}, new Object[]{fromAccount, toAddress, amount}, transactionId);
	}
	
	public void sendFrom(String fromAccount, String toAddress, BigDecimal amount, int confirmations)
			throws BitcoindException, CommunicationException {
		String transactionId = btcdClient.sendFrom(fromAccount, toAddress, amount, confirmations);
		printResult(Commands.SEND_FROM.getName(), new String[]{"fromAccount", "toAddress", 
				"amount", "confirmations"}, new Object[]{fromAccount, toAddress, amount, 
				confirmations}, transactionId);
	}
	
	public void sendFrom(String fromAccount, String toAddress, BigDecimal amount, int confirmations,
			String comment) throws BitcoindException, CommunicationException {
		String transactionId = btcdClient.sendFrom(fromAccount, toAddress, amount, confirmations,
				comment);
		printResult(Commands.SEND_FROM.getName(), new String[]{"fromAccount", "toAddress", 
				"amount", "confirmations", "comment"}, new Object[]{fromAccount, toAddress, 
				amount, confirmations, comment}, transactionId);
	}
	
	public void sendFrom(String fromAccount, String toAddress, BigDecimal amount, int confirmations,
			String comment, String commentTo) throws BitcoindException, CommunicationException {
		String transactionId = btcdClient.sendFrom(fromAccount, toAddress, amount, confirmations,
				comment, commentTo);
		printResult(Commands.SEND_FROM.getName(), new String[]{"fromAccount", "toAddress", 
				"amount", "confirmations", "comment", "commentTo"}, new Object[]{fromAccount, 
				toAddress, amount, confirmations, comment, commentTo}, transactionId);
	}

	public void sendMany(String fromAccount, Map<String, BigDecimal> toAddresses) 
			throws BitcoindException, CommunicationException {
		String transactionId = btcdClient.sendMany(fromAccount, toAddresses);
		printResult(Commands.SEND_MANY.getName(), new String[]{"fromAccount", "toAddresses"}, 
				new Object[]{fromAccount, toAddresses}, transactionId);
	}
	
	public void sendMany(String fromAccount, Map<String, BigDecimal> toAddresses, 
			int confirmations) throws BitcoindException, CommunicationException {
		String transactionId = btcdClient.sendMany(fromAccount, toAddresses, confirmations);
		printResult(Commands.SEND_MANY.getName(), new String[]{"fromAccount", "toAddresses", 
				"confirmations"}, new Object[]{fromAccount, toAddresses, confirmations},
				transactionId);
	}
	
	public void sendMany(String fromAccount, Map<String, BigDecimal> toAddresses, 
			int confirmations, String comment) throws BitcoindException, CommunicationException {
		String transactionId = btcdClient.sendMany(fromAccount, toAddresses, confirmations, 
				comment);
		printResult(Commands.SEND_MANY.getName(), new String[]{"fromAccount", "toAddresses", 
				"confirmations", "comment"}, new Object[]{fromAccount, toAddresses, confirmations, 
				comment}, transactionId);
	}
	
	public void sendRawTransaction(String hexTransaction) throws BitcoindException, 
			CommunicationException {
		String transactionId = btcdClient.sendRawTransaction(hexTransaction);
		printResult(Commands.SEND_RAW_TRANSACTION.getName(), new String[]{"hexTransaction"}, 
				new Object[]{hexTransaction}, transactionId);
	}

	public void sendRawTransaction(String hexTransaction, boolean withHighFees) 
			throws BitcoindException, CommunicationException {
		String transactionId = btcdClient.sendRawTransaction(hexTransaction, withHighFees);
		printResult(Commands.SEND_RAW_TRANSACTION.getName(), new String[]{"hexTransaction", 
				"withHighFees"}, new Object[]{hexTransaction, withHighFees}, transactionId);
	}
	
	public void sendToAddress(String toAddress, BigDecimal amount) throws BitcoindException, 
			CommunicationException {
		String transactionId = btcdClient.sendToAddress(toAddress, amount);
		printResult(Commands.SEND_TO_ADDRESS.getName(), new String[]{"toAddress", "amount"},
				new Object[]{toAddress, amount}, transactionId);
	}

	public void sendToAddress(String toAddress, BigDecimal amount, String comment) 
			throws BitcoindException, CommunicationException {
		String transactionId = btcdClient.sendToAddress(toAddress, amount, comment);
		printResult(Commands.SEND_TO_ADDRESS.getName(), new String[]{"toAddress", "amount", 
				"comment"}, new Object[]{toAddress, amount, comment}, transactionId);
	}

	public void sendToAddress(String toAddress, BigDecimal amount, String comment, 
			String commentTo) throws BitcoindException, CommunicationException {
		String transactionId = btcdClient.sendToAddress(toAddress, amount, comment, commentTo);
		printResult(Commands.SEND_TO_ADDRESS.getName(), new String[]{"toAddress", "amount",
				"comment", "commentTo"}, new Object[]{toAddress, amount, comment, commentTo}, 
				transactionId);
	}

	public void setAccount(String address, String account) throws BitcoindException, 
			CommunicationException {
		btcdClient.setAccount(address, account);
		printResult(Commands.SET_ACCOUNT.getName(), new String[]{"address", "account"},
				new Object[]{address, account}, null);
	}

	public void setGenerate(boolean isGenerate) throws BitcoindException, CommunicationException {
		btcdClient.setGenerate(isGenerate);
		printResult(Commands.SET_GENERATE.getName(), new String[]{"isGenerate"}, 
				new Object[]{isGenerate}, null);
	}

	public void setGenerate(boolean isGenerate, int processors) throws BitcoindException, 
			CommunicationException {
		btcdClient.setGenerate(isGenerate, processors);
		printResult(Commands.SET_GENERATE.getName(), new String[]{"isGenerate", "processors"},
				new Object[]{isGenerate, processors}, null);
	}

	public void setTxFee(BigDecimal txFee) throws BitcoindException, CommunicationException {
		Boolean isSuccess = btcdClient.setTxFee(txFee);
		printResult(Commands.SET_TX_FEE.getName(), new String[]{"txFee"}, new Object[]{txFee},
				isSuccess);
	}
	
	public void signMessage(String address, String message) throws BitcoindException, 
			CommunicationException {
		String signature = btcdClient.signMessage(address, message);
		printResult(Commands.SIGN_MESSAGE.getName(), new String[]{"address", "message"}, 
				new Object[]{address, message}, signature);
	}
	
	public void signRawTransaction(String hexTransaction) throws BitcoindException, 
			CommunicationException {
		SignatureResult signatureResult = btcdClient.signRawTransaction(hexTransaction);
		printResult(Commands.SIGN_RAW_TRANSACTION.getName(), new String[]{"hexTransaction"},
				new Object[]{hexTransaction}, signatureResult);
	}

	public void signRawTransaction(String hexTransaction, List<Output> outputs) 
			throws BitcoindException, CommunicationException {
		SignatureResult signatureResult = btcdClient.signRawTransaction(hexTransaction, outputs);
		printResult(Commands.SIGN_RAW_TRANSACTION.getName(), new String[]{"hexTransaction", 
				"outputs"}, new Object[]{hexTransaction, outputs}, signatureResult);
	}

	public void signRawTransaction(String hexTransaction, List<Output> outputs, 
			List<String> privateKeys) throws BitcoindException, CommunicationException {
		SignatureResult signatureResult = btcdClient.signRawTransaction(hexTransaction, outputs, 
				privateKeys);
		printResult(Commands.SIGN_RAW_TRANSACTION.getName(), new String[]{"hexTransaction", 
				"outputs", "privateKeys"}, new Object[]{hexTransaction, outputs, privateKeys}, 
				signatureResult);
	}

	public void signRawTransaction(String hexTransaction, List<Output> outputs, 
			List<String> privateKeys, String sigHashType) throws BitcoindException, 
			CommunicationException {
		SignatureResult signatureResult = btcdClient.signRawTransaction(hexTransaction, outputs, 
				privateKeys, sigHashType);
		printResult(Commands.SIGN_RAW_TRANSACTION.getName(), new String[]{"hexTransaction", 
				"outputs", "privateKeys", "sigHashType"}, new Object[]{hexTransaction, outputs, 
				privateKeys, sigHashType}, signatureResult);
	}

	public void stop() throws BitcoindException, CommunicationException {
		String noticeMsg = btcdClient.stop();
		printResult(Commands.STOP.getName(), null, null, noticeMsg);
	}

	public void validateAddress(String address) throws BitcoindException, CommunicationException {
		AddressInfo addressInfo = btcdClient.validateAddress(address);
		printResult(Commands.VALIDATE_ADDRESS.getName(), new String[]{"address"}, 
				new Object[]{address}, addressInfo);
	}
	
	public void verifyMessage(String address, String signature, String message) 
			throws BitcoindException, CommunicationException {
		Boolean isSigValid = btcdClient.verifyMessage(address, signature, message);
		printResult(Commands.VERIFY_MESSAGE.getName(), new String[]{"address", "signature", 
				"message"}, new Object[]{address, signature, message}, isSigValid);
	}
	
	public void walletLock() throws BitcoindException, CommunicationException {
		btcdClient.walletLock();
		printResult(Commands.WALLET_LOCK.getName(), null, null, null);
	}

	public void walletPassphrase(String passphrase, int authTimeout) throws BitcoindException, 
			CommunicationException {
		btcdClient.walletPassphrase(passphrase, authTimeout);
		printResult(Commands.WALLET_PASSPHRASE.getName(), new String[]{"passphrase", "authTimeout"},
				new Object[]{passphrase, authTimeout}, null);
	}

	public void walletPassphraseChange(String curPassphrase, String newPassphrase) 
			throws BitcoindException, CommunicationException {
		btcdClient.walletPassphraseChange(curPassphrase, newPassphrase);
		printResult(Commands.WALLET_PASSPHRASE_CHANGE.getName(), new String[]{"curPassphrase", 
				"newPassphrase"}, new Object[]{curPassphrase, newPassphrase}, null);
	}
}