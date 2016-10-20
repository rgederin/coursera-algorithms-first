package com.gederin.sort.elementary;

import static com.gederin.sort.Util.exchange;
import static com.gederin.sort.Util.less;

/**
 * Selection sort:
 * <p>
 * 1. In iteration i, find index min of smallest remaining entry.
 * 2. Swap a[i] and a[min].
 */
public class SelectionSort {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++)
                if (less(a[j], a[min]))
                    min = j;
            exchange(a, i, min);
        }
    }
}
