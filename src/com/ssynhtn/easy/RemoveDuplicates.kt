package com.ssynhtn.easy

import com.ssynhtn.common.ListNode

class RemoveDuplicates {
//    fun deleteDuplicates(head: ListNode?): ListNode? {
//        if (head == null) return null
//        var prev: ListNode = head
//        while (prev.next != null) {
//            if (prev.`val` == prev.next!!.`val`) {
//                prev.next = prev.next!!.next
//            } else {
//                prev = prev.next!!
//            }
//        }
//        return head
//    }

    fun deleteDuplicates(head: ListNode?): ListNode? {
        if (head == null || head.next == null) return head
        var result: ListNode? = null
        var start: ListNode = head
        var end: ListNode = head  // [start, end]

        do {
            while (end.next != null && end.next!!.`val` == end.`val`) {
                end = end.next!!
            }

            if (start == end) {
                result = start
//                println("first non duplicate is " + result.`val`)
            } else {
                if (end.next == null) return null
                start = end.next!!
                end = end.next!!
            }

        } while (result == null)

        var prev = result
        var node = result.next

        while (node != null) {
            if (node.next == null || node.next!!.`val` != node.`val`) {
                prev = node
                node = node.next
            } else {
                node = node.next
                while (node!!.next != null && node.next!!.`val` == node.`val`) {
                    node = node.next
                }

                prev!!.next = node.next
                node = node.next
            }
        }

        return result

    }


}

fun generateNode(intArray: IntArray): ListNode? {
    if (intArray.isEmpty()) return null
    var head: ListNode? = null
    var node: ListNode? = null

    for (num in intArray) {
        if (node != null) {
            node.next = ListNode(num)
            node = node.next!!
        } else {
            node = ListNode(num)
            head = node
        }
    }

    return head
}


fun main() {
    println(RemoveDuplicates().deleteDuplicates(generateNode(intArrayOf(1,1,1, 2, 3))))
}