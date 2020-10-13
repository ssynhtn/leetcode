package com.ssynhtn.medium

import com.ssynhtn.common.TreeNode

fun constructMaximumBinaryTree(nums: IntArray): TreeNode? {
    return makeMaxTree(nums, 0, nums.size - 1)

}

fun makeMaxTree(nums: IntArray, left: Int, right: Int): TreeNode? {
    if (right < left) return null
    var max = Int.MIN_VALUE
    var maxIndex = -1
    for (i in left .. right) {
        if (nums[i] > max) {
            max = nums[i]
            maxIndex = i
        }
    }

    val root = TreeNode(max)
    root.left = makeMaxTree(nums, left, maxIndex - 1)
    root.right = makeMaxTree(nums, maxIndex + 1, right)

    return root
}
