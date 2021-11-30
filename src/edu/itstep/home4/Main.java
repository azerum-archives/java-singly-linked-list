package edu.itstep.home4;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> numbers = new SinglyLinkedList<>();

        for (int i = 0; i < 10; ++i) {
            numbers.addLast(i);
        }

        for (int value : numbers) {
            System.out.print(value + " ");
        }
    }
}
