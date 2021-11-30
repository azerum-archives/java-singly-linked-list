package edu.itstep.home4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class TestSinglyLinkedList {
    @Test
    public void test_addFirst() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        assertEquals(3, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(1, list.get(2));
    }

    @Test
    public void test_addLast() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Nested
    class Removing {
        private final SinglyLinkedList<Integer> fromOneToTen =
            new SinglyLinkedList<>();

        @BeforeEach
        public void fillList() {
            for (int i = 1; i <= 10; ++i) {
                fromOneToTen.addLast(i);
            }
        }

        @Test
        public void test_removeFirst() {
            for (int i = 1; i <= 10; ++i) {
                int removed = fromOneToTen.removeFirst();
                assertEquals(i, removed);
            }
        }

        @Test
        public void test_removeLast() {
            for (int i = 10; i >= 1; --i) {
                int removed = fromOneToTen.removeLast();
                assertEquals(i, removed);
            }
        }

        @Test
        public void test_methods_throw_NoSuchElementException_when_list_is_empty() {
            SinglyLinkedList<Integer> empty = new SinglyLinkedList<>();

            assertThrows(
                NoSuchElementException.class,
                empty::removeFirst,
                "removeFirst() didn't throw"
            );

            assertThrows(
                NoSuchElementException.class,
                empty::removeLast,
                "removeLast() didn't throw"
            );
        }
    }

    @Test
    public void test_get_throws_IndexOutOfBounds() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addLast(1);

        assertThrows(
            IndexOutOfBoundsException.class,
            () -> list.get(-1),
            "get() should throw when index < 0"
        );

        assertThrows(
            IndexOutOfBoundsException.class,
            () -> list.get(1),
            "get() should throw when index >= list.size()"
        );
    }

    @Test
    public void test_size() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assertEquals(3, list.size());
    }

    @Test
    public void test_foreach() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        final int count = 10;

        for (int i = 0; i < count; ++i) {
            list.addLast(i);
        }

        int i = 0;

        for (int value : list) {
            assertEquals(i, value);
            ++i;
        }

        assertEquals(count, i,  "iterator must traverse " + count + " elements");
    }
}
