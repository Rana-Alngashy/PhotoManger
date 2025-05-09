/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Photo.java
    

package Album;
public class Photo {
    private String path;
    private LinkedList<String> allTags;

    // Constructor
    public Photo(String path, LinkedList<String> tags) {
        this.path = path;
        this.allTags = new LinkedList<String>();
        if (!tags.empty()) {
            tags.findFirst();
            while (true) {
                this.allTags.insert(tags.retrieve());
                if (tags.last()) break;
                tags.findNext();
            }
        }
    }

    // Return the path (full file name) of the photo. A photo is uniquely identified by its path.
    public String getPath() {
        return path;
    }

    // Return all tags associated with the photo
    public LinkedList<String> getTags() {
        LinkedList<String> copyTags = new LinkedList<String>();
        if (!allTags.empty()) {
            allTags.findFirst();
            while (true) {
                copyTags.insert(allTags.retrieve());
                if (allTags.last()) break;
                allTags.findNext();
            }
        }
        return copyTags;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Photo{" + "path=" + path + ", allTags=[");
        if (!allTags.empty()) {
            allTags.findFirst();
            while (true) {
                str.append(allTags.retrieve());
                if (allTags.last()) break;
                str.append("; ");
                allTags.findNext();
            }
        }
        str.append("]}");
        return str.toString();
    }
}