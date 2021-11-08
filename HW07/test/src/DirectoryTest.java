import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class DirectoryTest {
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

    private String[] dirToStringArray(Directory dir){
        String[] dirInfo = {String.valueOf(dir.getParent()),
                            dir.getName(),
                            String.valueOf(dir.getSize())};
        return dirInfo;
    }
    private String[] dirChildArray(Directory dir){
        ArrayList<String> childrenList = new ArrayList<>();
        LinkedList<FSElement> children = dir.getChildren();
        for (int i=0;i<children.size();i++){
            childrenList.add(children.get(i).getName());
        }
        String[] childrenArr = {};
        childrenArr = childrenList.toArray(childrenArr);
        return childrenArr;
    }
    private String[] fLinkedListToArray(LinkedList<File> list){
        ArrayList<String> aList = new ArrayList<>();

        for (int i=0;i<list.size();i++){
            aList.add(list.get(i).getName());
        }
        String[] aArr = {};
        aArr = aList.toArray(aArr);
        return aArr;
    }
    private String[] dLinkedListToArray(LinkedList<Directory> list){
        ArrayList<String> aList = new ArrayList<>();

        for (int i=0;i<list.size();i++){
            aList.add(list.get(i).getName());
        }
        String[] aArr = {};
        aArr = aList.toArray(aArr);
        return aArr;
    }
    //Method Tests
    @Test
    public void DirectoryCreationTest(){
        Directory target = new Directory(applications, "target", 30, LocalDateTime.now());
        String[] expected = {String.valueOf(applications), "target", "30"};
        assertArrayEquals(expected,dirToStringArray(target));
    }
    @Test
    public void AppendChildTest(){
        File testfile = new File(applications, "testfile", 123, LocalDateTime.now());
        applications.appendChild(testfile);
        String[] expected = {"a", "testfile"};
        assertArrayEquals(expected, dirChildArray(applications));
    }
    @Test
    public void ChildrenCountTest(){
        int actual = code.countChildren();
        assertEquals(2,actual);
    }
    @Test
    public void GetSubDirectoryTest(){
        LinkedList<Directory> subdir = home.getSubDirectories();
        String[] subdirArr = dLinkedListToArray(subdir);
        String[] expected = {"code"};
        assertArrayEquals(expected,subdirArr);
    }
    @Test
    public void GetFilesTest(){
        LinkedList<File> files= code.getFiles();
        String[] fileArr=fLinkedListToArray(files);
        String[] expected = {"c","d"};
        assertArrayEquals(expected,fileArr);
    }
    @Test
    public void GetTotalSizeTest(){
        int size = root.getTotalSize();
        assertEquals(130,size);
    }
    @Test
    public void IsDirectoryTest(){
        boolean isItDir = applications.isDirectory();
        assertTrue(isItDir);
    }

    //Instance tests
    @Test
    public void rootDirectoryTest(){
        String[] rootInfo ={root.getName(),
                            String.valueOf(root.countChildren()),
                            String.valueOf(root.getTotalSize())};
        String[] expected = {"root","2", "130"};
    }
    @Test
    public void applicationsDirectoryTest(){
        String[] appInfo ={applications.getName(),
                            String.valueOf(applications.countChildren()),
                            String.valueOf(applications.getTotalSize())};
        String[] expected = {"applications","1", "25"};
    }
    @Test
    public void homeDirectoryTest(){
        String[] homeInfo ={home.getName(),
                            String.valueOf(home.countChildren()),
                            String.valueOf(home.getTotalSize())};
        String[] expected = {"home","2", "105"};
    }
    @Test
    public void codeDirectoryTest(){
        String[] codeInfo ={code.getName(),
                            String.valueOf(code.countChildren()),
                            String.valueOf(code.getTotalSize())};
        String[] expected = {"code","2","75"};
    }
}

