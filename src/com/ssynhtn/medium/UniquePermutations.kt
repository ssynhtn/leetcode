package com.ssynhtn.medium

import java.util.*

class UniquePermutations {
    fun permuteUnique(nums: IntArray): List<List<Int>> {
        nums.sort()
        val collection = mutableListOf<List<Int>>()
        permute(nums, 0, collection)
        return collection

    }

    private fun permute(nums: IntArray, fixCount: Int, collection: MutableList<List<Int>>) {
        if (!checkIncreasing(nums, fixCount)) {
            return
        }

        if (fixCount == nums.size) {
            collection.add(nums.toList())
            return
        }

        permute(nums, fixCount + 1, collection)
        val index = fixCount
        for (i in index + 1 until nums.size) {
            if (nums[i] == nums[i - 1]) continue

            shiftRight(nums, index, i)
            permute(nums, fixCount + 1, collection)
            shiftLeft(nums, index, i)
        }
    }

    private fun shiftLeft(nums: IntArray, index: Int, i: Int) {
        val temp = nums[index]
        System.arraycopy(nums, index + 1, nums, index, i - index)
        nums[i] = temp
    }

    private fun shiftRight(nums: IntArray, index: Int, i: Int) {
        val temp = nums[i]
        System.arraycopy(nums, index, nums, index + 1, i - index)
        nums[index] = temp
    }


    private fun checkIncreasing(nums: IntArray, fixCount: Int): Boolean {
        for (i in fixCount until nums.size - 1) {
            if (nums[i] > nums[i + 1]) {
//                println("bad " + nums.toList() + ", fixCount " + fixCount)
                return false;
            }
        }

//        println("good " + nums.toList() + ", fixCount " + fixCount)
        return true;
    }

    fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }
}

fun main(args: Array<String>) {
    val nums = intArrayOf(0, 0, 1, 2)
    val res = UniquePermutations().permuteUnique(nums)
    println(res)
}