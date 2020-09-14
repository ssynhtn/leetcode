package com.ssynhtn.medium

class GetPermutation {
    fun getPermutation(n: Int, k: Int): String {
        val chs = CharArray(n)
        for (i in 1 .. n) {
            chs[i - 1] = '0'.plus(i)
        }
        var index = k - 1
        val facts = mutableListOf<Int>()

        var f = 1
        for (i in 1 .. n-1) {
            f = f * i
            if (f <= index) {
                facts.add(f)
            }
//            facts[n - i - 1] = f
        }

        // i == n-2, [0]
        for (i in n-1-facts.size until n - 1) {
            val multiple = index / facts[n-2-i]
            index = index % facts[n-2-i]

            shiftRight(chs, i, multiple)

        }
        return String(chs)
    }

    private fun shiftRight(chs: CharArray, i: Int, len: Int) {
        if (len == 0) return
        val temp = chs[i + len]
        System.arraycopy(chs, i, chs, i + 1, len)
        chs[i] = temp
    }
}

fun main() {
    println(GetPermutation().getPermutation(3, 3))
}