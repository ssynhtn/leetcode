package com.ssynhtn.medium

class RotateImage {
    fun rotate(matrix: Array<IntArray>): Unit {
        val n = matrix.size
        val maxLevel = n / 2;

        for (i in 0 until maxLevel) {
            val jMax = n - 2 - i
            for (j in i .. jMax) {
                val i_ = n - 1 - i
                val j_ = n - 1 - j

                val bottomLeft = matrix[j_][i]
                matrix[j_][i] = matrix[i_][j_]
                matrix[i_][j_] = matrix[j][i_]
                matrix[j][i_] = matrix[i][j]
                matrix[i][j] = bottomLeft
            }
        }
    }

}

fun main(args: Array<String>) {
    val matrix = arrayOf(intArrayOf(1,2,3,4), intArrayOf(5,6, 7,8), intArrayOf(9, 10, 11, 12), intArrayOf(13, 14, 15, 16))
    matrix.forEach { a -> println(a.toList()) }
    RotateImage().rotate(matrix)
    matrix.forEach { a -> println(a.toList()) }
}