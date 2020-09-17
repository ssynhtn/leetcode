package com.ssynhtn.medium

class FourDivisors {
    fun sumFourDivisors(nums: IntArray): Int {
        var sum = 0
        for (n in nums) {
            sum += divisorSum(n)
        }
        return sum
    }

    /**
     * n >= 1
     * if num has 4 divisors, return sum, else return 0
     */
    fun divisorSum(n: Int): Int {
        for (i in 2 until Math.sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) {
                if (i * i * i == n) {
                    return 1 + i + i * i + n
                } else {
                    val q = n / i
                    if (i == q) return 0
                    if (isPrime(q)) {
                        return 1 + i + q + n
                    } else {
                        return 0
                    }
                }
            }
        }

        return 0
    }

    fun isPrime(i: Int): Boolean {
        if (i == 1) return false

        val sq = Math.ceil(Math.sqrt(i.toDouble())).toInt()

        for (j in 2 .. sq) {
            if (i % j == 0) return false
        }

        return true
    }
}

fun main() {
    println(FourDivisors().sumFourDivisors(intArrayOf(21,4,7)))
}