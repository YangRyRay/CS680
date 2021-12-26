import FSFoundation.*;
import apfs.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class APFSTest {
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
    private String[] rootLinkedListToArray(LinkedList<FSElement> root){
        ArrayList<String> aList = new ArrayList<>();
        for (int i=0;i<root.size();i++){
            aList.add(root.get(i).getName());
        }
        String[] aArr = {};
        aArr = aList.toArray(aArr);
        return aArr;
    }

    private String[] dirToStringArray(FSElement dir){
        String[] dirInfo = {String.valueOf(dir.getParent()),
                dir.getName(),
                String.valueOf(dir.getSize())};
        return dirInfo;
    }

    //Method Tests
    @Test
    public void getRootDirsTest(){
        LinkedList<FSElement> rootList = sys.getRootDirs();
        String[] roots = rootLinkedListToArray(rootList);
        String[] expected = {"root"};
        assertArrayEquals(expected,roots);
    }
    @Test
    public void CreateDefaultRootTest(){
        FSElement newroot= sys.createDefaultRoot("test");
        FSElement expected =new APFSDirectory(null,"test",0, LocalDateTime.now(),"User",LocalDateTime.now());
        assertArrayEquals(dirToStringArray(expected), dirToStringArray(newroot));
    }
    @Test
    public void ClearFSTest(){
        sys.clearFS();
        assertEquals(new LinkedList<>(),sys.getRootDirs());
    }

}
