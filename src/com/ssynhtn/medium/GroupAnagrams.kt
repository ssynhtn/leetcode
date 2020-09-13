package com.ssynhtn.medium

class GroupAnagrams {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {

        val map: MutableMap<String, MutableList<String>> = mutableMapOf()
        val data = CharArray(26)

        for (i in 0 until strs.size) {
            for (j in 0 until data.size) {
                data[j] = 0.toChar()
            }

            for (ch in strs[i]) {
                data[ch - 'a']++
            }

            val key = String(data)

            if (map.containsKey(key)) {
                map[key]!!.add(strs[i])
            } else {
                map[key] = mutableListOf(strs[i])
            }
        }

        return map.values.toList()
    }
}

fun main(args: Array<String>) {
    val strs = arrayOf("eat","tea","tan","ate","nat","bat")
    println(GroupAnagrams().groupAnagrams(strs))
}