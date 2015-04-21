##Introduction

The btcd-cli4j library is a simple Java wrapper around Bitcoin Core's JSON-RPC (via HTTP) interface. 

* **Latest release:** 0.3.6 ([.zip](https://github.com/priiduneemre/btcd-cli4j/archive/0.3.6.zip); [.tar.gz](https://github.com/priiduneemre/btcd-cli4j/archive/0.3.6.tar.gz))
* **Compatibility:** Bitcoin Core 0.10.0
* **API coverage:** 63 of 81 commands (fully) implemented
* **License:** Apache License 2.0 (see [LICENSE.md](https://github.com/priiduneemre/btcd-cli4j/blob/master/LICENSE.md))
* **Readme updated:** 2015-03-19 14:13:49

A list of all *bitcoind* API commands currently supported by btcd-cli4j can be found in the `Commands` enum (see [here](https://github.com/priiduneemre/btcd-cli4j/blob/master/core/src/main/java/com/neemre/btcdcli4j/core/Commands.java) for more details).

btcd-cli4j follows a layered architecture in that the actions needed to communicate with the Bitcoin network have been separated into multiple levels of abstraction. The central interface used to invoke *bitcoind* API commands (`BtcdClient`) is solely concerned with Bitcoin-specific entity mapping & business logic and does not know anything about JSON-RPC or HTTP. Internally, `BtcdClient` relies on `JsonRpcClient` for managing cross-application communication and adherence to the JSON-RPC standard. `JsonRpcClient`, in turn, utilizes the interface provided by `SimpleHttpClient` to tunnel all JSON-RPC traffic over HTTP. Both the `JsonRpcClient` and `SimpleHttpClient` classes rely on external service providers internally (*Jackson JSON Processor* and *Apache HttpComponents Client* for the time being) which should be relatively easy to replace, were the need to arise.

By default, all incoming & outgoing decimal values (*i.e.* amounts, balances, ping times etc) are transformed into `BigDecimal`s with a scale of 8 and rounding mode of `RoundingMode.HALF_UP` by btcd-cli4j.


##Technologies & dependencies

The btcd-cli4j library has been designed for use with Java SE 7+, however it should also work fine with slightly older versions of the Java runtime (*i.e.* Java SE 6).

Core dependencies:
* Apache HttpComponents Client 4.3.6
* Jackson JSON Processor 2.5.0:
  * Streaming 2.5.0 (`jackson-core`) 
  * Annotations 2.5.0 (`jackson-annotations`)
  * Databind 2.5.0 (`jackson-databind`)
* Lombok 1.16.2

Other dependencies:
* Simple Logging Facade for Java 1.7.10
* Apache Commons Lang 3.3.2


##Getting started

Since the project is currently not hosted on the Maven Central Repository, you should begin by specifying the following custom repository in your `pom.xml` file:

	<repositories>
		<repository>
			<id>mvn-nemp-ftp</id>
			<name>Nemp's Maven Repository</name>
			<url>http://mvn.neemre.com/</url>
		</repository>
	</repositories>

Next, modify your `pom.xml` to include `btcd-cli4j-core` as a dependency:

	<dependency>
		<groupId>com.neemre.btcd-cli4j</groupId>
		<artifactId>btcd-cli4j-core</artifactId>
		<version>0.3.6</version>
	</dependency>

In order to communicate with `bitcoind`, btcd-cli4j needs to be aware of your node's exact configuration. The easiest way of providing this information is via a `node_config.properties` file, for example:

	node.bitcoind.rpc.protocol = http
	node.bitcoind.rpc.host = 127.0.0.1
	node.bitcoind.rpc.port = 8332
	node.bitcoind.rpc.user = falcon-pc
	node.bitcoind.rpc.password = 3F4DN9QGqWrB4DCdfYMXp8xdDYL4HDFzpaS9r76DbNhw
	node.bitcoind.http.auth_scheme = Basic

Finally, to begin using the wrapper, create an instance of `CloseableHttpClient` (the default HTTP provider), load up the properties file created in the previous step and pass the two into a new `BtcdClientImpl` instance: 

	PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	CloseableHttpClient httpProvider = HttpClients.custom().setConnectionManager(cm)
			.build();
	Properties nodeConfig = new Properties();
	InputStream is = new BufferedInputStream(new FileInputStream("node_config.properties"));
	nodeConfig.load(is);
	is.close();
	
	BtcdClient client = new BtcdClientImpl(httpProvider, nodeConfig);
	
That's it!
	
	MiningInfo miningInfo = client.getMiningInfo();
	
*P.S. To learn more about the default HTTP provider (e.g. performance tuning of* `CloseableHttpClient` *instances and/or use of SSL/TLS layering (i.e. HTTPS) (untested!)), see the official HttpComponents Client documentation* [here](http://hc.apache.org/httpcomponents-client-ga/tutorial/html/connmgmt.html#d5e380) *and* [here](http://hc.apache.org/httpcomponents-client-ga/tutorial/html/connmgmt.html#d5e436)*. Additionally, check out the related code samples:* [1](http://hc.apache.org/httpcomponents-client-4.4.x/httpclient/examples/org/apache/http/examples/client/ClientConfiguration.java) *and* [2](http://hc.apache.org/httpcomponents-client-4.4.x/httpclient/examples/org/apache/http/examples/client/ClientCustomSSL.java)*.* 


##Examples

For a short list of usage examples (mostly just sample API calls), see [examples](https://github.com/priiduneemre/btcd-cli4j/tree/master/examples/src/main). 

Please note that some of the examples above will only work on the Bitcoin TESTNET chain (for obvious reasons).


##Supporting the project

If btcd-cli4j has been useful to you and you feel like contributing, consider posting a bug report or a pull request. Alternatively, a spare beer or pizza would also be much appreciated:

* Bitcoin: 12CfEQ7RAEwpS82jFZg1HgjeH8obbpMeL5
* Litecoin: LMwTYNgj7Hkugd1x8rjNXBYdYKgNvACjRF
* Dogecoin: D6DUTo8MTxredfHbbQsb7MLqu7zkuFSMnt