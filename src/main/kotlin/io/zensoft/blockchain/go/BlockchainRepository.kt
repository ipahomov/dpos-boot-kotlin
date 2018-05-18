package io.zensoft.blockchain.go

import org.mapdb.DB
import org.mapdb.DBMaker
import org.mapdb.HTreeMap
import org.mapdb.Serializer
import org.springframework.stereotype.Service
import org.springframework.util.SerializationUtils

/**
 * @author Igor Pahomov
 */
@Service
class BlockchainRepository : DBRepository {

    val BLOCKS_BUCKET = "blocks"
    val PEERS_BUCKET = "peers"

    private lateinit var db: DB


    override fun setupDB(dbName: String): DB {
        this.db = DBMaker
                .fileDB(dbName)
                .fileMmapEnable()
                .make()

        createBlocksBucket(db)
        createPeersBucket(db)

        return db
    }

    override fun saveBlock(block: Block) {
        val blocksMap = getBlocksBucket(this.db).open()
        blocksMap[block.hash] = SerializationUtils.serialize(block)
    }

    override fun saveBlock(hash: String, block: Block) {
        val blocksMap = getBlocksBucket(this.db).open()
        blocksMap[hash] = SerializationUtils.serialize(block)
    }

    override fun getLastBlock(): Block {
        val blocksMap = getBlocksBucket(this.db).open()
        return SerializationUtils.deserialize(blocksMap["lastHash"]) as Block

    }

    override fun savePeer(peerAddress: String, delegate: ByteArray) {
        val peersBucket = getPeersBucket(this.db).open()
        peersBucket[peerAddress] = delegate
    }

    private fun createBlocksBucket(db: DB): HTreeMap<String, ByteArray> {
        return getBlocksBucket(db).create()
    }


    private fun createPeersBucket(db: DB): HTreeMap<String, ByteArray> {
        return getPeersBucket(db).create()
    }

    private fun getBlocksBucket(db: DB): DB.HashMapMaker<String, ByteArray> {
        return db.hashMap(BLOCKS_BUCKET, Serializer.STRING, Serializer.BYTE_ARRAY)
    }

    private fun getPeersBucket(db: DB): DB.HashMapMaker<String, ByteArray> {
        return db.hashMap(PEERS_BUCKET, Serializer.STRING, Serializer.BYTE_ARRAY)
    }

}