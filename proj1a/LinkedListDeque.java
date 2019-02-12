public class LinkedListDeque<T> {
    public class TNode {
        public T item;
        public TNode prev;
        public TNode next;


        public TNode(T i, TNode p, TNode n) {
            item = i;
            prev = p;
            next = n;
        }

    }

    public TNode sentinel = new TNode(null, null, null);
    public int size;

    /**
     * Creates an empty LinkedListDeque
     */
    public LinkedListDeque() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Add a element to the start of list
     */
    public void addFirst(T item) {
        TNode n = new TNode(item, sentinel, sentinel.next);
        sentinel.next.prev = n;
        sentinel.next = n;
        size += 1;
    }

    /**
     * Add a element to the end of list
     */
    public void addLast(T item) {
        TNode n = new TNode(item, sentinel.prev, sentinel);
        sentinel.prev.next = n;
        sentinel.prev = n;
        size += 1;
    }

    /**
     * Return a boolean value if deque is empty
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Return the size of deque
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last
     */
    public void printDeque() {
        TNode n = sentinel.next;
        while (n != sentinel) {
            System.out.print(n.item);
            System.out.print('\t');
            n = n.next;
        }
    }

    /**
     * Removes and returns the item at the front of the deque
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T t = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return t;
    }

    /**
     * Removes and returns the item at the end of the deque
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T t = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return t;
    }

    /**
     * Gets the item at the given index
     */
    public T get(int index) {
        if (index > size) {
            return null;
        }

        TNode n = sentinel.next;
        for (int i = 0; i < index; i++) {
            n = n.next;
        }
        return n.item;
    }

    /**
     * Same as get, but uses recursion
     */
    public T getRecursive(int index) {
        if (index > size) {
            return null;
        }
        if (index == 0) {
            return sentinel.next.item;
        } else {
            LinkedListDeque<T> L = new LinkedListDeque<>();
            L.sentinel = sentinel.next;
            return L.getRecursive(index - 1);
        }
    }


/*    public static void main(String[] args) {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        L.addFirst(1);
        L.addLast(2);
        L.addLast(3);
        L.addFirst(4);
        System.out.println(L.get(1));
        System.out.println(L.getRecursive(1));
        L.printDeque();
    }*/

}
