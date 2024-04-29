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
        int index = (front - 1) % size;
        deque[index] = item;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        // if the array is full, resize it.
        int index = (backNext + 1) % size;
        deque[index] = item;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        
    }

    // remove and return the item from the back
    public Item removeLast() {

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {

    }

    // unit testing (required)
    public static void main(String[] args) {
        
    }

}