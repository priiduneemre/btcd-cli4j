##Introduction

The btcd-cli4j library is a simple Java wrapper around Bitcoin Core's JSON-RPC (via HTTP) interface. 

* Latest release: 0.2.4
* Compatibility: Bitcoin Core 0.10.0
* API coverage: 45 of 81 commands (fully) implemented

* License: GNU General Public License v3.0 (see [LICENSE.md] (https://github.com/priiduneemre/btcd-cli4j/blob/master/LICENSE.md))
* Readme updated: 2015-03-07 18:53:44

A list of all *bitcoind* API commands currently supported by btcd-cli4j is available in the `Commands` enum (or see [here]).(https://github.com/priiduneemre/btcd-cli4j/blob/master/src/main/java/com/neemre/btcdcli4j/Commands.java).

btcd-cli4j has been designed to follow a layered architecture, *i.e.* the actions needed to communicate with the Bitcoin network have been separated into multiple layers of abstraction. The central interface used to invoke *bitcoind* API commands (`BtcdClient`) is solely concerned with Bitcoin-specific business logic and does not know anything about JSON-RPC or HTTP. Internally, `BtcdClient` relies on `JsonRpcClient` for managing cross-application communication and adhering to the JSON-RPC standard. `JsonRpcClient`, in turn, uses the methods provided by `SimpleHttpClient` to tunnel its JSON-RPC traffic over HTTP. Both the `JsonRpcClient` and `SimpleHttpClient` classes rely on external service providers internally (*Jackson JSON Processor* and *Apache HttpComponents Client*, respectively) that should be relatively easy to replace, were the need to arise.

##Technologies & dependencies

The btcd-cli4j library has been designed & developed with Java SE 7 in mind, however it should also work acceptably in more restricted environments (such as those limited to Java SE 6).

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

For a short list of usage examples (mostly of sample API calls for now, anyway), see [examples](https://github.com/priiduneemre/btcd-cli4j/tree/master/examples).

##Supporting the project

If btcd-cli4j has been useful to you and you feel like contributing, please consider posting a bug report or a pull request. Alternatively, any beers or pizzas are also much appreciated:

* Bitcoin: 12CfEQ7RAEwpS82jFZg1HgjeH8obbpMeL5
* Litecoin: LMwTYNgj7Hkugd1x8rjNXBYdYKgNvACjRF
* Dogecoin: D6DUTo8MTxredfHbbQsb7MLqu7zkuFSMnt