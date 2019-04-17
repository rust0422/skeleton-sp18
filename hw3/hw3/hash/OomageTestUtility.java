package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] load = new int[M];
        for (Oomage o : oomages) {
            int h = o.hashCode();
            load[(h & 0x7FFFFFFF) % M] += 1;
        }
        int N = oomages.size();
        for (int l : load) {
            System.out.println(l);
            if (l * 50 < N) return false;
            if (l * 2.5 > N) return false;
        }
        return true;
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
    }
}
