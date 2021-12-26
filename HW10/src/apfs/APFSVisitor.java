package apfs;

public interface APFSVisitor {
    public void visit(APFSLink link);
    public void visit(APFSDirectory dir);
    public void visit(APFSFile file);
}
