package hackerrank;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by yogesh.bh on 28/04/18.
 */
public class QueueUsingTwoStacks {
    public static class MyQueue<T> {
        Stack<T> stackNewestOnTop = new Stack<T>();
        Stack<T> stackOldestOnTop = new Stack<T>();

        public void enqueue(T value) { // Push onto newest stack
            stackNewestOnTop.push(value);
        }

        public T peek() {
            if (!stackOldestOnTop.isEmpty())
                return stackOldestOnTop.peek();
            else if (!stackNewestOnTop.isEmpty()) {
                while (!stackNewestOnTop.isEmpty())
                    stackOldestOnTop.push(stackNewestOnTop.pop());
                return stackOldestOnTop.peek();
            }
            return null;
        }

        public T dequeue() {
            if (!stackOldestOnTop.isEmpty())
                return stackOldestOnTop.pop();
            else if (!stackNewestOnTop.isEmpty()) {
                while (!stackNewestOnTop.isEmpty())
                    stackOldestOnTop.push(stackNewestOnTop.pop());
                return stackOldestOnTop.pop();
            }
            return null;
        }
    }


    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}
