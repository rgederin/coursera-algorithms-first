package com.gederin.sort.elementary;

import static com.gederin.sort.Util.exchange;
import static com.gederin.sort.Util.less;

/**
 * In iteration i, swap a[i] with each larger entry to its left.
 */
public class InsertionsSort {
    public static void sort(Comparable[] a) {
        int N = a.length;

        for (int i = 0; i < N; i++)

            for (int j = i; j > 0; j--)
                if (less(a[j], a[j - 1]))
                    exchange(a, j, j - 1);
                else break;
    }
}
