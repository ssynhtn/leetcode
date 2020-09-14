package com.ssynhtn.medium

class SpiralMatrix2 {
    fun generateMatrix(n: Int): Array<IntArray> {
        val result = Array<IntArray>(n, {i -> IntArray(n) })

        var imin = 0
        var imax = n
        var jmin = 0
        var jmax = n

        var di = 0
        var dj = 1

        var i = 0
        var j = 0

        var value = 1
        while (imin < imax && jmin < jmax) {
            while (i >= imin && i < imax && j >= jmin && j < jmax) {
                result[i][j] = value++
                i = i + di
                j = j + dj
            }
            i -= di
            j -= dj

            if (di == 0 && dj == 1) {
                imin++
                di = 1
                dj = 0
            } else if (di == 1 && dj == 0) {
                jmax--
                di = 0
                dj = -1
            } else if (di == 0 && dj == -1) {
                imax--
                di = -1
                dj = 0
            } else {
                jmin++
                di = 0
                dj = 1
            }

            i += di
            j += dj

        }

        return result
    }
}

fun main() {
    val matrix = SpiralMatrix2().generateMatrix(4)
    for (arr in matrix) {
        println(arr.toList())
    }
}