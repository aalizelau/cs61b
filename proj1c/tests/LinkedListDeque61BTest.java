import deque.ArrayDeque61B;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;

public class LinkedListDeque61BTest {
    @Test
    public void testIterator() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        Iterator<Integer> iterator = deque.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testEquals() {
        ArrayDeque61B<Integer> deque1 = new ArrayDeque61B<>();
        ArrayDeque61B<Integer> deque2 = new ArrayDeque61B<>();
        ArrayDeque61B<Integer> deque3 = new ArrayDeque61B<>();

        deque1.addLast(1);
        deque1.addLast(2);
        deque1.addLast(3);

        deque2.addLast(1);
        deque2.addLast(2);
        deque2.addLast(3);

        deque3.addLast(1);
        deque3.addLast(2);

        assertTrue(deque1.equals(deque2));
        assertFalse(deque1.equals(deque3));
        assertFalse(deque1.equals(null));
        assertFalse(deque1.equals("string"));
    }

    @Test
    public void testToString() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        String expected = "[1, 2, 3]";
        assertEquals(expected, deque.toString());

        deque.removeFirst();
        expected = "[2, 3]";
        assertEquals(expected, deque.toString());

        deque.removeLast();
        expected = "[2]";
        assertEquals(expected, deque.toString());

        deque.removeFirst();
        expected = "[]";
        assertEquals(expected, deque.toString());
    }
}
