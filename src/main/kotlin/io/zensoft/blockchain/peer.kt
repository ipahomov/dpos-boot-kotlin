package io.zensoft.blockchain

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/**
 * @author Igor Pahomov
 */
class Peer(id: Any, port: Int, localId: Int) {

    private var remotePort: Int = 0
    private var localId: Int = 0

    private lateinit var socket: Any

    init {
        val buf: String = ""
        if (id is Number) {
            this.remotePort = port
            this.localId = localId
//            this.socket = net.connect(port, this.onConnected_.bind(this))
        } else {
            this.socket = id
            this.localId = port
            this.socket
        }
    }

    fun send(msg: Message) {
        val jsonValue = jacksonObjectMapper().writeValueAsString(msg)
    }

    fun close() {

    }


}