package geek;

public class AddTwoLinkedList {
    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }

        Node() {
        }
    }

    Node addTwoLists(Node first, Node second) {
        Node ls1 = first;
        Node ls2 = second;
        Node output = addLists(ls1, ls2);
        return output;
    }

    private Node addLists(Node first, Node second) {
        Node start = null;
        Node cur = null;
        Node firstCur = first;
        Node secondCur = second;

        int carry = 0;
        while (firstCur != null || secondCur != null) {
            int sum = carry;
            if (firstCur != null) {
                sum += firstCur.data;
                firstCur = firstCur.next;
            }
            if (secondCur != null) {
                sum += secondCur.data;
                secondCur = secondCur.next;
            }
            Node n = new Node();
            n.data = sum % 10;
            carry = sum / 10;
            if (start == null) {
                start = n;
                cur = n;
            } else {
                cur.next = n;
                cur = n;
            }
        }
        if (carry > 0) {
            Node n = new Node();
            n.data = carry;
            cur.next = n;
        }

        return start;
    }

    private Node reverse(Node first) {
        Node node = first, prev = null, next = null;
        while (node != null) {
            next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        Node ls = new AddTwoLinkedList().createList(new int[]{9});
        Node ls2 = new AddTwoLinkedList().createList(new int[]{5});
        printList(new AddTwoLinkedList().addTwoLists(ls, ls2));
    }

    private static void printList(Node ls) {
        while (ls != null) {
            System.out.print(ls.data + " ");
            ls = ls.next;
        }
    }

    private Node createList(int[] ints) {
        Node st = null;
        Node cur = null;
        for (int val : ints) {
            Node n = new Node();
            n.data = val;
            if (st == null) {
                st = n;
                cur = n;
            } else
                cur.next = n;
            cur = n;
        }
        return st;
    }
}
