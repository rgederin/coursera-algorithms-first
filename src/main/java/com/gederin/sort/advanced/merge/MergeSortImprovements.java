package com.gederin.sort.advanced.merge;

import edu.princeton.cs.algs4.Insertion;

import static com.gederin.sort.Util.less;

/**
 * 1. Use insertion sort for small subarrays
 * a. Mergesort has too much overhead for tiny subarrays
 * b. Cutoff to insertion sort for ? 7 items
 * <p>
 * 2. Stop if already sorted
 * a. Is biggest item in first half ? smallest item in second half?
 * <p>
 * 3. Eliminate the copy to the auxiliary array
 */
public class MergeSortImprovements {
    private static final int CUTOFF = 7;

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) aux[k] = a[j++];
            else if (j > hi) aux[k] = a[i++];
            else if (less(a[j], a[i])) aux[k] = a[j++];
            else aux[k] = a[i++];
        }
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo + CUTOFF - 1) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(aux, a, lo, mid);
        sort(aux, a, mid + 1, hi);
        if (!less(a[mid + 1], a[mid])) return;
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }
}
