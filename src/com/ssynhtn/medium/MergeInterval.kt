package com.ssynhtn.medium

class MergeInterval {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val list = mutableListOf<IntArray>()

        if (intervals.size > 0) {
            intervals.sortBy { arr -> arr[0] }
            var interval = intervals[0]

            for (i in 1 until intervals.size) {
                val next = intervals[i]
                if (next[0] <= interval[1]) {
                    interval[1] = Math.max(interval[1], next[1])
                } else {
                    list.add(interval)
                    interval = next
                }
            }

            list.add(interval)

        }



        return list.toTypedArray()
    }
}

fun main(args: Array<String>) {
    val list = MergeInterval().merge(arrayOf(intArrayOf(1, 3), intArrayOf(2,6), intArrayOf(8,10), intArrayOf(15,18)))
    for (interval in list) {
        println(interval.toList())
    }
}