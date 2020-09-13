package com.ssynhtn.medium

class SpiralMatrix {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {

        val list = mutableListOf<Int>()

        var imin = 0
        var imax = matrix.size
        if (imax == 0) return list
        var jmin = 0
        var jmax = matrix[0].size
        if (jmax == 0) return list

        var di = 0
        var dj = 1

        var i = 0
        var j = 0

        while (imin < imax && jmin < jmax) {
            while (i >= imin && i < imax && j >= jmin && j < jmax) {
                list.add(matrix[i][j])
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

        return list
    }
}

fun main(args: Array<String>) {
    val list = SpiralMatrix().spiralOrder(arrayOf(intArrayOf(1, 2, 3, 4), intArrayOf(5,6, 7, 8), intArrayOf(9, 10, 11, 12)))
    println(list)
}