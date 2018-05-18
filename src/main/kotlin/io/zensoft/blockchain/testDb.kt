package io.zensoft.blockchain

import org.mapdb.DBMaker
import org.mapdb.Serializer

/**
 * @author Igor Pahomov
 */
fun main(args: Array<String>) {
    var blocksBucket = "blocks"
    var peerBucket = "peers"

    var db = DBMaker
            .fileDB("blockchain_1.db")
            .fileMmapEnable()
            .make()

    var blockMap = db
            .hashMap(blocksBucket, Serializer.STRING, Serializer.STRING)
            .createOrOpen()

    blockMap.put("firstBlock", "hash1")
    blockMap.put("secondBlock", "hash2")

    val map = db.hashMap(blocksBucket).open()
    println(map["firstBlock"])
    for (store in map.stores) println(store.getAllFiles())

    blockMap.close()
}