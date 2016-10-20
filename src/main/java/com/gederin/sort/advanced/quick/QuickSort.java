package com.gederin.sort.advanced.quick;

import edu.princeton.cs.algs4.StdRandom;

import static com.gederin.sort.Util.exchange;
import static com.gederin.sort.Util.less;

/**
 * 1. Shuffle the array.
 * 2. Partition so that, for some j
 * –      entry a[j] is in place
 * –      no larger entry to the left of j
 * –      no smaller entry to the right of j
 * 3. Sort each piece recursively.
 */
public class QuickSort {
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
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    /**
     * shuffle needed for performance guarantee
     */
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }
}
