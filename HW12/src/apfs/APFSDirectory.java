package apfs;

import FSFoundation.FSElement;
import apfs.util.AlphabeticalComparator;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class APFSDirectory extends APFSElement {
    public APFSDirectory(FSElement parent, String name, int size, LocalDateTime creationTime, String owner, LocalDateTime lastModified){
        super(parent, name, size, creationTime, owner, lastModified);
    }


    private LinkedList<APFSElement> children = new LinkedList<>();
    private LinkedList<APFSDirectory> subDirectories = new LinkedList<>();
    private LinkedList<APFSFile> files = new LinkedList<>();
    public void appendChild(APFSElement child){
        children.add(child);
        Collections.sort(children,new AlphabeticalComparator());
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

    public LinkedList<APFSElement> getChildren(){
        return children;
    }
    public LinkedList<APFSDirectory> getSubDirectories(){
        return subDirectories;
    }
    public LinkedList<APFSFile> getFiles(){
        return files;
    }


    public LinkedList<APFSElement> getChildren(Comparator<APFSElement> comp){
        Collections.sort(children,comp);
        return children;
    }
    public LinkedList<APFSDirectory> getSubDirectories(Comparator<APFSElement> comp){
        Collections.sort(subDirectories,comp);
        return subDirectories;
    }
    public LinkedList<APFSFile> getFiles(Comparator<APFSElement> comp){
        Collections.sort(files,comp);
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
    public void accept(APFSVisitor v){
        v.visit(this);
        for(APFSElement e:children){
            e.accept(v);
        }
    }
}
