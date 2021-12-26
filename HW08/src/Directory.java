import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;

public class Directory extends FSElement{
    public Directory(Directory parent, String name, int size, LocalDateTime creationTime){
        super(parent,name,size,creationTime);
    }


    private LinkedList<FSElement> children = new LinkedList<>();
    private LinkedList<Directory> subDirectories = new LinkedList<>();
    private LinkedList<File> files = new LinkedList<>();
    public LinkedList<FSElement> getChildren(){
        return children;
    }
    public void appendChild(FSElement child){
        children.add(child);
        child.setParent(this);
        if(child instanceof Directory){
            subDirectories.add((Directory) child);
        }
        if(child instanceof File){
            files.add((File)child);
        }
    }
    public int countChildren(){
        return children.size();
    }
    public LinkedList<Directory> getSubDirectories(){
        return subDirectories;
    }
    public LinkedList<File> getFiles(){
        return files;
    }
    public int getTotalSize(){
        int size = 0;
        LinkedList<FSElement> items = getChildren();
        for (FSElement obj : items) {
            size = size + obj.getTotalSize();
        }
        return size;
    }

    public boolean isDirectory(){return true;}
}
