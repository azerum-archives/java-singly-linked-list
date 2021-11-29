package edu.itstep;

import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements Iterable<T> {
    private class Node {
        public T value;
        public Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node first = null;
    private int size = 0;

    public void addFirst(T value) {
        Node node = new Node(value, first);
        first = node;

        ++size;
    }

    public void addLast(T value) {
        Node node = new Node(value, null);

        if (first == null) {
            first = node;
        }
        else {
            Node last = getNode(size - 1);
            last.next = node;
        }

        ++size;
    }

    public T removeFirst() {
        throwCannotRemoveElementIfFirstIsNull();

        T removedValue = first.value;

        Node newFirst = first.next;
        first = newFirst;

        --size;

        return removedValue;
    }

    public T removeLast() {
        throwCannotRemoveElementIfFirstIsNull();

        if (size == 1) {
            T removedValue = first.value;
            first = null;

            --size;

            return removedValue;
        }

        Node lastButOne = getNode(size - 2);

        T removedValue = lastButOne.next.value;
        lastButOne.next = null;

        --size;

        return removedValue;
    }

    private void throwCannotRemoveElementIfFirstIsNull() {
        if (first == null) {
            throw new NoSuchElementException("Cannot remove element because list is empty");
        }
    }

    public T get(int index) {
        return getNode(index).value;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node node = first;

        for (int i = 0; i < index; ++i) {
            node = node.next;
        }

        return node;
    }

    public int size() {
        return size;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node next = first;

            @Override
            public boolean hasNext() {
                return next != null;
            }

            @Override
            public T next() {
                T value = next.value;
                next = next.next;

                return value;
            }
        };
    }
}
