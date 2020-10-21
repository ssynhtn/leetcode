package com.ssynhtn.medium;

import com.ssynhtn.common.ListNode;
import com.sun.xml.internal.org.jvnet.fastinfoset.stax.LowLevelFastInfosetStreamWriter;
import jdk.internal.org.objectweb.asm.tree.LdcInsnNode;

import java.util.Arrays;

public class InsertionSort {
    public ListNode insertionSortList(ListNode head) {
        ListNode sorted = null;
        ListNode next = head;

        while (next != null) {
            ListNode temp = next.next;

            if (sorted == null) {
                sorted = next;
                next.next = null;
            } else if (sorted.val > next.val) {
                next.next = sorted;
                sorted = next;
            } else {
                ListNode node = sorted;
                while (node.next != null && node.next.val < next.val) {
                    node = node.next;
                }

                next.next = node.next;
                node.next = next;
            }

            next = temp;
        }

        return sorted;
    }

    public static void main(String[] args) {
        System.out.println(new InsertionSort().insertionSortList(ListNode.makeList(Arrays.asList(3,1,2,45,5,7))));
    }
}
