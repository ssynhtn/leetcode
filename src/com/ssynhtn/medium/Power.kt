package com.ssynhtn.medium

class Power {
    fun myPow(x: Double, n_: Int): Double {
        if (x == 0.0) return x
        var n = n_

        if (n == Int.MIN_VALUE) {
            return myPow(x, -Int.MAX_VALUE) / x
        }

        if (n < 0) {
            return 1 / myPow(x, -n)
        }

        val powers = DoubleArray(32)
        var current = x

        var i = 0
        while (n != 0) {
            if ((n and 1) != 0) {
                powers[i] = current
            }
            i++
            current = current * current
            n = (n shr 1)

        }

//        println(powers.toList())
        var result = 1.0
        for (j in 0 until i) {
            if (powers[j] == 0.0) continue
            result = result * powers[j]
        }

        return result

    }
}

fun main(args: Array<String>) {
    println(Power().myPow(2.1,3))
    println(2.10000 * 2.10000 * 2.10000)
}