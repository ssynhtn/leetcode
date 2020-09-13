package com.ssynhtn.medium

class Permutations {
    fun permute(nums: IntArray): List<List<Int>> {
        val collect = mutableListOf<List<Int>>()
        permute(nums, 0, collect)
        return collect
    }


    fun permute(nums: IntArray, fixCount: Int, collect: MutableList<List<Int>>) {
        if (fixCount >= nums.size - 1) {
            collect.add(nums.toList())
            return
        }

        permute(nums, fixCount + 1, collect)

        for (i in fixCount + 1 until nums.size) {
            val temp = nums[fixCount]
            nums[fixCount] = nums[i]
            nums[i] = temp
            permute(nums, fixCount + 1, collect)
            nums[i] = nums[fixCount]
            nums[fixCount] = temp
        }
    }
}

fun main(args: Array<String>) {
    val nums = intArrayOf(1, 2, 3)
    val ps = Permutations().permute(nums)
    println(ps)
    println(nums.toList())


}

