package apfs.util;

import apfs.APFSDirectory;
import apfs.APFSFile;
import apfs.APFSLink;
import apfs.APFSVisitor;

public class APFSCountingVisitor implements APFSVisitor {

    int dirNum = 0;
    int fileNum = 0;
    int linkNum =0;

    public void visit(APFSLink link) {
        linkNum++;
    }
    public void visit(APFSDirectory dir) {
        dirNum++;
    }
    public void visit(APFSFile file) {
        fileNum++;
    }

    public int getDirNum(){
        return dirNum;
    }
    public int getFileNum() {
        return fileNum;
    }
    public int getLinkNum() {
        return linkNum;
    }
}
