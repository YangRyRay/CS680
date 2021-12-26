import java.util.LinkedList;

abstract class FileSystem {
    protected String name;
    protected int capacity;
    protected int id;
    protected LinkedList<FSElement> rootDirs = new LinkedList<>();

    public FSElement initFileSystem(String name, int capacity){
        this.name=name;
        this.capacity=capacity;
        FSElement root = createDefaultRoot(name);
        if (root.isDirectory() && capacity >= root.getSize()) {
            setRoot(root);
            this.id = root.hashCode();
            return root;
        }else{
            return null;
        }
    }
    public abstract FSElement createDefaultRoot(String name);
    public void setRoot(FSElement root){rootDirs.add(root);}

    public int getCapacity() {return capacity;}
    public int getId() {return id;}
    public String getName() {return name;}
    public LinkedList<FSElement> getRootDirs() {return rootDirs;}
}
