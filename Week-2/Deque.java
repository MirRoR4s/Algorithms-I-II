import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Item[] deque;
    private int front = 0; // a pointer that point to the first deque element
    private int backNext = 1; // a pointer that point to the last deque element + 1
    private int size = 0; // size of the deque

    // construct an empty deque
    public Deque() {
        deque = (Item[]) new Object[1]; 
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
        // if the array is full, resize it.
        if (size == deque.length) {
            resize(size * 2);
        }
        int index = (front - 1 + deque.length) % deque.length;
        deque[index] = item;

        front = index; // 将 front 指向新的头元素
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        // if the array is full, resize it.
        if (size == deque.length) {
            resize(size * 2);
        }
        int index = backNext % deque.length;
        deque[index] = item;
        backNext =  (backNext + 1) % deque.length;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item res = deque[front];
        deque[front] = null;
        front = (front + 1) % deque.length;
        size--;
        // if capacity less than 1/4, resize it
        if (size / deque.length <= 0.25) {
            resize(deque.length / 2);
        }
        return res;
    }

    // remove and return the item from the back
    public Item removeLast() {
        int index = (backNext - 1 + deque.length) % deque.length;
        Item res = deque[index];
        deque[index] = null; // 避免对象游离
        backNext = index;
        size--;
        if (size / deque.length <= 0.25) {
            resize(deque.length / 2);
        }
        return res;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int currentIndex = front;
        public boolean hasNext() {
            return currentIndex != backNext;
        }

        public Item next() {
            Item res = deque[currentIndex];
            currentIndex = (currentIndex + 1) % deque.length;
            return res;
        }
    }
    private void resize(int capacity) {
        Item[] newDeque = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newDeque[i] = deque[i];
        }
        deque = newDeque;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);

        System.out.println(deque.size);

        // for (int i: deque) {
        //     System.out.println(i);
        // }
    }




}