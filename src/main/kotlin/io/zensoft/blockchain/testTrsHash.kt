package io.zensoft.blockchain

import main.io.zensoft.dpos.kotlin.blockchain.Block
import main.io.zensoft.dpos.kotlin.blockchain.Transaction
import java.util.*

/**
 * @author Igor Pahomov
 */
fun main(args: Array<String>) {
    val transactions = mutableListOf(Transaction(1, Date(), "1", "4"),
            Transaction(2, Date(), "2", "4"),
            Transaction(3, Date(), "3", "4"),
            Transaction(4, Date(), "4", "4"))

    val block = Block(0, 0, 3, Date(), 1, "prevHash", "merkleHash", transactions, "hash")
    println(block.getMerkleList(transactions))
}