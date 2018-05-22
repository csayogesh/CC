package hckrnk;

import common.MaxHeap;
import common.MinHeap;

import java.util.Scanner;

/**
 * Created by yogesh.bh on 29/04/18.
 */
public class RunningMedian {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int a_i = 0; a_i < n; a_i++) {
            a[a_i] = in.nextInt();
        }
        runningMedian(a);
    }

    private static void runningMedian(int[] a) {
        MaxHeap maxHeap = new MaxHeap();
        MinHeap minHeap = new MinHeap();

        for (int x : a) {
            if (maxHeap.getSize() == 0)
                maxHeap.insert(x);
            else if (maxHeap.findMax() < x)
                minHeap.insert(x);
            else maxHeap.insert(x);
            while (maxHeap.getSize() - minHeap.getSize() > 1) minHeap.insert(maxHeap.removeMax());
            while (minHeap.getSize() > maxHeap.getSize())
                maxHeap.insert(minHeap.removeMin());
            if (minHeap.getSize() == maxHeap.getSize()) {
                double med = minHeap.findMin() + maxHeap.findMax();
                System.out.println(med / 2);
            } else
                System.out.println((double) maxHeap.findMax());
        }
    }
}
