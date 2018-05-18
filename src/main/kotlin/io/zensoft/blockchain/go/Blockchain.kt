package io.zensoft.blockchain.go

import com.muquit.libsodiumjna.SodiumUtils
import org.mapdb.DB
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.Serializable
import java.util.*

/**
 * @author Igor Pahomov
 */
@Service
class Blockchain(tip: ByteArray, db: DB): Serializable {

    val LAST_HASH = "lastHash"

    @Autowired
    private lateinit var dbRepository: DBRepository

    fun newInstance(nodeId: Long): Blockchain {
        val dbName = "blockchain_$nodeId.db"
        val db = dbRepository.setupDB(dbName)

        val block = createGenesisBlock()
        dbRepository.saveBlock(block)
        dbRepository.saveBlock(LAST_HASH, block)

        return Blockchain(SodiumUtils.hex2Binary(block.hash), db)
    }

    fun addBlock(block: Block) {
        dbRepository.saveBlock(block)
        val lastBlock = dbRepository.getLastBlock()

        if (block.height > lastBlock.height) {
            block.previousHash = lastBlock.hash
            block.hash = block.calculateHash()

            dbRepository.saveBlock(block)
            dbRepository.saveBlock(LAST_HASH, block)
        }
    }

    fun getBestHeight(): Int {
        return dbRepository.getLastBlock().height
    }

    fun generateNewBlock(oldBlock: Block, amount: Long, delegateAddress: String): Block {
        val block = Block(++oldBlock.height, Date().time, amount, oldBlock.hash, delegateAddress)
        block.hash = block.calculateHash()

        return block
    }

    fun genBlockPeriod() {

    }

    private fun createGenesisBlock(): Block {
        val block = Block(0, Date().time, 0, "", "")
        block.hash = block.calculateHash()

        return block
    }

}