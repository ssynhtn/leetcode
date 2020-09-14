package com.ssynhtn.medium

class PlusOne {
    fun plusOne(digits: IntArray): IntArray {
        var add = 1
        for (i in digits.size - 1 downTo 0) {
            var sum = add + digits[i]
            if (sum <= 9) {
                digits[i] = sum
                return digits
            }

            digits[i] = sum - 10
            add = 1
        }

        val result = IntArray(digits.size + 1)
        result[0] = 1
        System.arraycopy(digits, 0, result, 1, digits.size)
        return result
    }
}

fun main() {
    println(PlusOne().plusOne(intArrayOf(9, 9)).toList())
}