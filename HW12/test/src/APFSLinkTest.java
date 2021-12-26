import apfs.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class APFSLinkTest {
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
    private String[] LinkToStringArray(APFSLink link){
        String[] linkInfo = {String.valueOf(link.getParent()),
                link.getName(),
                String.valueOf(link.getTarget())};
        return linkInfo;
    }
    //Method Tests
    @Test
    public void LinkCreationTest(){
        APFSLink target = new APFSLink(applications, "testlink", 0, LocalDateTime.now(), "User", LocalDateTime.now(),b);
        String[] expected = {String.valueOf(applications), "testlink", String.valueOf(b)};
        assertArrayEquals(expected,LinkToStringArray(target));
    }
    @Test
    public void GetTotalSizeTest(){
        int size = x.getTotalSize();
        int expected = x.getSize();
        assertEquals(expected,size);
    }
    @Test
    public void IsDirectoryTest(){
        boolean isItDir = x.isDirectory();
        assertFalse(isItDir);
    }
    //InstanceTests
    @Test
    public void xLinkTest(){
        String[] xInfo = LinkToStringArray(x);
        String[] expected = {String.valueOf(home), "x",String.valueOf(applications)};
        assertArrayEquals(expected,xInfo);
    }
    @Test
    public void yLinkTest(){
        String[] yInfo = LinkToStringArray(y);
        String[] expected = {String.valueOf(code), "y",String.valueOf(a)};
        assertArrayEquals(expected,yInfo);
    }

}
