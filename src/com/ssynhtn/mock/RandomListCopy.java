package com.ssynhtn.mock;

import com.ssynhtn.common.Node;

import java.util.HashMap;
import java.util.Map;

public class RandomListCopy {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<>();
        Node _head = new Node(head.val);
        Node _tail = _head;

        map.put(head, _head);
        if (head.random == head) {
            _head.random = _head;
        } else if (head.random != null) {
            _head.random = new Node(head.random.val);
            map.put(head.random, _head.random);
        }


        Node node = head.next;
        while (node != null) {
            Node _node = map.get(node);
            if (_node == null) {
                _node = new Node(node.val);
                map.put(node, _node);
            }

            _tail.next = _node;
            _tail = _node;

            if (node.random != null) {
                Node _random = map.get(node.random);
                if (_random == null) {
                    _random = new Node(node.random.val);
                    map.put(node.random, _random);
                }
                _node.random = _random;
            }

            node = node.next;
        }


        return _head;
    }


}
