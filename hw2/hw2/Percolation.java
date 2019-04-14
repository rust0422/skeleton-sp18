package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF o;
    private boolean[][] open;
    private int top;
    private int bottom;
    private int N;

    public Percolation(int N) {
        // 2 more for top and bottom
        o = new WeightedQuickUnionUF(N * N + 2);
        this.N = N;
        this.top = N * N;
        this.bottom = N * N + 1;

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
    public int to2D(int row, int col) {
        if (isOut(row, col)) {
            throw new ArrayIndexOutOfBoundsException("Out of Boundary!!!");
        }
        return row * N + col;
    }

    public void open(int row, int col) {
        if (isOut(row, col)) {
            throw new ArrayIndexOutOfBoundsException("Out of Boundary!!!");
        }
        open[row][col] = true;
        if (row == 0) {
            o.union(to2D(row, col), top);
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
            }
        }

        for (int r = row - 1; r <= row + 1; r += 1) {
            if (isOut(r, col)) { //corner cases
                continue;
            }
            if (open[r][col]) {
                o.union(to2D(row, col), to2D(r, col));
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
        if (open[row][col] && o.connected(to2D(row, col), top)) {
            return true;
        }
        return false;
    }

    public int numberOfOpenSites() {
        int num = 0;
        for (int r = 0; r < N; r += 1) {
            for (int c = 0; c < N; c += 1) {
                if (open[r][c]) {
                    num += 1;
                }
            }
        }
        return num;
    }

    public boolean percolates() {
        /*for (int r = 0; r < N; r++) {
            if (isFull(N - 1, r)) {
                return true;
            }
        }
        return false;
        */
        return o.connected(top, bottom);
    }

    private boolean isOut(int row, int col) {
        if (row < 0 || row >= N)
            return true;
        if (col < 0 || col >= N)
            return true;
        return false;
    }
}
