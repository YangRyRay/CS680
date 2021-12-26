import apfs.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class APFSFileTest {
    private static APFS sys = APFS.getInstance();
    private static APFSDirectory root = new APFSDirectory(null,"root",0, LocalDateTime.now(),"User",LocalDateTime.now());
    private static APFSDirectory applications = new APFSDirectory(root, "applications", 0, LocalDateTime.now(),"User",LocalDateTime.now());
    private static APFSDirectory home=new APFSDirectory(root,"home",0,LocalDateTime.now(),"User",LocalDateTime.now());
    private static APFSDirectory code=new APFSDirectory(home, "code",0,LocalDateTime.now(),"User",LocalDateTime.now());
    private static APFSFile a = new APFSFile(applications, "a", 25, LocalDateTime.now(),"User",LocalDateTime.now());
    private static APFSFile b = new APFSFile(home, "b",30,LocalDateTime.now(),"User",LocalDateTime.now());
    private static APFSFile c = new APFSFile(home, "c",35,LocalDateTime.now(),"User",LocalDateTime.now());
    private static APFSFile d = new APFSFile(home, "d",40,LocalDateTime.now(),"User",LocalDateTime.now());
    private static APFSLink x = new APFSLink(home, "x", 0, LocalDateTime.now(),"User",LocalDateTime.now(), applications);
    private static APFSLink y = new APFSLink(code, "y", 0, LocalDateTime.now(),"User",LocalDateTime.now(), a);

    @BeforeAll
    public static void FileSystemSetUp(){
        sys = APFS.getInstance();
        sys.clearFS();
        sys.initFileSystem("root",100000);
        root.appendChild(applications);
        applications.appendChild(a);
        root.appendChild(home);
        home.appendChild(code);
        home.appendChild(b);
        home.appendChild(x);
        code.appendChild(c);
        code.appendChild(d);
        code.appendChild(y);
    }

    private String[] fileToStringArray(APFSFile file){
        String[] fileInfo = {String.valueOf(file.getParent()),
                file.getName(),
                String.valueOf(file.getSize()),
                file.getOwner()};
        return fileInfo;
    }

    //Method Tests
    @Test
    public void FileCreationTest(){
        APFSFile target = new APFSFile(applications, "target", 30, LocalDateTime.now(),"User", LocalDateTime.now());
        String[] expected = {String.valueOf(applications), "target", "30","User"};
        assertArrayEquals(expected,fileToStringArray(target));
    }
    @Test
    public void GetTotalSizeTest(){
        int size = a.getTotalSize();
        int expected = a.getSize();
        assertEquals(expected,size);
    }
    @Test
    public void IsDirectoryTest(){
        boolean isItDir = a.isDirectory();
        assertFalse(isItDir);
    }
    //InstanceTests
    @Test
    public void aFileTest(){
        String[] aInfo = {a.getName(),
                String.valueOf(a.getSize()),
                String.valueOf(a.getParent())};
        String[] expected = {"a", "25",String.valueOf(applications)};
        assertArrayEquals(expected,aInfo);
    }
    @Test
    public void bFileTest(){
        String[] bInfo = {b.getName(),
                String.valueOf(b.getSize()),
                String.valueOf(b.getParent())};
        String[] expected = {"b", "30",String.valueOf(home)};
        assertArrayEquals(expected,bInfo);
    }
    @Test
    public void cFileTest(){
        String[] cInfo = {c.getName(),
                String.valueOf(c.getSize()),
                String.valueOf(c.getParent())};
        String[] expected = {"c", "35",String.valueOf(code)};
        assertArrayEquals(expected,cInfo);
    }
    @Test
    public void dFileTest(){
        String[] dInfo = {d.getName(),
                String.valueOf(d.getSize()),
                String.valueOf(d.getParent())};
        String[] expected = {"d", "40",String.valueOf(code)};
        assertArrayEquals(expected,dInfo);
    }
}
