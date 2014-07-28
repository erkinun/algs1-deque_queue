import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DequeTest {

    private Deque<Integer> deque;

    @Before
    public void construct(){
        deque = new Deque<Integer>();
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertEquals(true, deque.isEmpty());

        deque.addFirst(5);

        assertEquals(false, deque.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(0, deque.size());

        deque.addFirst(5);
        deque.addFirst(10);

        assertEquals(2, deque.size());
    }

    @Test
    public void testAddFirst() throws Exception {
        deque.addFirst(5);
        deque.addFirst(10);

        Integer first = deque.removeFirst();

        assertEquals(10, (int)first);
    }

    @Test
    public void testAddLast() throws Exception {
        deque.addLast(5);
        deque.addLast(15);

        Integer last = deque.removeLast();

        assertEquals(15, (int)last);
    }

    @Test
    public void testRemoveFirst() throws Exception {
        deque.addFirst(5);
        deque.addFirst(10);

        Integer first = deque.removeFirst();

        assertEquals(10, (int)first);
    }

    @Test
    public void testRemoveLast() throws Exception {
        deque.addLast(5);
        deque.addLast(15);

        Integer last = deque.removeLast();

        assertEquals(15, (int)last);
    }

    @Test
    public void testMain() throws Exception {

    }
}