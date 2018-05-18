package io.zensoft.blockchain

/**
 * @author Igor Pahomov
 */
data class Node(val id: Int, val isBad: Boolean) {
    private var peers: MutableList<Peer> = mutableListOf()
    private var peerIds = mutableListOf<Int>()
    private var server = this.createServer()
    private var blockchain = BlockChain(this)


    private fun createServer(): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}