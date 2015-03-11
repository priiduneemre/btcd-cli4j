##Introduction

The btcd-cli4j library is a simple Java wrapper around Bitcoin Core's JSON-RPC (via HTTP) interface. 

* **Latest release:** 0.3.0
* **Compatibility:** Bitcoin Core 0.10.0
* **API coverage:** 62 of 81 commands (fully) implemented
* **License:** GNU GPL v3.0 (see [LICENSE.md](https://github.com/priiduneemre/btcd-cli4j/blob/master/LICENSE.md))
* **Readme updated:** 2015-03-11 10:49:33

A list of all *bitcoind* API commands currently supported by btcd-cli4j can be found in the `Commands` enum (see [here](https://github.com/priiduneemre/btcd-cli4j/blob/master/src/main/java/com/neemre/btcdcli4j/Commands.java) for more details).

btcd-cli4j follows a layered architecture, *i.e.* the actions needed to communicate with the Bitcoin network have been separated into multiple levels of abstraction. The central interface used to invoke *bitcoind* API commands (`BtcdClient`) is solely concerned with Bitcoin-specific entity mapping & business logic and does not know anything about JSON-RPC or HTTP. Internally, `BtcdClient` relies on `JsonRpcClient` for managing cross-application communication and handling adherence to the JSON-RPC standard. `JsonRpcClient`, in turn, utilizes the interface provided by `SimpleHttpClient` to tunnel all JSON-RPC traffic over HTTP. Both the `JsonRpcClient` and `SimpleHttpClient` classes rely on external service providers internally (*Jackson JSON Processor* and *Apache HttpComponents Client* for the time being) which should be relatively easy to replace, were the need to arise.

By default, all incoming & outgoing decimal values (*i.e* amounts, balances, ping times etc) are transformed into `BigDecimal`s with a scale of 8 and rounding mode of `RoundingMode.HALF_UP` by btcd-cli4j.

##Technologies & dependencies

The btcd-cli4j library has been designed for use with Java SE 7+, however it should also work fine with slightly older versions of the runtime (*i.e.* Java SE 6).

Core dependencies:
* Apache HttpComponents Client 4.3.6
* Jackson JSON Processor 2.5.0:
  * Streaming 2.5.0 (`jackson-core`) 
  * Annotations 2.5.0 (`jackson-annotations`)
  * Databind 2.5.0 (`jackson-databind`)
* Lombok 1.16.2

Other dependencies:
* Apache Commons Lang 3.3.2

##Getting started

TODO

##Examples

For a short list of usage examples (mostly just sample API calls), see [examples](https://github.com/priiduneemre/btcd-cli4j/tree/master/examples). 

Please note that some of the examples listed above will only work correctly on the Bitcoin TESTNET chain (for obvious reasons).

##Supporting the project

If btcd-cli4j has been useful to you and you feel like contributing, consider posting a bug report or a pull request. Alternatively, a spare beer or pizza would also be much appreciated:

* Bitcoin: 12CfEQ7RAEwpS82jFZg1HgjeH8obbpMeL5
* Litecoin: LMwTYNgj7Hkugd1x8rjNXBYdYKgNvACjRF
* Dogecoin: D6DUTo8MTxredfHbbQsb7MLqu7zkuFSMnt