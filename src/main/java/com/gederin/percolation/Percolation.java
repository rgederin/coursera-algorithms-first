package com.gederin.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid2D;
    private boolean[] closeSiteBool;
    private WeightedQuickUnionUF unionUF;
    private WeightedQuickUnionUF unionUFForFull;
    private final int n;
    private int fakeTop;
    private int fakeBottom;


    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.fakeTop = n * n + 1;
        this.fakeBottom = n * n + 2;
        this.unionUF = new WeightedQuickUnionUF(n * n + 4);
        this.unionUFForFull = new WeightedQuickUnionUF(n * n + 4);
        this.initGrid2D();
        this.initClose();
    }

    public void open(int i, int j) {
        if (i <= 0 || i > n)
            throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > n)
            throw new IndexOutOfBoundsException("col index j out of bounds");
        if (this.isOpen(i, j)) {
            return;
        }
        int newOpenSite = this.grid2D[i - 1][j - 1];
        if (this.isTop(newOpenSite)) {
            unionUF.union(newOpenSite, fakeTop);
            unionUFForFull.union(newOpenSite, fakeTop);
        }
        if (this.isBottom(newOpenSite)) {
            unionUF.union(newOpenSite, fakeBottom);
        }
        this.closeSiteBool[newOpenSite - 1] = false;
        this.joinNeibors(i, j, newOpenSite);
    }

    public boolean isOpen(int i, int j) {
        if (i <= 0 || i > n)
            throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > n)
            throw new IndexOutOfBoundsException("col index j out of bounds");
        return closeSiteBool[this.getSite(i, j) - 1] == false;
    }

    public boolean isFull(int i, int j) {
        if (i <= 0 || i > n)
            throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > n)
            throw new IndexOutOfBoundsException("col index j out of bounds");
        return this.isOpen(i, j)
                && unionUFForFull.connected(this.getSite(i, j), fakeTop);
    }

    public boolean percolates() {
        return unionUF.connected(fakeTop, fakeBottom);
    }

    private boolean isOpen(int site) {
        if (this.closeSiteBool[site - 1] == false) {
            return true;
        } else {
            return false;
        }
    }

    private void joinNeibors(int i, int j, int newOpenSite) {
        int left = this.findLeftNeighbor(i, j);
        int right = this.findRightNeighbor(i, j);
        int top = this.findTopNeighbor(i, j);
        int bottom = this.findBottomNeighbor(i, j);
        if (left != -1 && isOpen(left)) {
            unionUF.union(newOpenSite, left);
            unionUFForFull.union(newOpenSite, left);
        }
        if (right != -1 && isOpen(right)) {
            unionUF.union(newOpenSite, right);
            unionUFForFull.union(newOpenSite, right);
        }
        if (top != -1 && isOpen(top)) {
            unionUF.union(newOpenSite, top);
            unionUFForFull.union(newOpenSite, top);
        }
        if (bottom != -1 && isOpen(bottom)) {
            unionUF.union(newOpenSite, bottom);
            unionUFForFull.union(newOpenSite, bottom);
        }
    }

    private int findLeftNeighbor(int i, int j) {
        if ((j - 1) != 0) {
            return this.grid2D[i - 1][j - 2];
        }
        return -1;
    }

    private int findRightNeighbor(int i, int j) {
        if ((j) != n) {
            return this.grid2D[i - 1][j];
        }
        return -1;
    }

    private int findTopNeighbor(int i, int j) {
        if ((i - 1) != 0) {
            return this.grid2D[i - 2][j - 1];
        }
        return -1;
    }

    private int findBottomNeighbor(int i, int j) {
        if ((i) != n) {
            return this.grid2D[i][j - 1];
        }
        return -1;
    }

    private int getSite(int row, int col) {
        int site = this.grid2D[row - 1][col - 1];
        return site;
    }

    private void initClose() {
        this.closeSiteBool = new boolean[n*n];
        for (int i = 0; i < n*n; i++) {
            this.closeSiteBool[i] = true;
        }
    }

    private void initGrid2D() {
        int count = 1;
        this.grid2D = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.grid2D[i][j] = count;
                count++;
            }
        }
    }

    /**
     * Check if site is on the top line
     *
     * @param site - site number
     * @return true iff site is on the top, otherwise - false
     */
    private boolean isTop(int site) {
        for (int i = 0; i < n; i++) {
            if (grid2D[0][i] == site) {
                return true;
            }
        }
        return false;
    }

    private boolean isBottom(int site) {
        for (int i = 0; i < n; i++) {
            if (grid2D[n - 1][i] == site) {
                return true;
            }
        }
        return false;
    }
}
