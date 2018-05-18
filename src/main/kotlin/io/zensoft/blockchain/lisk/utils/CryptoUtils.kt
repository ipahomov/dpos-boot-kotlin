package io.zensoft.blockchain.lisk.utils

import com.muquit.libsodiumjna.SodiumKeyPair
import com.muquit.libsodiumjna.SodiumLibrary
import com.muquit.libsodiumjna.SodiumUtils

/**
 * @author Igor Pahomov
 */
object CryptoUtils {

    fun sign(objHash: String, keyPair: SodiumKeyPair): ByteArray? {
        return SodiumLibrary.cryptoSignDetached(SodiumUtils.hex2Binary(objHash), keyPair.privateKey)
    }

}