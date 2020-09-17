package com.ssynhtn.hard

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.sign

class MinimumWindowSubstring {
    fun minWindow(s: String, t: String): String {
        if (t.isEmpty()) return ""

        val chars = HashMap<Char, Int>()
        for (ch in t) {
            chars[ch] = chars.getOrDefault(ch, 0) + 1
        }



        val charCounts = HashMap<Char, Int>()

        var minSub = ""

        var l = 0
        var sufficientChars = 0

        for (r in 0 until s.length) {
            val ch = s[r]
            if (!chars.containsKey(ch)) {
                continue
            }

            charCounts[ch] = charCounts.getOrDefault(ch, 0) + 1

            if (charCounts[ch] == chars[ch]) {
                sufficientChars++

                while (sufficientChars == chars.size) {
                    if (minSub.isEmpty() || r + 1 - l < minSub.length) {
                        minSub = s.substring(l, r + 1)
                    }

                    val chLeft = s[l++]
                    if (!chars.containsKey(chLeft)) {
                        continue
                    }

                    charCounts[chLeft] = charCounts[chLeft]!! - 1
                    if (charCounts[chLeft] == chars[chLeft]!! - 1) {
                        sufficientChars--
                    }

                }
            }

        }


        return minSub
    }

}

fun main() {
    println(MinimumWindowSubstring().minWindow("a", "aa"))
}