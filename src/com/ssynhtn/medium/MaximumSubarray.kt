package com.ssynhtn.medium

class MaximumSubarray {
    fun maxSubArray(nums: IntArray): Int {
        var max = Int.MIN_VALUE

        var sum = 0
        for (num in nums) {
            if (num > max) {
                max = num
            }

            sum += num
            if (sum > max) {
                max = sum
            }

            if (sum < 0) {
                sum = 0
            }
        }

        return max
    }
}

fun main(args: Array<String>) {
    println(MaximumSubarray().maxSubArray(intArrayOf(1)))
    println(MaximumSubarray().maxSubArray(intArrayOf(0)))
    println(MaximumSubarray().maxSubArray(intArrayOf(-1)))
    println(MaximumSubarray().maxSubArray(intArrayOf(-2147483647)))
}