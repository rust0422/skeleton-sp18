package hw3.hash;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    /* This should pass if your OomageTestUtility.haveNiceHashCodeSpread
       is correct. This is true even though our given ComplexOomage class
       has a flawed hashCode. */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();
        List<Integer> params = new ArrayList<>();

        /** Higher digits will be omitted
         *  Here we have 256 as base, which means we use 8 bits as bucket.
         *  int has 32 bits, so we have 4 buckets here.
         *  Any digits out of 4 buckets are irrelevant.
         *  So, when we randomly choose first number, that's fine.
         *
         */
        for (int i = 0; i < 5; i++) {
            params.add(1);
        }

        for (int i = 0; i < 1000; i += 1) {
            params.set(0, StdRandom.uniform(255));

            ComplexOomage o = new ComplexOomage(params);
            deadlyList.add(o);
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10));
    }

    /**
     * Calls tests for SimpleOomage.
     */
    public static void main(String[] args) {
        //jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
