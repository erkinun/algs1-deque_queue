import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ERKIN on 28/07/2014.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] elements;

    public RandomizedQueue() {
        // construct an empty randomized queue
        size = 0;
        elements = (Item[]) new Object[2];
    }
    public boolean isEmpty() {
        // is the queue empty?
        return size == 0;
    }
    public int size() {
        // return the number of items on the queue
        return size;
    }
    public void enqueue(Item item) {
        // add the item
        checkForNull(item);

        if (size == elements.length) {
            resize(2*elements.length);
        }

        elements[size++] = item;
    }
    public Item dequeue() {
        // delete and return a random item

        checkForEmptyGet();

        int index =  StdRandom.uniform(size);

        //swap the index and the last
        Item deqItem = elements[index];
        elements[index] = elements[size-1];
        elements[size-1] = null;  //avoid loitering

        size--;

        if (size > 0 && size == elements.length/4) {
            resize(elements.length/2);
        }

        return deqItem;
    }
    public Item sample() {
        // return (but do not delete) a random item

        checkForEmptyGet();

        int index = StdRandom.uniform(size);

        return elements[index];
    }
    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new RandomQueueIterator(elements, size);
    }
    public static void main(String[] args) {
            // unit testing
        int[] array = new int[]{5, 10, 15, 1, 2, 3, 22, 101, 50};
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();

        for (int number: array) {
            randomizedQueue.enqueue(number);
        }

        Iterator<Integer> iter1 = randomizedQueue.iterator();
        Iterator<Integer> iter2 = randomizedQueue.iterator();

        while (iter1.hasNext()) {
            int item = iter1.next();
            StdOut.println("iter1: " + item);
        }

        while (iter2.hasNext()) {
            int item = iter2.next();
            StdOut.println("iter2: " + item);
        }

    }

    private class RandomQueueIterator implements Iterator<Item> {

        private Item[] iteratorItems;
        private int iterSize;

        RandomQueueIterator(Item[] items, int length) {

            int itemsLen = length;
            iteratorItems = (Item[]) new Object[itemsLen];


            Item[] tempArray = (Item[]) new Object[itemsLen];
            int i = 0;

            StdOut.println("creating the temp array");

            while (items[i] != null) {
                tempArray[i] = items[i];
                i++;
            }

            StdOut.println("copying them in random order");

            iterSize = 0;

            while (itemsLen > 0) {
                int index = StdRandom.uniform(itemsLen);

                Item elem = tempArray[index];
                iteratorItems[iterSize] = elem;

                tempArray[index] = tempArray[itemsLen-1];
                tempArray[itemsLen-1] = null;

                itemsLen--;
                iterSize++;
            }

            StdOut.println("finished creating the iterator");
            StdOut.println("length of iterator: " + iterSize);

        }

        @Override
        public boolean hasNext() {
            return iterSize > 0;
        }

        @Override
        public Item next() {

            if (!hasNext()) {
                throw new NoSuchElementException("empty iter!");
            }

            int index = iterSize - 1;
            iterSize--;
            return iteratorItems[index];

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("cannot use remove in iterator");
        }
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            temp[i] = elements[i];
        }

        elements = temp;
    }

    private void checkForNull(Item item) {
        if (item == null) {
            throw new NullPointerException("trying to insert null item");
        }
    }

    private void checkForEmptyGet() {
        if (isEmpty()) {
            throw new NoSuchElementException("trying to remove from an empty deque");
        }
    }
}
