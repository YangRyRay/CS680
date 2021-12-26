package apfs.util;

import apfs.APFSDirectory;
import apfs.APFSFile;
import apfs.APFSLink;
import apfs.APFSVisitor;

import java.util.LinkedList;

public class APFSFileCrawlingVisitor implements APFSVisitor {

    LinkedList<APFSFile> files = new LinkedList<>();

    public void visit(APFSLink link) {
        return;
    }

    public void visit(APFSDirectory dir) {
        return;
    }

    public void visit(APFSFile file) {
        files.add(file);
    }

    public LinkedList<APFSFile> getFiles() {
        return files;
    }
}
