package com.ssynhtn.medium

import java.util.*

class TwoSum {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val copy = nums.clone()
        Arrays.sort(copy)

        for (i in 0 until copy.size) {
            val a = copy[i]
            val b = target - a

            val index = Arrays.binarySearch(copy, i + 1, copy.size, b)
            println(index)
            if (index >= 0) {
                var aIndex = -1
                var bIndex = -1

                for (j in 0 until nums.size) {
                    if (aIndex == -1 && nums[j] == a) {
                        aIndex = j
                    } else if (nums[j] == b) {
                        bIndex = j
                    }

                    if (aIndex >= 0 && bIndex >= 0) {
                        return intArrayOf(aIndex, bIndex)
                    }

                }
            }
        }

        return intArrayOf(-1, -1)
    }
}

fun main() {
    println(TwoSum().twoSum(intArrayOf(3,3), 6).toList())
}