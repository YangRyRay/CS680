package apfs.util;

import apfs.APFSDirectory;
import apfs.APFSFile;
import apfs.APFSLink;
import apfs.APFSVisitor;

import java.util.LinkedList;

public class APFSFileSearchVisitor implements APFSVisitor {
    String filename;
    LinkedList<APFSFile> foundFiles = new LinkedList<>();

    public APFSFileSearchVisitor(String filename){
        this.filename=filename;
    }

    @Override
    public void visit(APFSLink link) {
        return;
    }

    @Override
    public void visit(APFSDirectory dir) {
        return;
    }

    @Override
    public void visit(APFSFile file) {
        if (file.getName().equals(filename)){
            foundFiles.add(file);
        }
    }

    public LinkedList<APFSFile> getFoundFiles(){return foundFiles;}
}
