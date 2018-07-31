package ibit;

public class IsPalindrome {
    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public int lPalin(ListNode A) {
        int totalNodes = countNodes(A);
        if (totalNodes == 1)
            return 1;
        int diff = 1 + (totalNodes % 2 == 1 ? 1 : 0);
        ListNode midNode = getMidNode(A, totalNodes / 2 + diff);
        midNode = reverseList(midNode);
        ListNode st = A;
        while (midNode != null) {
            if (st.val != midNode.val)
                return 0;
            st = st.next;
            midNode = midNode.next;
        }
        return 1;
    }

    private ListNode reverseList(ListNode midNode) {
        ListNode prev = null;
        ListNode cur = midNode;
        ListNode next = midNode.next;
        while (cur != null) {
            cur.next = prev;
            prev = cur;
            cur = next;
            if (next != null)
                next = next.next;
        }
        return prev;
    }

    private ListNode getMidNode(ListNode ls, int k) {
        int cur = 1;
        ListNode head = ls;
        while (head != null) {
            if (cur == k)
                return head;
            head = head.next;
            cur++;
        }
        return null;
    }


    int countNodes(ListNode ls) {
        ListNode head = ls;
        int cnt = 0;
        while (head != null) {
            head = head.next;
            cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] ar = {1, 2, 3, 2, 1};
        ListNode st = new ListNode(ar[0]);
        ListNode tmp = st;
        for (int i = 1; i < ar.length; i++) {
            tmp.next = new ListNode(ar[i]);
            tmp = tmp.next;
        }
        int out = new IsPalindrome().lPalin(st);
        System.out.println(out);
    }
}
