package com.ssynhtn.medium

class Permutations {
    var swapCount = 0;
    fun permute(nums: IntArray): List<List<Int>> {
        val collect = mutableListOf<List<Int>>()
        permute(nums, 0, collect)
        return collect
    }

    fun permute2(nums: IntArray): List<List<Int>> {
        val collect = mutableListOf<List<Int>>()

        swapPermute2(nums, nums.size, collect)

        return collect
    }

    /**
     * 将最后size个进行permute, 结果添加到collect, 并且使得最后size个的顺序与输入相比为倒序
     * pre: size >= 1
     */
    fun swapPermute2(nums: IntArray, size: Int, collect: MutableList<List<Int>>) {
        if (size == 1) {
            collect.add(nums.toList())
            return
        }

        swapPermute2(nums, size - 1, collect)
        val index = nums.size - size
        var j = nums.size - 1;
        var i = index + 2

        while (j > index) {
            if (j > index) {
                val temp = nums[index]
                nums[index] = nums[j]
                nums[j] = temp
                swapPermute2(nums, size - 1, collect)
                j -= 2;
            }

            if (i < nums.size) {
                val temp = nums[i]
                nums[i] = nums[index]
                nums[index] = temp
                swapPermute2(nums, size - 1, collect)
                i += 2;
            }


        }

        if (size % 2 == 0) {
            reverse(nums, index + 1, nums.size - 1)
        }


    }

    private fun reverse(nums: IntArray, i: Int, j: Int) {
        var start = i
        var end = j
        while (start < end) {
            val temp = nums[start]
            nums[start] = nums[end]
            nums[end] = temp
            start++;
            end--;
        }
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
            swapCount += 2
        }
    }
}

fun main(args: Array<String>) {
    val nums = intArrayOf(1,2,3)
    val nums2 = nums.clone()

    val perm = Permutations()
    val perm2 = Permutations()

//    val ps = perm.permute(nums)
    val ps2 = perm2.permute2(nums)

//    println(ps)
//    println(nums.toList())
    println("swap count " + perm.swapCount)

    println("==2== ")
    println(ps2)
//    println(nums2.toList())
    println("swap count2 " + perm2.swapCount)



}

