package io.zensoft

/**
 * @author Igor Pahomov
 */
fun main(args: Array<String>) {
    val list = listOf("1", "2", "3", "4")

    val merkleList = getMerkleList(list)
    println(merkleList)

}

fun getMerkleList(list: List<Any>): List<Int> {
    if (1 == list.size) {
        return listOf(list.first().hashCode())
    } else {
        val hashesList: MutableList<Int> = mutableListOf()
        for (i in list.indices step 2) {
            val hashedElement: Int =
                    if ((i == list.size - 1) && (list.size % 2 == 1)) {
                        2 * list[i].hashCode()
                    } else {
                        list[i].hashCode() + list[i + 1].hashCode()
                    }
            hashesList.add(hashedElement)
        }

        return getMerkleList(hashesList)
    }
}