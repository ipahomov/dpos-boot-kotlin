package main.io.zensoft.dpos.kotlin.blockchain

import java.nio.ByteBuffer
import java.security.MessageDigest
import java.util.*

/**
 * @author Igor Pahomov
 */
data class Block(
        private val version: Int = 0,
        private val height: Int = 0,
        private var size: Int = 0,
        private val timestamp: Date,
        private val generatorId: Int,
        private val previousHash: String,
        private var merkleHash: String,
        private val transactions: MutableList<Transaction>,
        private var hash: String
) {

    fun addTransactions(transactions: MutableList<Transaction>) {
        this.transactions.addAll(transactions)
        this.size += transactions.size
        this.merkleHash = this.calculateMerkleHash()
        this.hash = this.calculateHash()
    }

    private fun calculateMerkleHash(): String {
        return getMerkleList(this.transactions).first()
    }

    fun getMerkleList(list: List<Any>): List<String> {
        if (1 == list.size) {
            return listOf(hashAndGetHex(list.first().toString().toByteArray()))
        } else {
            val hashesList: MutableList<String> = mutableListOf()
            val sha256 = MessageDigest.getInstance("SHA-256")

            for (i in list.indices step 2) {
                sha256.update(list[i].toString().toByteArray())
                if ((i == list.size - 1) && (list.size % 2 == 1)) {
                    sha256.update(list[i].toString().toByteArray())
                } else {
                    sha256.update(list[i + 1].toString().toByteArray())
                }

                hashesList.add(sha256.digest().fold("", { str, it -> str + "%02x".format(it) }))
            }

            return getMerkleList(hashesList)
        }
    }

    private fun calculateHash(): String {
        val buf: ByteBuffer = ByteBuffer.allocate(64)

        buf.putInt(this.version)
        buf.putInt(this.height)
        buf.putInt(this.size)
        buf.putInt(this.size)
        buf.putInt(this.generatorId)
        buf.put(this.previousHash.toByteArray())
        buf.put(this.merkleHash.toByteArray())

        buf.flip()

        return hashAndGetHex(buf.array())
    }

    private fun hashAndGetHex(bytes: ByteArray): String {
        val instance = MessageDigest.getInstance("SHA-256")
        val digest = instance.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }

}