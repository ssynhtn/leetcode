package com.ssynhtn.hard

import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.*
import kotlin.math.sign

class MinimumWindowSubstring {
    fun minWindow(s: String, t: String): String {
        if (t.isEmpty()) return ""

        val targetSize = t.length
        val chars = mutableMapOf<Char, Int>()
        for (ch in t) {
            if (chars.containsKey(ch)) {
                chars[ch] = chars[ch]!! + 1
            } else {
                chars[ch] = 1
            }
        }



        val c2i = mutableMapOf<Char, TreeSet<Int>>()
        val i2c = TreeMap<Int, Char>()

        var minSub = ""

        var l = 0
        var ch = ' '

        while (l < s.length && !chars.containsKey(s[l].also { ch = it })) {
            l++
        }

        if (l == s.length) {
            return ""
        }

        if (targetSize == 1) return t

        c2i[ch] = TreeSet(setOf(l))
        i2c[l] = ch

//
//// before each iteration, map.size < chars.size

//        for rest of text
//        if not in chars continue
//        if new char
//                add new char to map,
//        if (map size full)
//            if no previous window or this one is shorter, set this one
//        remove char at l, check second char, increment l
//        else recur char
//                replace char

        for (r in l + 1 until s.length) {
            ch = s[r]
            if (!chars.contains(ch)) {
                continue
            }

            if (c2i.containsKey(ch) && c2i.get(ch)!!.size == chars[ch]) {
                val lastIndex = c2i.get(ch)!!.pollFirst()!!
                c2i[ch]!!.add(r)
                i2c.remove(lastIndex)
                i2c[r] = ch
                l = i2c.firstKey()
            } else {
                if (!c2i.containsKey(ch)) {
                    c2i[ch] = TreeSet()
                }
                c2i[ch]!!.add(r)
                i2c[r] = ch

                if (i2c.size == targetSize) {
                    val candidate = s.substring(l, r + 1)
//                    println("got candidate " + candidate)
                    if (minSub.isEmpty() || (r - l + 1) < minSub.length) {
                        minSub = s.substring(l, r + 1)
                    }

                    val oldChar = i2c[l]
                    i2c.remove(l)
                    c2i[oldChar]!!.remove(l)
                    l = i2c.firstKey()
//                    println("got one window, move l to " + l)
                }
            }
        }

        return minSub
    }

}

fun main() {
    println(MinimumWindowSubstring().minWindow("aa", "aa"))
}