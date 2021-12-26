import java.time.LocalDateTime;

abstract class FSElement {
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    protected FSElement parent;
    FSElement(){}
    FSElement(FSElement parent, String name, int size, LocalDateTime creationTime){
        this.parent=parent;
        this.name=name;
        this.size=size;
        this.creationTime=creationTime;
    }
    public FSElement getParent(){
        return parent;
    }
    public void setParent(FSElement parent){
        this.parent=parent;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getSize(){
        return size;
    }
    public void setSize(int size){
        this.size=size;
    }
    public LocalDateTime getCreationTime(){
        return creationTime;
    }
    public void setCreationTime(LocalDateTime creationTime){
        this.creationTime=creationTime;
    }

    public abstract int getTotalSize();
    public abstract boolean isDirectory();
}
