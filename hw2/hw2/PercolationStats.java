package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    final static double COEF = 1.96;

    int T;
    double[] res;
    int Ns; //N squared

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.T = T;
        this.Ns = N * N;

        res = new double[T];

        Percolation p = pf.make(N);

        for (int t = 0; t < T; t += 1) {
            int s = 0;
            while (!p.percolates()) {
                int r = StdRandom.uniform(0, N);
                int c = StdRandom.uniform(0, N);
                if (!p.isOpen(r, c)) {
                    p.open(r, c);
                    s += 1;
                }
            }
            res[t] = (double) s / Ns;
        }
    }


    public double mean() {
        return StdStats.mean(res);
    }

    public double stddev() {
        return StdStats.stddev(res);
    }

    public double confidenceLow() {
        return mean() - COEF * stddev() / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return mean() + COEF * stddev() / Math.sqrt(T);
    }
}
