package com.ssynhtn.easy

import com.ssynhtn.common.TreeNode

class SameTree {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null) {
            return q == null
        }

        if (q == null) return false

        if (p.`val` != q.`val`) return false

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    }
}

