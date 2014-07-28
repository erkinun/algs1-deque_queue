import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ERKIN on 28/07/2014.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    //will use a linked list impl here
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
    public void addFirst(Item item) {

        checkForNull(item);

        Node firstNode = new Node();
        firstNode.item = item;
        firstNode.next = null;
        firstNode.previous = null;

        if (first == null) {  //meaning deque is empty
            first = firstNode;
            last = first;
        } else {
            Node oldFirst = first;
            firstNode.next = oldFirst;
            oldFirst.previous = firstNode;
            first = firstNode;
        }

        size++;

    }

    public void addLast(Item item) {

        checkForNull(item);

        // insert the item at the end
        Node lastNode = new Node();
        lastNode.item = item;
        lastNode.next = null;
        lastNode.previous = null;

        if (last == null) {  //meaning also deque is empty
            last = lastNode;
            first = last;
        } else {
            Node oldLast = last;
            oldLast.next = lastNode;
            lastNode.previous = oldLast;
            last = lastNode;
        }

        size++;

    }
    public Item removeFirst() {
        // delete and return the item at the front

        checkForEmptyRemove();

        Node returnNode = first;

        size--;

        if (isEmpty()) {
            first = null;
            last = null;
        }
        else {
            first = returnNode.next;
            first.previous = null;
        }

        return returnNode.item;
    }
    public Item removeLast() {
        // delete and return the item at the end

        checkForEmptyRemove();

        Node returnNode = last;

        size--;

        if (isEmpty()) {
            last = null;
            first = null;
        } else {
            last = returnNode.previous;
            last.next = null;
        }

        return returnNode.item;

    }

    public static void main(String[] args) {
        // unit testing
        Deque<String> deque = new Deque<String>();
        deque.addFirst("5");
        deque.addFirst("10");

        String first = deque.removeFirst();

        //TODO add more

        deque.addFirst("15");
        deque.addFirst("20");
        deque.addFirst("25");

        for (String item : deque) {
            StdOut.print(item + ", ");
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {

            if (!hasNext()){
                throw new NoSuchElementException("there are no items in iter");
            }

            Item nextItem = current.item;
            current = current.next;
            return nextItem;
        }

        @Override
        public void remove() {
           throw new UnsupportedOperationException("trying to remove from iterator");
        }
    }

    private void checkForNull(Item item) {
        if (item == null) {
            throw new NullPointerException("trying to insert null item");
        }
    }

    private void checkForEmptyRemove() {
        if (isEmpty()) {
            throw new NoSuchElementException("trying to remove from an empty deque");
        }
    }

}
