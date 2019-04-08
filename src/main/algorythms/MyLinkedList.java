package com.ubs.cea;

public class MyLinkedList<E> {
    ListNode head;
    ListNode last;
    ListNode tail;
    private int size;

    public MyLinkedList() {
        size = 0;
        head = new ListNode(null);
        tail = new ListNode(null);
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
            ListNode newNode = new ListNode(value);
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

}

class ListNode<E> {
    ListNode prev;
    ListNode next;
    E value;

    public ListNode(E value) {
        this.value = value;
    }
}
