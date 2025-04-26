/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Album;
// LinkedList.java
public class LinkedList<T> {
    class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T val) {
            data = val;
            next = null;
        }
    }

    private Node<T> head;
    private Node<T> current;
    private int size;

    public LinkedList() {
        head = current = null;
        size = 0;
    }

    public boolean empty() {
        return head == null;
    }

    public boolean last() {
        return current != null && current.next == null;
    }

    public boolean full() {
        return false; // linked list is never "full"
    }

    public void findFirst() {
        current = head;
    }

    public void findNext() {
        if (current != null) current = current.next;
    }

    public T retrieve() {
        if (current != null) return current.data;
        return null;
    }

    public void update(T val) {
        if (current != null) current.data = val;
    }

    public void insert(T val) {
        Node<T> newNode = new Node<>(val);
        if (empty()) {
            head = current = newNode;
        } else {
            newNode.next = current.next;
            current.next = newNode;
            current = newNode;
        }
        size++;
    }

    public void remove() {
        if (empty() || current == null) return;

        if (current == head) {
            head = head.next;
            current = head;
        } else {
            Node<T> tmp = head;
            while (tmp.next != current && tmp.next != null) {
                tmp = tmp.next;
            }
            if (tmp.next != null) {
                tmp.next = current.next;
                current = tmp.next != null ? tmp.next : head;
            }
        }
        size--;
    }

    public int getSize() {
        return size;
    }
}