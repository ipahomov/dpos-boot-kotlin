package io.zensoft.blockchain.go

import com.muquit.libsodiumjna.SodiumUtils
import java.nio.ByteBuffer
import java.security.MessageDigest

/**
 * @author Igor Pahomov
 */
data class Block(
        var height: Int,
        val timestamp: Long,
        val amount: Long,
        var previousHash: String,
        val generatedBy: String,
        var id: Long = 0
) {

    init {
        id++
    }

    var hash: String = ""

    fun calculateHash(): String {
        val buf: ByteBuffer = ByteBuffer.allocate(64)

        buf.putInt(this.height)
        buf.putLong(this.timestamp)
        buf.putLong(this.amount)
        buf.put(SodiumUtils.hex2Binary(this.previousHash))

        buf.flip()

        return hashAndGetHex(buf.array())
    }

    private fun hashAndGetHex(bytes: ByteArray): String {
        val instance = MessageDigest.getInstance("SHA-256")
        val digest = instance.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }

}