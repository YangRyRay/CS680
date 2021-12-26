import apfs.APFS;
import apfs.APFSDirectory;
import apfs.APFSFile;
import apfs.APFSLink;
import apfs.util.APFSCountingVisitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class APFSCountingVisitorTest {
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
    private int[] toElementCount(APFSCountingVisitor v){
        int[] counts = {
                v.getDirNum(),
                v.getFileNum(),
                v.getLinkNum()};
        return counts;


    }
    //Method Tests
    @Test
    public void CountingVisitorGetDirTest(){
        APFSCountingVisitor visitor = new APFSCountingVisitor();
        root.accept(visitor);
        assertEquals(4,visitor.getDirNum());

    }
    @Test
    public void CountingVisitorGetFileTest(){
        APFSCountingVisitor visitor = new APFSCountingVisitor();
        root.accept(visitor);
        assertEquals(4,visitor.getFileNum());
    }
    @Test
    public void CountingVisitorGetLinkTest(){
        APFSCountingVisitor visitor = new APFSCountingVisitor();
        root.accept(visitor);
        assertEquals(2,visitor.getLinkNum());
    }
    @Test
    public void CountingVisitorRootTest(){
        APFSCountingVisitor visitor = new APFSCountingVisitor();
        root.accept(visitor);
        int[] expected = {4,4,2};
        assertArrayEquals(expected,toElementCount(visitor));
    }
    @Test
    public void CountingVisitorHomeTest(){
        APFSCountingVisitor visitor = new APFSCountingVisitor();
        home.accept(visitor);
        int[] expected = {2,3,2};
        assertArrayEquals(expected,toElementCount(visitor));
    }
    @Test
    public void CountingVisitorCodeTest(){
        APFSCountingVisitor visitor = new APFSCountingVisitor();
        code.accept(visitor);
        int[] expected = {1,2,1};
        assertArrayEquals(expected,toElementCount(visitor));
    }
    @Test
    public void CountingVisitorApplicationsTest(){
        APFSCountingVisitor visitor = new APFSCountingVisitor();
        applications.accept(visitor);
        int[] expected = {1,1,0};
        assertArrayEquals(expected,toElementCount(visitor));
    }
}
