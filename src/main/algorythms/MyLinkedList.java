package com.ubs.cea;

public class MyLinkedList<E> {
    private ListNode head;
    private ListNode last;
    private ListNode tail;
    private int size;

    public MyLinkedList() {
        size = 0;
        head = new ListNode<E>(null);
        tail = new ListNode<E>(null);
        head.next = tail;
        tail.prev = head;
    }

    public void add(E value) throws NullPointerException {
        if (value == null) throw new NullPointerException();
        size++;
        if (last == null) {
            last = new ListNode<>(value);
            last.next = tail;
            last.prev = head;
            head.next = last;
            tail.prev = last;
        } else {
            ListNode newNode = new ListNode<>(value);
            newNode.prev = last;
            newNode.next = tail;
            last.next = newNode;
            head.prev = newNode;
            last = newNode;
        }
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode node = head.next;
        while (node.hasNext()) {
            sb.append(node);
            if (!node.next.equals(tail)) {
                sb.append(", ");
            }
            node = node.next;
        }
        return sb.toString();
    }

}

class ListNode<E> {
    ListNode prev;
    ListNode next;
    private E value;

    ListNode(E value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    boolean hasNext() {
        return next != null;
    }
}
