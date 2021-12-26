import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class LinkTest {
    private static FileSystem sys;
    private static Directory root = new Directory(null,"root",0, LocalDateTime.now());
    private static Directory applications = new Directory(root, "applications", 0, LocalDateTime.now());
    private static Directory home=new Directory(root,"home",0,LocalDateTime.now());
    private static Directory code=new Directory(home, "code",0,LocalDateTime.now());
    private static File a = new File(applications, "a", 25, LocalDateTime.now());
    private static File b = new File(home, "b",30,LocalDateTime.now());
    private static File c = new File(home, "c",35,LocalDateTime.now());
    private static File d = new File(home, "d",40,LocalDateTime.now());
    private static Link x = new Link(home, "x", 0, LocalDateTime.now(), applications);
    private static Link y = new Link(code, "y", 0, LocalDateTime.now(), a);

    @BeforeAll
    public static void FileSystemSetUp(){
        sys = FileSystem.getInstance();
        sys.clearFS();
        sys.appendRootDir(root);
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

    private String[] LinkToStringArray(Link link){
        String[] linkInfo = {String.valueOf(link.getParent()),
                link.getName(),
                String.valueOf(link.getTarget())};
        return linkInfo;
    }

    //Method Tests
    @Test
    public void LinkCreationTest(){
        Link target = new Link(applications, "testlink", 0, LocalDateTime.now(), b);
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

