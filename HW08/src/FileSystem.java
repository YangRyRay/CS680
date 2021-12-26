import java.util.LinkedList;

public class FileSystem {
    public FileSystem(){}
    private static FileSystem instance=null;

    public static FileSystem getInstance(){
        if(instance==null)
            instance = new FileSystem();
        return instance;
    }

    private LinkedList<Directory> rootDirs = new LinkedList<>();
    public LinkedList<Directory> getRootDirs(){return rootDirs;}
    public void appendRootDir(Directory root){
        rootDirs.add(root);
    }
    public void clearFS(){
        rootDirs.clear();
    }

}
