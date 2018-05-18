package io.zensoft.blockchain.lisk

import java.math.BigInteger
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.ByteOrder


/**
 * @author Igor Pahomov
 */
data class Block(var version: Byte,
                 var previousBlock: String,
                 var totalAmount: Long,
                 var totalFee: Long,
                 var reward: Long,
                 var payloadHash: String,
                 var payloadLength: Int,
                 var timestamp: Int,
                 var height: Int,
                 var generatorPublicKey: String,
                 var transactionsNumber: Int,
                 var blockSignature: String,
                 var id: String
                 ) {

    private val bufferSize = 1000

    fun sign(block: Block, keypair:Any) {
//        this.blockSignature
//        block.hashCode()
//        SodiumLibrary.cryptoSignDetached(getHash(block), ByteArray.)
//        CryptoUtils.sign(getHash(block), )
    }


    fun verify(): Boolean {
        return true
    }

    fun setId() {
        val bytesId = getBytes().sliceArray(0..7)
        id = BigInteger(bytesId).toString()
    }

    private fun getBytes(includeSignature: Boolean = false): ByteArray {
        var output = ByteArray(0)
        val buffer: Buffer = prepareBuffer(ByteBuffer.allocate(bufferSize), includeSignature)

        with(buffer) {
            output = ByteArray(position())
            rewind()
//            get(output)
        }

        return output
    }

    private fun prepareBuffer(buffer: ByteBuffer, includeSignature: Boolean): Buffer = buffer.apply {
        order(ByteOrder.LITTLE_ENDIAN)

        put(version)
        putInt(timestamp)
        putInt(height)
        put(BigInteger(previousBlock).toByteArray())
        putInt(transactionsNumber)
        putLong(totalAmount)
        putLong(totalFee)
        putLong(reward)
        putInt(payloadLength)



        if (includeSignature) put(blockSignature.toByteArray())
    }


}

