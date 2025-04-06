/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastructures;
class BSTNode<T extends Comparable<T>> {
    T key;
    LinkedList<Photo> photos;
    BSTNode<T> left, right;

    public BSTNode(T key, Photo photo) {
        this.key = key;
        this.photos = new LinkedList<>();
        this.photos.insert(photo);
        this.left = this.right = null;
    }
}