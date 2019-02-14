public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /* Assume that starting point is on index 0

    /**
     * Create an empty array.
     */
    public ArrayDeque() {
        items = (T[]) new Object[5];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }


    /**
     * Insert an element to the back of ArrayDeque.
     */
    public void addLast(T t) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextLast] = t;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /**
     * Insert an element to the front of ArrayDeque.
     */
    public void addFirst(T t) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextFirst] = t;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    /**
     * Removes and returns the item at the front of the deque.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = plusOne(nextFirst);
        T t = items[nextFirst];
        // Avoid loitering
        items[nextFirst] = null;
        size -= 1;
        if (usageRatio() < 0.25) {
            resize(items.length / 2 + 1);// it avoid some problems.
        }
        return t;
    }

    /**
     * Removes and returns the item at the front of the deque.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = minusOne(nextLast);
        T t = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        if (usageRatio() < 0.25) {
            resize(items.length / 2 + 1);
        }
        return t;
    }

    /**
     * Get
     */
    public T get(int i) {
        if (i > size) {
            return null;
        }
        i = nextFirst + i + 1;
        // When it hits index length, go back to 0
        i = i % items.length;
        return items[i];
    }

    /**
     * Returns the number of items in the deque
     */
    public int size() {
        return size;
    }

    /**
     * Return true if deque is empty
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Prints the items in the deque from the first to last, separated by space
     */
    public void printDeque() {
        int p = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[p] + " ");
            p = plusOne(p);
        }
    }

    /**
     * Resize the underlying array to the target capcity.
     *
     * @param capacity
     */
    private void resize(int capacity) {
        T a[] = (T[]) new Object[capacity];
        int p = plusOne(nextFirst);
        for (int i = 1; i <= size; i++) {
            a[i] = items[p];
            p = plusOne(p);
        }

        items = a;
        // Set back these two variables.
        nextFirst = 0;
/*        // When reduce the length of items, nextLast may be out bounded
        if (nextLast >= items.length) {
            nextLast %= items.length;
        } else {
            nextLast = size + 1;
        }*/
        nextLast = size + 1;
    }

    /**
     * Helper function which does the loop
     *
     * @param i
     * @return
     */
    private int plusOne(int i) {
        i += 1;
        i = i % items.length;
        return i;
    }

    private int minusOne(int i) {
        i -= 1;
        return ((i < 0) ? i + items.length : i);
    }

    private float usageRatio() {
        return ((float) size / items.length);
    }

/*    public static void main(String[] args) {
        ArrayDeque<Integer> D = new ArrayDeque<>();
        D.addLast(0);
        D.printDeque();
        System.out.println();

        D.addLast(2);
        D.printDeque();
        System.out.println();

        D.addLast(3);
        D.printDeque();
        System.out.println();

        D.removeLast();
        D.printDeque();
        System.out.println();

        D.removeFirst();
        D.printDeque();
        System.out.println();

        D.addLast(6);
        D.printDeque();
        System.out.println();
    }*/
}
