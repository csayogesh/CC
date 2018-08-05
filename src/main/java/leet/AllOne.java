package leet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class AllOne {
    class Node {
        int value;
        Set<String> keys = new HashSet<>();
        Node prev, next;

        public Node(int value) {
            this.value = value;
        }
    }

    Node head, tail;
    Map<String, Node> nodeMap = new HashMap<>();

    /**
     * Initialize your data structure here.
     */
    public AllOne() {
        head = new Node(0);
        tail = new Node(0);
        join(head, tail);
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (nodeMap.containsKey(key)) {
            Node cur = nodeMap.get(key);
            Node prev = cur.prev;
            Node next = cur.next;
            int newVal = cur.value + 1;
            cur.keys.remove(key);
            if (cur.keys.size() == 0) {
                join(prev, next);
                cur = prev;
            }
            if (next.value == newVal) {
                next.keys.add(key);
                nodeMap.put(key, next);
            } else {
                Node newNode = new Node(newVal);
                newNode.keys.add(key);
                insertBetween(cur, next, newNode);
                nodeMap.put(key, newNode);
            }
        } else {
            Node firstNode = head.next;
            if (firstNode == tail) {
                Node newNode = new Node(1);
                newNode.keys.add(key);
                insertBetween(head, tail, newNode);
                nodeMap.put(key, newNode);
            } else {
                if (firstNode.value == 1) {
                    firstNode.keys.add(key);
                    nodeMap.put(key, firstNode);
                } else {
                    Node newNode = new Node(1);
                    newNode.keys.add(key);
                    insertBetween(head, firstNode, newNode);
                    nodeMap.put(key, newNode);
                }
            }
        }
    }

    private void insertBetween(Node prev, Node next, Node newNode) {
        join(prev, newNode);
        join(newNode, next);
    }

    private void join(Node prev, Node next) {
        prev.next = next;
        next.prev = prev;
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        if (nodeMap.containsKey(key)) {
            Node cur = nodeMap.get(key);
            Node prev = cur.prev;
            Node next = cur.next;
            cur.keys.remove(key);
            nodeMap.remove(key);
            if (cur.keys.size() == 0) {
                removeNode(cur);
            }
            if (cur.value != 1) {
                int newVal = cur.value - 1;
                if (prev.value != newVal) {
                    Node newNode = new Node(newVal);
                    newNode.keys.add(key);
                    insertBetween(prev, next, newNode);
                    nodeMap.put(key, newNode);
                } else {
                    prev.keys.add(key);
                    nodeMap.put(key, prev);
                }
            }
        }
    }

    private void removeNode(Node cur) {
        Node prev = cur.prev;
        Node next = cur.next;
        join(prev, next);
        cur.prev = null;
        cur.next = null;
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        if (head.next == tail) return "";
        return tail.prev.keys.iterator().next();
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        if (head.next == tail) return "";
        return head.next.keys.iterator().next();
    }

    public static void main(String[] args) {
        AllOne one = new AllOne();
        one.inc("a");
        one.inc("a");
        System.out.println(one.getMinKey());
        System.out.println(one.getMaxKey());
        System.out.println(one.getMinKey());
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */