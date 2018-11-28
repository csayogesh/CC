package leet;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode prev = head;
        if (prev == null || k == 1)
            return prev;
        ListNode cur = head.next;
        if (cur == null)
            return prev;
        int cnt = countNodes(head);
        if (cnt < k)
            return head;
        ListNode next = cur.next;
        int noOfNodesReversed = 1;
        ListNode end = prev;
        while (cur != null && noOfNodesReversed < k) {
            cur.next = prev;
            noOfNodesReversed++;
            prev = cur;
            cur = next;
            if (next != null)
                next = next.next;
        }
        end.next = reverseKGroup(cur, k);
        return prev;
    }

    private int countNodes(ListNode head) {
        int ans = 0;
        while (head != null) {
            ans++;
            head = head.next;
        }
        return ans;
    }
}
