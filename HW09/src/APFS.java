import java.time.LocalDateTime;
import java.util.LinkedList;

public class APFS extends FileSystem{
    public APFS(){};
    private static APFS instance=null;

    public static APFS getInstance(){
        if(instance==null)
            instance = new APFS();
        return instance;
    }

    public FSElement createDefaultRoot(String name) {
        return new APFSDirectory(null,name,0, LocalDateTime.now(),"User",LocalDateTime.now());
    }
    public APFSDirectory getRootDir(){return (APFSDirectory)this.getRootDirs().getLast();}
    public void clearFS(){
        rootDirs.clear();
    }
}
