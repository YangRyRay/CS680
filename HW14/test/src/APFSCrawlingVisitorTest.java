import apfs.APFS;
import apfs.APFSDirectory;
import apfs.APFSFile;
import apfs.APFSLink;
import apfs.util.APFSFileCrawlingVisitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class APFSCrawlingVisitorTest {
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
    //Method Tests
    @Test
    public void CrawlingVisitorGetFilesTest(){
        APFSFileCrawlingVisitor visitor = new APFSFileCrawlingVisitor();
        applications.accept(visitor);
        LinkedList<APFSFile> expected = new LinkedList<APFSFile>();
        expected.add(a);
        assertEquals(expected,visitor.getFiles());

    }

    @Test
    public void CountingVisitorRootTest(){
        APFSFileCrawlingVisitor visitor = new APFSFileCrawlingVisitor();
        root.accept(visitor);
        LinkedList<APFSFile> expected = new LinkedList<APFSFile>();
        expected.add(a);
        expected.add(b);
        expected.add(c);
        expected.add(d);
        assertTrue(expected.containsAll(visitor.getFiles())&&visitor.getFiles().containsAll(expected));
    }
    @Test
    public void CountingVisitorApplicationsTest(){
        APFSFileCrawlingVisitor visitor = new APFSFileCrawlingVisitor();
        applications.accept(visitor);
        LinkedList<APFSFile> expected = new LinkedList<APFSFile>();
        expected.add(a);
        assertTrue(expected.containsAll(visitor.getFiles())&&visitor.getFiles().containsAll(expected));
    }
    @Test
    public void CountingVisitorHomeTest(){
        APFSFileCrawlingVisitor visitor = new APFSFileCrawlingVisitor();
        home.accept(visitor);
        LinkedList<APFSFile> expected = new LinkedList<APFSFile>();
        expected.add(b);
        expected.add(c);
        expected.add(d);
        assertTrue(expected.containsAll(visitor.getFiles())&&visitor.getFiles().containsAll(expected));
    }
    @Test
    public void CountingVisitorCodeTest(){
        APFSFileCrawlingVisitor visitor = new APFSFileCrawlingVisitor();
        code.accept(visitor);
        LinkedList<APFSFile> expected = new LinkedList<APFSFile>();
        expected.add(c);
        expected.add(d);
        assertTrue(expected.containsAll(visitor.getFiles())&&visitor.getFiles().containsAll(expected));
    }

}

