/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastructures;
public class BST<T extends Comparable<T>> {
    private class BSTNode {
        T key;
        LinkedList<Photo> photos;
        BSTNode left, right;

        BSTNode(T key, Photo photo) {
            this.key = key;
            this.photos = new LinkedList<>();
            this.photos.insert(photo);
            this.left = this.right = null;
        }
    }

    private BSTNode root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public void insert(T key, Photo photo) {
        root = insertRec(root, key, photo);
    }

    private BSTNode insertRec(BSTNode node, T key, Photo photo) {
        if (node == null) {
            size++;
            return new BSTNode(key, photo);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insertRec(node.left, key, photo);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, key, photo);
        } else {
            // Key exists, add photo if not already present
            if (!containsPhoto(node.photos, photo)) {
                node.photos.insert(photo);
            }
        }
        return node;
    }

    public LinkedList<Photo> search(T key) {
        BSTNode node = searchRec(root, key);
        return node != null ? node.photos : new LinkedList<>();
    }

    private BSTNode searchRec(BSTNode node, T key) {
        if (node == null || key.compareTo(node.key) == 0) {
            return node;
        }
        return key.compareTo(node.key) < 0 
               ? searchRec(node.left, key) 
               : searchRec(node.right, key);
    }

    public void delete(T key, Photo photo) {
        root = deleteRec(root, key, photo);
    }

    private BSTNode deleteRec(BSTNode node, T key, Photo photo) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = deleteRec(node.left, key, photo);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, key, photo);
        } else {
            // Found the node with the key
            removePhotoFromList(node.photos, photo);
            
            // If no photos left, remove the node completely
            if (node.photos.empty()) {
                size--;
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                }
                
                // Node with two children
                BSTNode successor = findMin(node.right);
                node.key = successor.key;
                node.photos = successor.photos;
                node.right = deleteRec(node.right, successor.key, successor.photos.retrieve());
            }
        }
        return node;
    }

    private BSTNode findMin(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private boolean containsPhoto(LinkedList<Photo> photos, Photo photo) {
        if (photos.empty()) return false;
        
        photos.findFirst();
        while (true) {
            if (photos.retrieve().equals(photo)) {
                return true;
            }
            if (photos.last()) break;
            photos.findNext();
        }
        return false;
    }

    private void removePhotoFromList(LinkedList<Photo> photos, Photo photo) {
        if (photos.empty()) return;
        
        photos.findFirst();
        while (true) {
            Photo current = photos.retrieve();
            if (current.equals(photo)) {
                photos.remove();
                break;
            }
            if (photos.last()) break;
            photos.findNext();
        }
    }

    public boolean contains(T key) {
        return searchRec(root, key) != null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }
}