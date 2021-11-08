import java.time.LocalDateTime;

public class File extends FSElement{
    public File(Directory parent, String name, int size, LocalDateTime creationTime){
        super(parent,name,size,creationTime);
    }
    public int getTotalSize(){return size;}
    public boolean isDirectory(){return false;}
}
