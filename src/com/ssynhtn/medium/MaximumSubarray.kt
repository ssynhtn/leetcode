package com.ssynhtn.medium

class MaximumSubarray {
    fun maxSubArrayDivConquer(nums: IntArray): Int {
        return maxSubArrayDivConquer(nums, 0, nums.size)[0] // max, left max, right max, total, each requiring at least length one
    }

    // end > start
    fun maxSubArrayDivConquer(nums: IntArray, start: Int, end: Int): IntArray {
        if (end - start == 1) {
            val ans = nums[start]
            return intArrayOf(ans, ans, ans, ans)
        }

        val mid = (start + end) / 2
        val max1 = maxSubArrayDivConquer(nums, start, mid)
        val max2 = maxSubArrayDivConquer(nums, mid, end)

//        val total = max1[3] + max2[3]
//        val leftMax = Math.max(max1[1], max1[3] + max2[1])
//        val rightMax = Math.max(max2[2], max2[3] + max1[2])
//        val max = Math.max(max1[0], Math.max(max2[0], max1[2] + max2[1]))

        max1[0] = Math.max(max1[0], Math.max(max2[0], max1[2] + max2[1]))
        max1[1] = Math.max(max1[1], max1[3] + max2[1])
        max1[2] = Math.max(max2[2], max2[3] + max1[2])
        max1[3] = max1[3] + max2[3]

        return max1

    }


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
    println(MaximumSubarray().maxSubArrayDivConquer(intArrayOf(-2,1,-3,4,-1,2,1,-5,4)))
    println(MaximumSubarray().maxSubArrayDivConquer(intArrayOf(1)))
    println(MaximumSubarray().maxSubArrayDivConquer(intArrayOf(0)))
    println(MaximumSubarray().maxSubArrayDivConquer(intArrayOf(-1)))
    println(MaximumSubarray().maxSubArrayDivConquer(intArrayOf(-2147483647)))
}