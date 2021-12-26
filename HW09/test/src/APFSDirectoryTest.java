import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class APFSDirectoryTest {
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

    private String[] dirToStringArray(APFSDirectory dir){
        String[] dirInfo = {String.valueOf(dir.getParent()),
                dir.getName(),
                String.valueOf(dir.getSize())};
        return dirInfo;
    }
    private String[] dirChildArray(APFSDirectory dir){
        ArrayList<String> childrenList = new ArrayList<>();
        LinkedList<APFSElement> children = dir.getChildren();
        for (int i=0;i<children.size();i++){
            childrenList.add(children.get(i).getName());
        }
        String[] childrenArr = {};
        childrenArr = childrenList.toArray(childrenArr);
        return childrenArr;
    }
    private String[] fLinkedListToArray(LinkedList<APFSFile> list){
        ArrayList<String> aList = new ArrayList<>();

        for (int i=0;i<list.size();i++){
            aList.add(list.get(i).getName());
        }
        String[] aArr = {};
        aArr = aList.toArray(aArr);
        return aArr;
    }
    private String[] dLinkedListToArray(LinkedList<APFSDirectory> list){
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
        APFSDirectory target = new APFSDirectory(applications, "target", 30, LocalDateTime.now(),"User",LocalDateTime.now());
        String[] expected = {String.valueOf(applications), "target", "30"};
        assertArrayEquals(expected,dirToStringArray(target));
    }
    @Test
    public void AppendChildTest(){
        APFSFile testfile = new APFSFile(applications, "testfile", 123, LocalDateTime.now(),"User",LocalDateTime.now());
        applications.appendChild(testfile);
        String[] expected = {"a", "testfile"};
        assertArrayEquals(expected, dirChildArray(applications));
    }
    @Test
    public void ChildrenCountTest(){
        int actual = code.countChildren();
        assertEquals(3,actual);
    }
    @Test
    public void GetSubDirectoryTest(){
        LinkedList<APFSDirectory> subdir = home.getSubDirectories();
        String[] subdirArr = dLinkedListToArray(subdir);
        String[] expected = {"code"};
        assertArrayEquals(expected,subdirArr);
    }
    @Test
    public void GetFilesTest(){
        LinkedList<APFSFile> files= code.getFiles();
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
