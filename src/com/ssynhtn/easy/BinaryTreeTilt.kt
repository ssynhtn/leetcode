package com.ssynhtn.easy

import com.ssynhtn.common.TreeNode

var tilt = 0
fun findTilt(root: TreeNode?): Int {
    findTiltAndSum(root)
    return tilt
}

fun findTiltAndSum(root: TreeNode?): Int {
    if (root == null) return 0
    val left = findTiltAndSum(root.left)
    val right = findTiltAndSum(root.right)

    tilt += Math.abs(left - right)
    return left + right + root.`val`

}


