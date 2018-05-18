package io.zensoft.blockchain.go

import org.mapdb.DB

/**
 * @author Igor Pahomov
 */
interface DBRepository {

    fun setupDB(dbName: String): DB

    fun saveBlock(block: Block)

    fun saveBlock(hash: String, block: Block)

    fun getLastBlock(): Block

    fun savePeer(peerAddress: String, delegate: ByteArray)

}