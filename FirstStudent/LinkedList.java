/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastructures;
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
    Node<T> current;
    private int size;
    
    public LinkedList() {
        head = current = null;
        size = 0;
    }
    
    // Existing methods (unchanged)
    public boolean empty() {
        return head == null;
    }
    
    public boolean last() {
        return current.next == null;
    }
    
    public boolean full() {
        return false;
    }
    
    public void findFirst() {
        current = head;
    }
    
    public void findNext() {
        if (current != null)
            current = current.next;
    }
    
    public T retrieve() {
        return current != null ? current.data : null;
    }
    
    public void update(T val) {
        if (current != null)
            current.data = val;
    }
    
    // Modified insert to add at the end by default
    public void insert(T val) {
        Node<T> newNode = new Node<>(val);
        if (empty()) {
            head = current = newNode;
        } else {
            Node<T> tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = newNode;
        }
        size++;
    }
    
    public void remove() {
        if (current == null) return;
        
        if (current == head) {
            head = head.next;
            current = head;
        } else {
            Node<T> tmp = head;
            while (tmp.next != current) {
                tmp = tmp.next;
            }
            tmp.next = current.next;
            current = tmp.next;
        }
        size--;
    }
    
    // New methods needed for the project
    
    public int size() {
        return size;
    }
    
    public boolean contains(T val) {
        Node<T> tmp = head;
        while (tmp != null) {
            if (tmp.data.equals(val)) {
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }
    
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        Node<T> tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.data;
    }
    
    public void clear() {
        head = current = null;
        size = 0;
    }
    
    // Helper method to convert to array (useful for debugging)
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node<T> tmp = head;
        for (int i = 0; i < size; i++) {
            arr[i] = tmp.data;
            tmp = tmp.next;
        }
        return arr;
    }
}