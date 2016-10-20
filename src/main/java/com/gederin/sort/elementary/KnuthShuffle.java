package com.gederin.sort.elementary;

import edu.princeton.cs.algs4.StdRandom;

import static com.gederin.sort.Util.exchange;

/**
 * 1. In iteration i, pick integer r between 0 and i uniformly at random.
 * 2. Swap a[i] and a[r]
 */
public class KnuthShuffle {
    public static void shuffle(Comparable[] a)
    {
        int N = a.length;
        for (int i = 0; i < N; i++)
        {
            int r = StdRandom.uniform(i + 1);
            exchange(a, i, r);
        }
    }
}
