package io.zensoft.blockchain

import main.io.zensoft.dpos.kotlin.blockchain.Block
import main.io.zensoft.dpos.kotlin.blockchain.Transaction
import java.util.*

/**
 * @author Igor Pahomov
 */
data class BlockChain(val node: Node) {

    private val genesis = Block(0, 0, 0, Date(), 1, "", "", mutableListOf(Transaction(1000000, Date(), "neo", "")), "")
    private val pendingTransaction: List<Transaction> = listOf()
    private val transactionIndex: Int = 0
//    private val chain = HashList()
//    private val pbft = Pbft(this)
    private val lastSlot = 0

    fun start() {
        var self = this

    }

    fun hasTransaction(trs: List<Transaction>) {

    }

    fun validateTransaction(rs: List<Transaction>) {

    }

}