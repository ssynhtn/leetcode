package com.ssynhtn.hard

import java.lang.IllegalArgumentException
import java.lang.StringBuilder
import kotlin.math.max

class JustifyText {
    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        val result = mutableListOf<String>()
        val row = mutableListOf<String>()
        var usedWidth = 0
        var index = 0
        val sb = StringBuilder()
        while (index < words.size) {
            // usedWidth = 0, row empty, sb empty
            if (words[index].length > maxWidth) {
                throw IllegalArgumentException("word too large ${words[index]})")
            }

            row.add(words[index])
            usedWidth += words[index].length
            index++
            while (index < words.size && usedWidth + words[index].length + 1 <= maxWidth) {
                row.add(words[index])
                usedWidth += words[index].length + 1
                index++
            }

            if (index < words.size && row.size > 1) {
                sb.append(row[0])
                val left = maxWidth - usedWidth
                val allSpaceExtra = left / (row.size - 1)
                val rightSpace = allSpaceExtra + 1
                val moreSpaceCount = left % (row.size - 1)


                for (i in 1 until row.size) {
                    for (j in 0 until rightSpace) {
                        sb.append(' ')
                    }
                    if (i <= moreSpaceCount) {
                        sb.append(' ')
                    }
                    sb.append(row[i])
                }

                result.add(sb.toString())

            } else {
                // last line, or single word in row
                sb.append(row[0])
                for (i in 1 until row.size) {
                    sb.append(' ')
                    sb.append(row[i])
                }
                for (j in 0 until maxWidth - usedWidth) {
                    sb.append(' ')
                }
                result.add(sb.toString())
            }


            usedWidth = 0
            row.clear()
            sb.clear()
        }

        return result
    }
}

fun main() {
    val res = JustifyText().fullJustify(arrayOf("Science","is","what","we","understand","well","enough","to","explain",
            "to","a","computer.","Art","is","everything","else","we","do"), 20)
    for (line in res) {
        println(line)
    }
}