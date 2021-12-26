import apfs.*;
import apfs.util.AlphabeticalComparator;
import apfs.util.ReverseAlphabeticalComparator;
import apfs.util.TimeStampComparator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ComparatorTests {
    private static APFS sys = APFS.getInstance();
    private static APFSDirectory root = new APFSDirectory(null,"root",0, LocalDateTime.now(),"User",LocalDateTime.now());
    private static APFSDirectory applications = new APFSDirectory(root, "applications", 0, LocalDateTime.now(),"User",LocalDateTime.now().plusHours(2));
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
    @Test
    public void appendAlphabeticalTest(){
        APFSFile testfile = new APFSFile(home, "testfile", 123, LocalDateTime.now(),"User",LocalDateTime.now());
        home.appendChild(testfile);
        String[] expected = {"b","code", "testfile","x"};
        assertArrayEquals(expected, dirChildArray(home));
    }

    @Test
    public void codeGetChildrenAlphabeticalSortTest(){
        code.getChildren(new AlphabeticalComparator());
        String[] expected = {"c","d", "y"};
        assertArrayEquals(expected, dirChildArray(code));
    }
    @Test
    public void codeGetFilesRevAlphabeticalSortTest(){
        String[] expected = {"d","c"};
        assertArrayEquals(expected, fLinkedListToArray(code.getFiles(new ReverseAlphabeticalComparator())));
    }
    @Test
    public void rootGetSubdirTimeStampSortTest(){
        String[] expected = {"applications","home"};
        assertArrayEquals(expected, dLinkedListToArray(root.getSubDirectories(new TimeStampComparator())));

    }
}
