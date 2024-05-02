import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> { // 16
    private int size;
    private Item[] queue;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[8];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Can't add null into a randomized queue");
        }
        if (size == queue.length) {
            resize(size * 2);
        }
        queue[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniformInt(size);
        Item result = queue[index];
        queue[index] = queue[size - 1];
        queue[--size] = null;
        if (size > 0 && size == queue.length / 4) {
            resize(queue.length / 2);
        }
        return result;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniformInt(size);
        return queue[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int initialSize = size;
        private int cnt = 0;
        private Item[] seq;

        public ArrayIterator() {
            seq = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                seq[i] = queue[i];
            }
            StdRandom.shuffle(seq);
        }

        public boolean hasNext() {
            if (initialSize != size) {
                throw new UnsupportedOperationException();
            }
            return cnt != size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item result = seq[cnt];
            cnt += 1;
            return result;
        }
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = queue[i];
        }
        queue = newArray;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.size();
        queue.enqueue(62);
        queue.enqueue(667);
        queue.enqueue(910);
        queue.isEmpty();
        queue.size();
        queue.enqueue(122);
        queue.enqueue(122);
        queue.enqueue(122);
        queue.enqueue(122);
        queue.enqueue(122);
        queue.enqueue(122);
        queue.enqueue(122);
        queue.enqueue(122);
        queue.enqueue(122);
        queue.enqueue(122);
        queue.enqueue(122);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(1);

        StdOut.println(queue.dequeue());
        queue.enqueue(2);
        StdOut.println(queue.sample());


    }

}
