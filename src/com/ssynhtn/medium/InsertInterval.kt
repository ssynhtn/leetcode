package com.ssynhtn.medium

import java.util.*

class InsertInterval {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val list = mutableListOf<IntArray>()
        var index = Arrays.binarySearch(intervals, newInterval, {a, b -> a[0] - b[0]})


        if (index < 0) {
            val insertionIndex = -index - 1
            val prevIndex = insertionIndex - 1
            if (prevIndex >= 0 && newInterval[0] <= intervals[prevIndex][1]) {
                index = prevIndex
            } else {
                index = insertionIndex
            }
        }

        for (i in 0 until index) {
            list.add(intervals[i])
        }

        var nonOverlapIndex = -1
        for (i in index until intervals.size) {
            val current = intervals[i]

            if (!(current[0] <= newInterval[1] && newInterval[0] <= current[1])) {
                nonOverlapIndex = i
                break
            } else {
                newInterval[1] = Math.max(newInterval[1], current[1])
                newInterval[0] = Math.min(newInterval[0], current[0])
            }
        }

        list.add(newInterval)

        if (nonOverlapIndex != -1) {
            for (i in nonOverlapIndex until intervals.size) {
                list.add(intervals[i])
            }
        }

        return list.toTypedArray()
    }


}

fun main() {
    val intervals = arrayOf(
            intArrayOf(1, 5)
    )
    val newInterval = intArrayOf(6, 9)

    val merged = InsertInterval().insert(intervals, newInterval)
    for (interval in merged) {
        println(interval.toList())
    }
}