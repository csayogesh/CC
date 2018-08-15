package leet;

public class SortLinkedList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        fast = slow.next;
        slow.next = null;

        ListNode ls1 = sortList(head);
        ListNode ls2 = sortList(fast);

        return mergeList(ls1, ls2);
    }

    private ListNode mergeList(ListNode ls1, ListNode ls2) {
        if (ls1 == null) return ls2;
        if (ls2 == null) return ls1;
        if (ls1.val < ls2.val) {
            ls1.next = mergeList(ls1.next, ls2);
            return ls1;
        } else {
            ls2.next = mergeList(ls1, ls2.next);
            return ls2;
        }
    }
}
