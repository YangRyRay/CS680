package apfs;

import FSFoundation.FSElement;

import java.time.LocalDateTime;

public abstract class APFSElement extends FSElement {
    String owner;
    LocalDateTime lastModified;

    public APFSElement(FSElement parent, String name, int size, LocalDateTime creationTime, String owner, LocalDateTime lastmodified){
        super(parent, name, size, creationTime);
        this.owner=owner;
        this.lastModified=lastmodified;
    }

    public abstract void accept(APFSVisitor v);
    public String getOwner() {return owner;}
    public void setOwner(String name){this.owner=name;}
    public LocalDateTime getLastModified(){return lastModified;}
    public void setLastModified(LocalDateTime time){this.lastModified=time;}
}
