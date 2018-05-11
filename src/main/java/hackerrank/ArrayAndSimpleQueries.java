package hackerrank;

import common.Treap;
import common.TreapNode;

import java.util.Scanner;

/**
 * Created by yogesh.bh on 11/05/18.
 */
public class ArrayAndSimpleQueries {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        Treap treap = new Treap();
        for (int i = 0; i < n; i++)
            treap.insertNode(new TreapNode(sc.nextInt(), i + 1, Math.random()));
        for (int i = 0; i < q; i++) {
            int type = sc.nextInt();
            int st = sc.nextInt();
            int en = sc.nextInt();
            TreapNode[] split1 = treap.split(st - 1);
            Treap inter = new Treap(split1[1]);
            TreapNode[] split2 = inter.split(en - st + 1);
            if (type == 1) {
                treap = new Treap(new Treap(split2[0], split1[0]).getRoot(), split2[1]);
            } else
                treap = new Treap(new Treap(split1[0], split2[1]).getRoot(), split2[0]);
        }
        int ans1 = Math.abs(treap.find(1) - treap.find(n));
        System.out.println(ans1);
        treap.printData();
    }
}
