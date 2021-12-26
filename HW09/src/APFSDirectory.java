import java.time.LocalDateTime;
import java.util.LinkedList;

public class APFSDirectory extends APFSElement{
    public APFSDirectory(FSElement parent, String name, int size, LocalDateTime creationTime, String owner, LocalDateTime lastModified){
        super(parent, name, size, creationTime, owner, lastModified);
    }


    private LinkedList<APFSElement> children = new LinkedList<>();
    private LinkedList<APFSDirectory> subDirectories = new LinkedList<>();
    private LinkedList<APFSFile> files = new LinkedList<>();
    public LinkedList<APFSElement> getChildren(){
        return children;
    }
    public void appendChild(APFSElement child){
        children.add(child);
        child.setParent(this);
        if(child instanceof APFSDirectory){
            subDirectories.add((APFSDirectory) child);
        }
        if(child instanceof APFSFile){
            files.add((APFSFile)child);
        }
    }
    public int countChildren(){
        return children.size();
    }
    public LinkedList<APFSDirectory> getSubDirectories(){
        return subDirectories;
    }
    public LinkedList<APFSFile> getFiles(){
        return files;
    }
    public int getTotalSize(){
        int size = 0;
        LinkedList<APFSElement> items = getChildren();
        for (FSElement obj : items) {
            size = size + obj.getTotalSize();
        }
        return size;
    }

    public boolean isDirectory(){return true;}
}
