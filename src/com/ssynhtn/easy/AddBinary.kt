package com.ssynhtn.easy

class AddBinary {
    fun addBinary(a: String, b: String): String {
        var m = a.length
        var n = b.length
        val bits = CharArray(Math.max(m, n) + 1)

        m--
        n--
        var carry = 0
        var index = bits.size - 1
        while (m >= 0 && n >= 0) {
            val d1 = if (m >= 0) a[m].minus('0') else 0
            val d2 = if (n >= 0) b[n].minus('0') else 0
            val sum = d1 + d2 + carry
            bits[index] = '0'.plus(d1 xor d2 xor carry)
            carry = if (sum >= 2) 1 else 0
            index--
            m--
            n--
        }

        while (m >= 0 && carry != 0) {
            val d1 = a[m].minus('0')
            bits[index] = '0'.plus(d1 xor carry)
            carry = if (a[m] + carry >= '2') 1 else 0
            index--
            m--
        }

        while (m >= 0) {
            bits[index] = a[m]
            index--
            m--
        }

        while (n >= 0 && carry != 0) {
            val d2 = b[n].minus('0')
            bits[index] = '0'.plus(d2 xor carry)
            carry = if (b[n] + carry >= '2') 1 else 0
            index--
            n--
        }

        while (n >= 0) {
            bits[index] = b[n]
            index--
            n--
        }

        bits[index] = '0'.plus(carry)
        if (carry == 0) {
            return String(bits, 1, bits.size - 1)
        } else {
            return String(bits)
        }
    }
}

fun main() {
    println(AddBinary().addBinary("1010", "1011"))
}