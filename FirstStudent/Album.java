/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastructures;

public class Album {
    private String name;
    private String condition;
    private PhotoManager manager;
    private int nbComps;

    public Album(String name, String condition, PhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
        this.nbComps = 0;
    }

    public LinkedList<Photo> getPhotos() {
        nbComps = 0;
        LinkedList<Photo> results = new LinkedList<>();
        
        // Empty condition returns all photos
        if (condition == null || condition.isEmpty()) {
            return manager.getPhotos();
        }

        String[] tags = condition.split(" AND ");
        BST<String> index = manager.getInvertedIndex();
        
        // Start with photos matching first tag
        LinkedList<Photo> candidates = index.search(tags[0].trim());
        nbComps += candidates.size();
        
        // Filter by remaining tags
        for (int i = 1; i < tags.length && !candidates.empty(); i++) {
            String tag = tags[i].trim();
            LinkedList<Photo> matching = index.search(tag);
            nbComps += matching.size();
            
            LinkedList<Photo> newCandidates = new LinkedList<>();
            candidates.findFirst();
            while (true) {
                Photo photo = candidates.retrieve();
                if (matching.contains(photo)) {
                    newCandidates.insert(photo);
                }
                
                if (candidates.last()) {
                    break;
                }
                candidates.findNext();
            }
            
            candidates = newCandidates;
        }
        
        return candidates;
    }

    public String getName() {
        return name;
    }

    public String getCondition() {
        return condition;
    }

    public int getNbComps() {
        return nbComps;
    }
}