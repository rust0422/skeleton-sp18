package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF o;
    private WeightedQuickUnionUF o2;

    private boolean[][] open;
    private int top;
    private int bottom;
    private int N;
    private int count; // # of open sites

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N must bee positive.");
        }
        this.N = N;
        this.top = N * N;
        this.bottom = N * N + 1;

        // 2 more for top and bottom
        o = new WeightedQuickUnionUF(N * N + 2);
        o2 = new WeightedQuickUnionUF(N * N + 1); //no bottom for 02

        open = new boolean[N][N];
        //initialize all sites to be closed
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1)
                open[i][j] = false;
        }
    }

    /**
     * get index in WeightedQuickUnionUF
     *
     * @param row
     * @param col
     * @return
     */
    private int to2D(int row, int col) {
        if (isOut(row, col)) {
            throw new ArrayIndexOutOfBoundsException("Out of Boundary!!!");
        }
        return row * N + col;
    }

    public void open(int row, int col) {
        if (isOut(row, col)) {
            throw new ArrayIndexOutOfBoundsException("Out of Boundary!!!");
        }

        if (!open[row][col]) {
            open[row][col] = true;
            count += 1;
        }

        if (row == 0) {
            o.union(to2D(row, col), top);
            o2.union(to2D(row, col), top);
        }
        if (row == N - 1) {
            o.union(to2D(row, col), bottom);
        }


        // connect to surrouding sites.
        for (int c = col - 1; c <= col + 1; c += 1) {
            if (isOut(row, c)) { //corner cases
                continue;
            }
            if (open[row][c]) {
                o.union(to2D(row, col), to2D(row, c));
                o2.union(to2D(row, col), to2D(row, c));
            }
        }

        for (int r = row - 1; r <= row + 1; r += 1) {
            if (isOut(r, col)) { //corner cases
                continue;
            }
            if (open[r][col]) {
                o.union(to2D(row, col), to2D(r, col));
                o2.union(to2D(row, col), to2D(r, col));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (isOut(row, col)) {
            throw new ArrayIndexOutOfBoundsException("Out of Boundary!!!");
        }
        return open[row][col];
    }

    public boolean isFull(int row, int col) {
        if (isOut(row, col)) {
            throw new ArrayIndexOutOfBoundsException("Out of Boundary!!!");
        }
        if (!open[row][col]) {
            return false;
        }
        if (!o2.connected(to2D(row, col), top)) {
            return false;
        }
        return true;
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        return o.connected(top, bottom);
    }

    private boolean isOut(int row, int col) {
        if (row < 0 || row >= N)
            return true;
        if (col < 0 || col >= N)
            return true;
        return false;
    }

    public static void main(String[] args) {
    }
}
