package com.gederin.sort.elementary;

import static com.gederin.sort.Util.exchange;
import static com.gederin.sort.Util.less;

/**
 * Created by rgederin on 10/20/2016.
 */
public class ShellSort {
    public static void sort(Comparable[] a)
    {
        int N = a.length;
        int h = 1;
        while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, ...
        while (h >= 1)
        { // h-sort the array.
            for (int i = h; i < N; i++)
            {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                    exchange(a, j, j - h);
            }

            h = h/3;
        }
    }
}
