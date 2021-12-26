import java.time.LocalDateTime;

public class APFSLink extends APFSElement{

    APFSElement target;

    public APFSLink(FSElement parent, String name, int size, LocalDateTime creationTime, String owner, LocalDateTime lastModified, APFSElement target){
        super(parent,name,size,creationTime, owner, lastModified);
        this.target = target;
    }
    public FSElement getTarget(){return target;}
    public int getTotalSize(){return size;}
    public boolean isDirectory(){return false;}
}
