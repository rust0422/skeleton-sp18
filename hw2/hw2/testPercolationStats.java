package hw2;

import org.junit.Test;

public class testPercolationStats {
    @Test
    public void testPercolationStats() {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(10, 20, pf);
        System.out.println(ps.confidenceLow());
        System.out.println(ps.confidenceHigh());
    }
}
