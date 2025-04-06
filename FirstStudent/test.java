/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastructures;
public class test {
    public static void main(String[] args) {
        testPhotoManager();
        testAlbumFiltering();
        testPhotoDeletion();
    }

    public static void testPhotoManager() {
        System.out.println("=== Testing PhotoManager ===");
        PhotoManager manager = new PhotoManager();
        
        // Test adding photos
        Photo photo1 = new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green"));
        manager.addPhoto(photo1);
        
        Photo photo2 = new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind"));
        manager.addPhoto(photo2);
        
        Photo photo3 = new Photo("orange-butterfly.jpg", toTagsLinkedList("insect, butterfly, flower, color"));
        manager.addPhoto(photo3);
        
        // Verify photo count
        System.out.println("Total photos: " + manager.getPhotos().size()); // Should be 3
        
        // Print all photos
        System.out.println("\nAll photos:");
        printPhotos(manager.getPhotos());
    }

    public static void testAlbumFiltering() {
        System.out.println("\n=== Testing Album Filtering ===");
        PhotoManager manager = createTestManager();
        
        // Test single tag filter
        Album album1 = new Album("Wild Animals", "bear", manager);
        System.out.println("\nAlbum: " + album1.getName());
        System.out.println("Condition: " + album1.getCondition());
        System.out.println("Matching photos:");
        printPhotos(album1.getPhotos());
        System.out.println("Comparisons made: " + album1.getNbComps());
        
        // Test AND condition
        Album album2 = new Album("Animals in Grass", "animal AND grass", manager);
        System.out.println("\nAlbum: " + album2.getName());
        System.out.println("Condition: " + album2.getCondition());
        System.out.println("Matching photos:");
        printPhotos(album2.getPhotos());
        System.out.println("Comparisons made: " + album2.getNbComps());
        
        // Test empty condition
        Album album3 = new Album("All Photos", "", manager);
        System.out.println("\nAlbum: " + album3.getName());
        System.out.println("Condition: " + album3.getCondition());
        System.out.println("Matching photos:");
        printPhotos(album3.getPhotos());
    }

    public static void testPhotoDeletion() {
        System.out.println("\n=== Testing Photo Deletion ===");
        PhotoManager manager = createTestManager();
        
        // Before deletion
        System.out.println("Before deletion:");
        printPhotos(manager.getPhotos());
        
        // Delete a photo
        System.out.println("\nDeleting 'bear.jpg'");
        manager.deletePhoto("bear.jpg");
        
        // After deletion
        System.out.println("\nAfter deletion:");
        printPhotos(manager.getPhotos());
        
        // Verify album updates
        Album album = new Album("Test Delete", "bear", manager);
        System.out.println("\nPhotos in 'bear' album after deletion:");
        printPhotos(album.getPhotos()); // Should be empty
    }

    // Helper methods
    private static PhotoManager createTestManager() {
        PhotoManager manager = new PhotoManager();
        manager.addPhoto(new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green")));
        manager.addPhoto(new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind")));
        manager.addPhoto(new Photo("orange-butterfly.jpg", toTagsLinkedList("insect, butterfly, flower, color")));
        manager.addPhoto(new Photo("fox.jpg", toTagsLinkedList("animal, fox, forest, grass")));
        return manager;
    }

    private static LinkedList<String> toTagsLinkedList(String tags) {
        LinkedList<String> result = new LinkedList<>();
        String[] tagsArray = tags.split("\\s*,\\s*");
        for (String tag : tagsArray) {
            result.insert(tag);
        }
        return result;
    }

    private static void printPhotos(LinkedList<Photo> photos) {
        if (photos.empty()) {
            System.out.println("No photos found");
            return;
        }
        
        photos.findFirst();
        while (true) {
            Photo p = photos.retrieve();
            System.out.println("- " + p.getPath() + " [Tags: " + tagsToString(p.getTags()) + "]");
            if (photos.last()) break;
            photos.findNext();
        }
    }

    private static String tagsToString(LinkedList<String> tags) {
        StringBuilder sb = new StringBuilder();
        if (tags.empty()) return "";
        
        tags.findFirst();
        while (true) {
            sb.append(tags.retrieve());
            if (tags.last()) break;
            sb.append(", ");
            tags.findNext();
        }
        return sb.toString();
    }
}