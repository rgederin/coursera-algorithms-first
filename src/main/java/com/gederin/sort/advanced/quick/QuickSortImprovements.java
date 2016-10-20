package com.gederin.sort.advanced.quick;

import edu.princeton.cs.algs4.Insertion;

import static com.gederin.sort.Util.exchange;
import static com.gederin.sort.Util.less;

/**
 * 1. Insertion sort small subarrays.
 * a. Even quicksort has too much overhead for tiny subarrays.
 * b. Cutoff to insertion sort for ? 10 items.
 * c. Note: could delay insertion sort until one pass at end.
 */
public class QuickSortImprovements {
    private static final int CUTOFF = 10;

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo]))
                if (i == hi) break;
            while (less(a[lo], a[--j]))
                if (j == lo) break;

            if (i >= j) break;
            exchange(a, i, j);
        }
        exchange(a, lo, j);
        return j;
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + CUTOFF - 1) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }
}
