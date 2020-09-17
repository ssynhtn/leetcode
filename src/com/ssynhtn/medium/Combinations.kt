package com.ssynhtn.medium

class Combinations {
    fun combine(n: Int, k: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        if (k > n) return result

        val buffer = IntArray(k)

        combineWithSelected(buffer, 0, 1, n, result)

        return result
    }

    fun combineWithSelected(buffer: IntArray, fixCount: Int, next: Int, n: Int, result: MutableList<List<Int>>) {
        if (fixCount == buffer.size) {
            result.add(buffer.toList())
            return
        }

        if (next > n) return
        buffer[fixCount] = next
        combineWithSelected(buffer, fixCount + 1, next + 1, n, result)
        combineWithSelected(buffer, fixCount, next + 1, n, result)
    }
}

fun main() {
    val result = Combinations().combine(1, 1)
    for (list in result) {
        println(list)
    }
}