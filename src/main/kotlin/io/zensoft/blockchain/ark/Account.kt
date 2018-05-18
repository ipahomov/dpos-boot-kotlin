package io.zensoft.blockchain.ark

/**
 * @author Igor Pahomov
 */
data class Account(
        var addres: String?,
        var unconfirmedBalance: Long,
        var publicKey: String?,
        var userName: String?,
        var balance: Long = 0,
        var vote: Long?,
        var votes: List<Long>?,
//        var network: Network,
        var updateTimestamp: Long
) {

//    fun applyTransaction(transaction: Transaction): Boolean {
//        balance -= transaction.amount + transaction.fee
//        return (balance > -1)
//    }
//
//    fun undoTransaction(transaction: Transaction): Boolean {
//        balance += transaction.amount + transaction.fee
//        return (balance > -1)
//    }
//
//    fun verifyTransaction(transaction: Transaction): Verification {
//        if (balance < transaction.amount + transaction.fee)
//            "Account $addres does not have enough balance: $balance"
//
//        return Verification()
//    }



}