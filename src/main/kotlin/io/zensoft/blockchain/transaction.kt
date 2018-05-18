package main.io.zensoft.dpos.kotlin.blockchain

import java.security.MessageDigest
import java.util.*

/**
 * @author Igor Pahomov
 */
data class Transaction(
        val amount: Int = 0,
        val timestamp: Date,
        val receipt: String,
        val sender: String
        ) {

    fun calculateHash(): String {
        val bytes = this.toString().toByteArray()
        val digest = MessageDigest.getInstance("SHA-256").digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }

}