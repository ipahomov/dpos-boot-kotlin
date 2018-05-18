//package io.zensoft.blockchain.ark
//
//import main.io.zensoft.dpos.kotlin.blockchain.Transaction
//import org.springframework.http.HttpRequest
//
//
///**
// * @author Igor Pahomov
// */
//class Peer(val ip: String, val port: Int, val network: Network) {
//    private var protocol: String = "http://"
//    private var status: String = "NEW"
//    var peerURL: String = ""
//
//    init {
//        peerURL = "$protocol$ip:$port"
//    }
//
//    constructor(peerInfo: List<String>, network: Network): this (
//            ip = peerInfo[0],
//            port = peerInfo[1].toInt(),
//            network = network
//    )
//
//    open fun getStatus() = HttpRequest.getStatus(this)
//
//    open fun postTransaction(transaction: Transaction) =
//            HttpRequest.postTransaction(this, transaction)
//
//    fun getTransaction(account: Account, amount: Int) =
//            HttpRequest.getTransaction(this, account, amount)
//
//
//
//}