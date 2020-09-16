package com.ssynhtn.easy

import kotlin.math.sqrt

class Sqrt {
    fun mySqrt(x: Int): Int {
        var x1 = x
        var x2 = x/2 + x%2
        while(x2 < x1) {
            x1 = x2
            x2 = (x/x2 +x2)/2
        }
        return x1
    }
}

fun main() {
    println(Sqrt().mySqrt(Int.MAX_VALUE))
}