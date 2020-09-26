package com.ssynhtn.medium

class SubSets {
    fun subsets(nums: IntArray): List<List<Int>> {
        val collect = mutableListOf<List<Int>>()
        val fixed = mutableListOf<Int>()

        collect(fixed, 0, nums, collect)

        return collect
    }

    fun collect(fixed: MutableList<Int>, index: Int, nums: IntArray, collect: MutableList<List<Int>>) {
        if (index == nums.size) {
            collect.add(ArrayList<Int>(fixed))
            return
        }

        fixed.add(nums[index])
        collect(fixed, index + 1, nums, collect)
        fixed.removeAt(fixed.size - 1)
        collect(fixed, index + 1, nums, collect)
    }
}

fun main() {
    val subs = SubSets().subsets(intArrayOf())
    for (list in subs) {
        println(list)
    }
}