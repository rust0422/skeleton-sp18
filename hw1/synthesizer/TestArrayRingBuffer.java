package synthesizer;

import org.junit.Test;

public class TestArrayRingBuffer {
    @Test
    public void testPluckTheAString() {
        ArrayRingBuffer<Integer> b = new ArrayRingBuffer<>(10);
        b.enqueue(1);
        b.enqueue(2);
        b.enqueue(3);
        for (int x : b) {
            System.out.println(x);
        }
    }
}

