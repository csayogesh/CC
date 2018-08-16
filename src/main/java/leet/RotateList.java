package leet;

public class RotateList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;

        int size = 1;
        ListNode end = head;
        while (end.next != null) {
            size++;
            end = end.next;
        }
        int n = size - k % size;
        ListNode pHead = new ListNode(0);
        pHead.next = head;
        ListNode st = pHead;
        while (n > 0) {
            st = st.next;
            n--;
        }
        end.next = pHead.next;
        ListNode ans = st.next;
        st.next = null;
        return ans;
    }
}
