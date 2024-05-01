import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Item[] deque; // 存储元素的数组
    private int front = 0; // 队首元素在数组中的索引
    private int backNext = 0;
    // 队尾元素数组索引 + 1，注意该值要对数组长度取模
    private int size = 0; // 队列中元素数量

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
        if (item == null) {
            throw new IllegalArgumentException();
        }
        // if the array is full, resize it.
        if (size == deque.length) {
            resize(size * 2);
        }
        front = (front - 1 + deque.length) % deque.length;
        deque[front] = item;
        size += 1;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        // if the array is full, resize it.
        if (size == deque.length) {
            resize(size * 2);
        }
        int index = backNext % deque.length;
        deque[index] = item;
        backNext = (backNext + 1) % deque.length;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item res = deque[front];
        deque[front] = null;
        front = (front + 1) % deque.length;
        size -= 1;
        // if capacity less than 1/4, resize it
        if ((double) size / deque.length <= 0.25) {
            resize(deque.length / 2);
        }
        return res;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int index = (backNext - 1 + deque.length) % deque.length;
        Item res = deque[index];
        deque[index] = null; // 避免对象游离
        backNext = index;
        size -= 1;
        if ((double) size / deque.length <= 0.25) {
            resize(deque.length / 2);
        }
        return res;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        System.out.println("调用了一次");
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {
        private int initialSize = size;
        private int currentIndex = front;
        private int currentNumber = 0;

        public boolean hasNext() {
            if (initialSize != size) {
                throw new UnsupportedOperationException();
            }
            return currentNumber != size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item res = deque[currentIndex];
            currentIndex = (currentIndex + 1) % deque.length;
            currentNumber += 1;
            return res;
        }
    }

    private void resize(int capacity) {
        /*
         * 扩容的时候注意调整队列元素在数组中的次序
         */
        Item[] newDeque = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            // 从 front 指向的队首元素开始
            newDeque[i] = deque[(front + i) % deque.length];
        }
        deque = newDeque;
        front = 0;
        backNext = size;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1); // 1
        deque.addFirst(2); // 2 1
        deque.addFirst(3); // 3 2 1
        deque.addLast(4); // 3 2 1 4
        System.out.println(deque.size()); // 4

        deque.removeFirst(); // 2 1 4
        deque.removeLast(); // 2 1
        System.out.println(deque.size()); // 2

        for (int i : deque) {
            System.out.println(i);
            // deque.removeFirst(); // UnsupportedOperationException
        }

        // deque = new Deque<>();
        // deque.removeLast(); // NoSuchElementException
        // deque.removeFirst(); // NoSuchElementException
        // deque.addLast(null); // IIlegalArgumentException

        Iterator<Integer> iterator = deque.iterator();
        iterator.next();
        iterator.next();
        // iterator.next(); // NoSuchElementException

    }


}
