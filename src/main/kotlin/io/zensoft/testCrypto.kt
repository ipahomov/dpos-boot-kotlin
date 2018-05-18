package io.zensoft

import com.muquit.libsodiumjna.SodiumUtils

/**
 * @author Igor Pahomov
 */
fun main(args: Array<String>) {
    println(SodiumUtils.binary2Hex("0xhelsdfsdfdsfdsfsdfsdfdsfsdflo".toByteArray()))
}