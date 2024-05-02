import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> { // 16
    private int size = 0; // 4
    private Node first = null; // 8
    private Node last = null; // 8

    private class Node { // 8
        private Node prev = null; // 8
        private Node back = null; // 8
        private Item data; // 8

        public Node(Item data) {
            this.data = data;
        }
    }
    // 16 + 4 + 8 + 8 + 8 + (16 + 8 + 8 + 8 + 8) * N + 4 = 48 + 48N，N 是队列长度

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node(item);
        newNode.back = first;
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        }
        else {
            first.prev = newNode;
            first = newNode;
        }
        size += 1;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node newNode = new Node(item);
        newNode.prev = last;
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        }
        else {
            last.back = newNode;
            last = newNode;
        }
        size += 1;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item result = first.data;
        if (size == 1) {
            first = null;
            last = null;
        }
        else {
            first.back.prev = null;
            first = first.back;
        }
        size -= 1;
        return result;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item result = last.data;
        if (size == 1) {
            first = null;
            last = null;
        }
        else {
            last.prev.back = null;
            last = last.prev;
        }
        size -= 1;
        return result;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {
        private int initialSize = size;
        private Node p = first;

        public boolean hasNext() {
            if (initialSize != size) {
                throw new UnsupportedOperationException();
            }
            return p != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item result = p.data;
            p = p.back;
            return result;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.isEmpty();
        deque.addFirst(2);
        deque.addFirst(3);
        deque.removeLast();
        deque.removeLast();
        StdOut.println(deque.size());

    }


}
