/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastructures;
public class InvIndexPhotoManager {
    private BST<String> invertedIndex;
    private LinkedList<Photo> allPhotos;

    public InvIndexPhotoManager() {
        this.invertedIndex = new BST<>();
        this.allPhotos = new LinkedList<>();
    }

    public void addPhoto(Photo p) {
    // Add to main photo list
    allPhotos.insert(p);
    
    // Add to inverted index for each tag
    LinkedList<String> tags = p.getTags();
    if (!tags.empty()) {
        tags.findFirst();
        while (true) {
            String tag = tags.retrieve();
            invertedIndex.insert(tag, p);
            
            if (tags.last()) {
                break;
            }
            tags.findNext();
        }
    }
}

    public void deletePhoto(String path) {
    // Find the photo in main list
    Photo toDelete = null;
    allPhotos.findFirst();
    while (toDelete == null && !allPhotos.empty()) {
        Photo current = allPhotos.retrieve();
        if (current.getPath().equals(path)) {
            toDelete = current;
            allPhotos.remove();
        } else if (!allPhotos.last()) {
            allPhotos.findNext();
        }
    }
    
    if (toDelete != null) {
        // Remove from inverted index
        LinkedList<String> tags = toDelete.getTags();
        if (!tags.empty()) {
            tags.findFirst();
            while (true) {
                String tag = tags.retrieve();
                invertedIndex.delete(tag, toDelete);
                
                if (tags.last()) break;
                tags.findNext();
            }
        }
    }
}
    public LinkedList<Photo> searchPhotosByTag(String tag) {
        return invertedIndex.search(tag);
    }

    public BST<String> getInvertedIndex() {
        return invertedIndex;
    }

    public LinkedList<Photo> getAllPhotos() {
        return allPhotos;
    }
}