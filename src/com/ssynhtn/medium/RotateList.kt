package com.ssynhtn.medium

class RotateList {
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head == null) return null
        if (k == 0) return head

        var node = head
        var length = 0
        while (length < k && node != null) {
            node = node.next
            length++
        }

        if (node == null) {
            var rotate = k % length
            if (rotate == 0) return head

            node = head
            length = 0
            while (length < rotate) {
                node = node?.next
                length++
            }
        }

        var prev = head
        while (node?.next != null) {
            node = node.next
            prev = prev?.next
        }

        node?.next = head
        val result = prev?.next
        prev?.next = null


        return result
    }
}
class ListNode(var `val`: Int) {
    var next: ListNode? = null
}