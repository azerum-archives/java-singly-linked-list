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
    private Node last = null;

    private void ensureCorrectFirstAndLastState() {
        //Если first еще не установлен, но last
        //уже куда-то ссылается, устанавливаем
        //first = last
        if (first == null && last != null) {
            first = last;
        }

        //Аналогично для last
        if (last == null && first != null) {
            last = first;
        }

        //То есть first и last либо вместе являются null,
        //либо вместе являются не-null. Не должно быть такого,
        //что одна ссылка установлена, а другая - нет
    }

    private int size = 0;

    public void addFirst(T value) {
        Node node = new Node(value, first);
        first = node;

        ensureCorrectFirstAndLastState();
        ++size;
    }

    public void addLast(T value) {
        Node node = new Node(value, null);

        if (last != null) {
            last.next = node;
        }

        last = node;

        ensureCorrectFirstAndLastState();
        ++size;
    }

    public T removeFirst() {
        throwNoSuchElementIfListIsEmpty();

        T removedValue = first.value;

        Node newFirst = first.next;
        first = newFirst;

        ensureCorrectFirstAndLastState();
        --size;

        return removedValue;
    }

    public T removeLast() {
        throwNoSuchElementIfListIsEmpty();

        T removedValue = last.value;

        if (size == 1) {
            last = null;
        }
        else {
            Node lastButOne = getNode(size - 2);
            lastButOne.next = null;

            last = lastButOne;
        }

        ensureCorrectFirstAndLastState();
        --size;

        return removedValue;
    }

    private void throwNoSuchElementIfListIsEmpty() {
        if (first == null) {
            throw new NoSuchElementException("Cannot remove element because list is empty");
        }
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

    public T get(int index) {
        if (index == size - 1) {
            return last.value;
        }

        return getNode(index).value;
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
