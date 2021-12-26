import apfs.APFS;
import apfs.APFSDirectory;
import apfs.APFSFile;
import apfs.APFSLink;
import apfs.util.APFSFileSearchVisitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class APFSSearchTest {
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

    @Test
    public void getFoundFilesTest(){
        APFSFileSearchVisitor visitor = new APFSFileSearchVisitor("a");
        applications.accept(visitor);
        assertFalse(visitor.getFoundFiles().isEmpty());
    }

    @Test
    public void noFileTest(){
        APFSFileSearchVisitor visitor = new APFSFileSearchVisitor("b");
        applications.accept(visitor);
        assertTrue(visitor.getFoundFiles().isEmpty());
    }
}
