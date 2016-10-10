package com.gederin.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * PercolationStats class for the first programming assignment - percolation
 *
 * @author Ruslan Gederin
 */
public class PercolationStats {

    private double fractionsOpen[];
    private int trials;
    private double mean;
    private double stddev;
    private double trialsSqrt;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.trials = trials;
        this.fractionsOpen = new double[trials];
        int openSites;
        for (int i = 0; i < trials; i++) {
            openSites = this.runPercolation(n);
            this.fractionsOpen[i] = (double) openSites / (n * n);
        }
        mean = StdStats.mean(fractionsOpen);
        stddev = StdStats.stddev(fractionsOpen);
        trialsSqrt =  Math.sqrt(trials);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return mean - ((1.96 * stddev) / trialsSqrt);
    }

    public double confidenceHi() {
        return mean + ((1.96 * stddev) /trialsSqrt);
    }

    public static void main(String[] args) {

        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        System.out.println("mean                    = "
                + percolationStats.mean());
        System.out.println("stddev                  = "
                + percolationStats.stddev());
        System.out.println("95% confidence interval = "
                + percolationStats.confidenceLo() + ", "
                + percolationStats.confidenceHi());
    }

    private int runPercolation(int n) {
        int openSites = 0;
        int randI, randJ;
        Percolation percolation = new Percolation(n);
        while (!percolation.percolates()) {
            randI = this.generateRand(n);
            randJ = this.generateRand(n);

            if (!percolation.isOpen(randI, randJ)) {
                percolation.open(randI, randJ);
                openSites++;
            }
        }
        return openSites;
    }

    private int generateRand(int N) {
        int ret = StdRandom.uniform(N + 1);
        while (ret == 0) {
            ret = StdRandom.uniform(N + 1);
        }
        return ret;
    }
}
