import java.time.LocalDateTime;

public class APFSFile extends APFSElement{
    public APFSFile(FSElement parent, String name, int size, LocalDateTime creationTime, String owner, LocalDateTime lastModified){
        super(parent,name,size,creationTime,owner,lastModified);
    }
    public int getTotalSize(){return size;}
    public boolean isDirectory(){return false;}
}
