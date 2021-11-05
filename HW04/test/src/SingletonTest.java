import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SingletonTest {
    @Test
    public void getInstanceTest(){
        Singleton target = Singleton.getInstance();
        assertNotNull(target);
    }
    @Test
    public void SingleInstanceTest(){
        Singleton firstCall = Singleton.getInstance();
        Singleton secondCall = Singleton.getInstance();
        assertEquals(firstCall.hashCode(),secondCall.hashCode());
    }
}
