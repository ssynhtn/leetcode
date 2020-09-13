package com.ssynhtn.medium

class GroupAnagrams {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {

        val map: MutableMap<String, MutableList<String>> = mutableMapOf()

        for (i in 0 until strs.size) {
            val chs = strs[i].toCharArray()
            chs.sort()
            val key = String(chs)

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