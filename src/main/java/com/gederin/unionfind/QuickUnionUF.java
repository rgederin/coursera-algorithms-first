package com.gederin.unionfind;

/**
 * Created by rgederin on 10/5/2016.
 */
public class QuickUnionUF {
    private int[] id;

    public QuickUnionUF(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }

    private int root(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }
}
