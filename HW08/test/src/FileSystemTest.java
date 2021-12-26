import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileSystemTest {
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

    private String[] rootLinkedListToArray(LinkedList<Directory> root){
        ArrayList<String> aList = new ArrayList<>();
        for (int i=0;i<root.size();i++){
            aList.add(root.get(i).getName());
        }
        String[] aArr = {};
        aArr = aList.toArray(aArr);
        return aArr;
    }
    //Method Tests
    @Test
    public void getRootDirsTest(){
        LinkedList<Directory> rootList = sys.getRootDirs();
        String[] roots = rootLinkedListToArray(rootList);
        String[] expected = {"root"};
        assertArrayEquals(expected,roots);
    }
    @Test
    public void appendRootDirsTest(){
        Directory root2 = new Directory(null, "root2", 0, LocalDateTime.now());
        sys.appendRootDir(root2);
        LinkedList<Directory> iter = sys.getRootDirs();
        Directory actual = iter.getLast();
        assertEquals(root2,actual);
    }
    @Test
    public void ClearFSTest(){
        sys.clearFS();
        assertEquals(new LinkedList<>(),sys.getRootDirs());
    }
}
