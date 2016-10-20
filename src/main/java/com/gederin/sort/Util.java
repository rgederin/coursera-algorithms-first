package com.gederin.sort;

/**
 * Created by rgederin on 10/20/2016.
 */
public class Util {
    /**
     * Is item v less than w ?
     */
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * Swap item in array a[] at index i with the one at index j.
     */
    public static void exchange(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /**
     * Test if an array is sorted
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }
}
