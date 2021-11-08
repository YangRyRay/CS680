import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

public class FileTest {
    private static FileSystem sys;
    private static Directory root = new Directory(null,"root",0, LocalDateTime.now());
    private static Directory applications = new Directory(root, "applications", 0, LocalDateTime.now());
    private static Directory home=new Directory(root,"home",0,LocalDateTime.now());
    private static Directory code=new Directory(home, "code",0,LocalDateTime.now());
    private static File a = new File(applications, "a", 25, LocalDateTime.now());
    private static File b = new File(home, "b",30,LocalDateTime.now());
    private static File c = new File(home, "c",35,LocalDateTime.now());
    private static File d = new File(home, "d",40,LocalDateTime.now());

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
        code.appendChild(c);
        code.appendChild(d);
    }

    private String[] fileToStringArray(File file){
        String[] fileInfo = {String.valueOf(file.getParent()),
                file.getName(),
                String.valueOf(file.getSize())};
        return fileInfo;
    }
    //Method Tests
    @Test
    public void FileCreationTest(){
        File target = new File(applications, "target", 30, LocalDateTime.now());
        String[] expected = {String.valueOf(applications), "target", "30"};
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
