package ibit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;

class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class MergeKSortedLists {
    public ListNode mergeKLists(ArrayList<ListNode> a) {
        TreeMap<Integer, LinkedList<Integer>> heap = new TreeMap<Integer, LinkedList<Integer>>();
        ListNode res = null;
        ListNode tmp = res;
        refill(heap, a);
        while (true) {
            if (tmp == null) {
                res = tmp = min(heap, a);
            } else {
                tmp.next = min(heap, a);
                tmp = tmp.next;
            }
            if (tmp == null)
                break;
        }
        return res;
    }

    public ListNode min(TreeMap<Integer, LinkedList<Integer>> heap,
                        ArrayList<ListNode> ls) {
        if (heap.size() == 0)
            refill(heap, ls);
        if (heap.size() == 0)
            return null;
        int r;
        ListNode res = ls.get(r = getValue(heap));
        if (res == null) {
        } else if (res.next != null) {
            put(heap, res.next.val, r);
        }
        ls.set(r, res.next);
        return res;
    }

    private int getValue(TreeMap<Integer, LinkedList<Integer>> heap) {
        LinkedList<Integer> ls = heap.get(heap.firstKey());
        int res = ls.removeFirst();
        if (ls.size() == 0) {
            heap.remove(heap.firstKey());
        }
        return res;
    }

    public void refill(TreeMap<Integer, LinkedList<Integer>> heap,
                       ArrayList<ListNode> lists) {
        for (int i = 0; i < lists.size(); i++) {

            if (lists.get(i) != null)
                put(heap, lists.get(i).val, i);
        }
    }

    private void put(TreeMap<Integer, LinkedList<Integer>> heap, int val, int i) {
        LinkedList<Integer> x = heap.get(val);
        if (x == null)
            heap.put(val, x = new LinkedList<Integer>());
        x.addLast(i);
    }
}