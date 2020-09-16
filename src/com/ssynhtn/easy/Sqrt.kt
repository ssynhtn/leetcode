package com.ssynhtn.easy

import kotlin.math.sqrt

class Sqrt {
    fun mySqrt(x: Int): Int {
        if (x == 0 || x == 1) return x

        var left = 2
        var u = (1 shl 30)
        while (u and x == 0) {
            u = u shr 1
            left++
        }

        var right = (32 - left) / 2
        var a = 1 shl right

        var b = Math.min(1 shl (right + 1), 46340)
        var m: Int
        while (true) {
            m = (a + b) / 2
            var m2 = m * m
            if (m2 > x) {
                b = m
                continue
            }
            if (m2 == x) {
                return m
            }

            var m12 = m2 + m * 2 + 1
            if (m12 == x) {
                return (m + 1)
            }
            if (m12 > x || m12 < 0) {
                return m
            }

            a = m + 1
        }


    }
}

fun main() {
    println(Sqrt().mySqrt(Int.MAX_VALUE))
}