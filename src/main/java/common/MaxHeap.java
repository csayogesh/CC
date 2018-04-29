package common;

import java.util.ArrayList;

/**
 * Created by yogesh.bh on 29/04/18.
 */
public class MaxHeap {
    private ArrayList<Integer> ls = new ArrayList<>();
    private int size = 0;

    public MaxHeap() {
    }

    public MaxHeap(ArrayList<Integer> ls) {
        this.ls = ls;
        size = ls.size();
        heapify();
    }

    public MaxHeap(int[] data) {
        for (int x : data)
            this.ls.add(x);
        size = data.length;
        heapify();
    }

    private void heapify() {
        for (int i = ls.size() / 2; i >= 0; i--)
            maxHeapify(i);
    }

    private void maxHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int largest;
        if (l < size && ls.get(l) > ls.get(i))
            largest = l;
        else largest = i;
        if (r < size && ls.get(r) > ls.get(largest))
            largest = r;
        if (largest != i) {
            int tmp = ls.get(i);
            ls.set(i, ls.get(largest));
            ls.set(largest, tmp);
            maxHeapify(largest);
        }
    }

    public int findMax() {
        return ls.get(0);
    }

    public int removeMax() {
        int ans = findMax();
        ls.set(0, ls.get(size - 1));
        size = size - 1;
        maxHeapify(0);
        return ans;
    }

    public void insert(int key) {
        if (ls.size() > size) ls.set(size, Integer.MIN_VALUE);
        else ls.add(Integer.MIN_VALUE);
        size++;
        increaseKey(size - 1, key);
    }

    private void increaseKey(int i, int key) {
        ls.set(i, key);
        while (i > 0 && ls.get(parent(i)) < ls.get(i)) {
            int tmp = ls.get(parent(i));
            ls.set(parent(i), ls.get(i));
            ls.set(i, tmp);
            i = parent(i);
        }
    }

    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

    private int parent(int i) {
        return i / 2;
    }

    public int getSize() {
        return size;
    }
}
