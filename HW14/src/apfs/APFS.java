package apfs;

import FSFoundation.FSElement;
import FSFoundation.FileSystem;

import java.time.LocalDateTime;

public class APFS extends FileSystem {
    public APFS(){};
    private static APFS instance=null;

    public static APFS getInstance(){
        if(instance==null)
            instance = new APFS();
        return instance;
    }

    public APFSDirectory createDefaultRoot(String name) {
        return new APFSDirectory(null, name, 0, LocalDateTime.now(), "User",LocalDateTime.now());
    }
    public APFSDirectory getRootDir(){return (APFSDirectory)this.getRootDirs().getLast();}
    public void clearFS(){
        rootDirs.clear();
    }
}
