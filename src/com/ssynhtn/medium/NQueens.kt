package com.ssynhtn.medium

class NQueens {
    fun solveNQueens2(n: Int): Int {
        val table = Array<CharArray>(n, {i -> CharArray(n)})
        for (i in 0 until n) {
            for (j in 0 until n) {
                table[i][j] = '.'
            }
        }


        return putQueens(table, 0)

    }

    private fun putQueens(table: Array<CharArray>, fixedRows: Int): Int {
        if (fixedRows == table.size) {
            return 1
        }

        var count = 0
        for (col in 0 until table.size) {
            if (checkHasConflictWithPrev(table, fixedRows, col)) continue
            table[fixedRows][col] = 'Q'
            count += putQueens(table, fixedRows + 1)
            table[fixedRows][col] = '.'

        }

        return count
    }

    fun solveNQueens(n: Int): List<List<String>> {
        val table = Array<CharArray>(n, {i -> CharArray(n)})
        for (i in 0 until n) {
            for (j in 0 until n) {
                table[i][j] = '.'
            }
        }

        val collection = mutableListOf<List<String>>()

        putQueens(table, 0, collection)

        return collection

    }

    private fun putQueens(table: Array<CharArray>, fixedRows: Int, collection: MutableList<List<String>>) {
        if (fixedRows == table.size) {
            collection.add(table.map { chs -> String(chs) })
            return
        }

        for (col in 0 until table.size) {
            if (checkHasConflictWithPrev(table, fixedRows, col)) continue
            table[fixedRows][col] = 'Q'
            putQueens(table, fixedRows + 1, collection)
            table[fixedRows][col] = '.'

        }
    }

    private fun checkHasConflictWithPrev(table: Array<CharArray>, row: Int, col: Int): Boolean {
        for (i in 0 until row) {
            var j = col - row + i
            if (j >= 0 && j < table.size) {
                if (table[i][j] == 'Q') {
                    return true
                }
            }
            j = row + col - i
            if (j >= 0 && j < table.size) {
                if (table[i][j] == 'Q') {
                    return true
                }
            }
            j = col
            if (j >= 0 && j < table.size) {
                if (table[i][j] == 'Q') {
                    return true
                }
            }
        }
        return false
    }
}

fun main(args: Array<String>) {
    println(NQueens().solveNQueens2(4))
//    var queenPlacements = NQueens().solveNQueens(4)
//    for (table in queenPlacements) {
//        println(table.joinToString("\n"))
//        println()
//    }
}