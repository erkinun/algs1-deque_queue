import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ERKIN on 28/07/2014.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] elements;

    public RandomizedQueue(){
        // construct an empty randomized queue
        size = 0;
        elements = (Item[]) new Object[2];
    }
    public boolean isEmpty(){
        // is the queue empty?
        return size == 0;
    }
    public int size(){
        // return the number of items on the queue
        return size;
    }
    public void enqueue(Item item){
        // add the item
        checkForNull(item);

        if ( size == elements.length ){
            resize(2*elements.length);
        }

        elements[size++] = item;
    }
    public Item dequeue(){
        // delete and return a random item

        checkForEmptyGet();

        int index =  StdRandom.uniform(size);

        //swap the index and the last
        Item deqItem = elements[index];
        elements[index] = elements[size-1];
        elements[size-1] = null;  //avoid loitering

        size--;

        if ( size > 0 && size == elements.length/4 ){
            resize(elements.length/2);
        }

        return deqItem;
    }
    public Item sample(){
        // return (but do not delete) a random item

        checkForEmptyGet();

        int index = StdRandom.uniform(size);

        return elements[index];
    }
    public Iterator<Item> iterator(){
        // return an independent iterator over items in random order
        throw new RuntimeException();
    }
    public static void main(String[] args){
            // unit testing
        throw new RuntimeException();
    }

    private void resize(int capacity){
        Item[] temp = (Item[]) new Object[capacity];

        for( int i = 0; i < size; i++ ){
            temp[i] = elements[i];
        }

        elements = temp;
    }

    private void checkForNull(Item item) {
        if ( item == null ){
            throw new NullPointerException("trying to insert null item") ;
        }
    }

    private void checkForEmptyGet(){
        if ( isEmpty() ){
            throw new NoSuchElementException("trying to remove from an empty deque");
        }
    }
}
