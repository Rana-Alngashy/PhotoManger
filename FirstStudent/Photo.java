/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastructures;
public class Photo {
    private final String path;
    private final LinkedList<String> tags;
    
    public Photo(String path, LinkedList<String> tags) {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Path cannot be null or empty");
        }
        this.path = path;
        this.tags = copyTags(tags);
    }
    
    private LinkedList<String> copyTags(LinkedList<String> source) {
        LinkedList<String> copy = new LinkedList<>();
        if (source != null && !source.empty()) {
            source.findFirst();
            while (!source.last()) {
                copy.insert(source.retrieve());
                source.findNext();
            }
            copy.insert(source.retrieve());
        }
        return copy;
    }
    
    public String getPath() {
        return path;
    }
    
    public LinkedList<String> getTags() {
        return copyTags(this.tags); // Return defensive copy
    }
    
    public boolean hasTag(String tag) {
    if (tag == null || tags.empty()) return false;
    
    tags.findFirst();
    while (true) {
        if (tag.equals(tags.retrieve())) {
            return true;
        }
        if (tags.last()) {
            break;
        }
        tags.findNext();
    }
    return false;
}    
    @Override
    public String toString() {
        return "Photo[path='" + path + "', tags=" + tagsToString() + "]";
    }
    
    private String tagsToString() {
        LinkedList<String> tagsCopy = this.getTags();
        StringBuilder sb = new StringBuilder("[");
        
        if (!tagsCopy.empty()) {
            tagsCopy.findFirst();
            sb.append(tagsCopy.retrieve());
            
            while (!tagsCopy.last()) {
                tagsCopy.findNext();
                sb.append(", ").append(tagsCopy.retrieve());
            }
        }
        
        return sb.append("]").toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Photo)) return false;
        Photo other = (Photo) obj;
        return path.equals(other.path);
    }
    
    @Override
    public int hashCode() {
        return path.hashCode();
    }
}