package com.ssynhtn.medium

class SortColors {
    fun sortColors(nums: IntArray): Unit {
        var a= 0
        var b = 0
        var c = 0

        var has1 = false
        var has2 = false

        for (i in 0 until nums.size) {
            val x = nums[i]
            if (x == 2) {
                has2 = true
                c++
            } else if (x == 1) {
                has1 = true
                nums[b++] = 1
                if (has2) {
                    nums[c++] = 2
                } else {
                    c++
                }
            } else {
                nums[a++] = 0
                if (has1) {
                    nums[b++] = 1
                } else {
                    b++
                }
                if (has2) {
                    nums[c++] = 2
                } else {
                    c++
                }
            }
        }
    }
}

fun main() {
    val nums = intArrayOf()
    SortColors().sortColors(nums)
    println(nums.toList())
}