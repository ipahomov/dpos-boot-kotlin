package io.zensoft.blockchain

/**
 * @author Igor Pahomov
 */
enum class MessageType(i: Int) {
    INIT(1),
    BLOCK(2),
    PREPARE(3),
    COMMIT(4)
}

data class Message(val type: MessageType, private val id: Int?, val body: Any?) {

    fun initMessage(id: Int): Message {
        return Message(MessageType.INIT, id, null)
    }

    fun blockMessage(body: Any): Message {
        return Message(MessageType.BLOCK, null, body)
    }

    fun prepareMessage(body: Any): Message {
        return Message(MessageType.PREPARE, null, body)
    }

    fun commitMessage(body: Any): Message {
        return Message(MessageType.COMMIT, null, body)
    }

}