import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DVDPlayerTest {
    @Test
    public void DVDPlayerGetInstanceTest(){
        DVDPlayer player=DVDPlayer.getInstance();
        assertNotNull(player);
    }
    @Test
    public void DVDPlayerSingleInstanceTest(){
        DVDPlayer p1=DVDPlayer.getInstance();
        DVDPlayer p2=DVDPlayer.getInstance();
        assertSame(p1,p2);
    }
    @Test
    public void DVDPlayerGetStateTest(){
        DVDPlayer player=DVDPlayer.getInstance();
        player.changeState(DrawerOpen.getInstance());
        assertSame(DrawerOpen.getInstance(),player.getState());
    }

    @Test
    public void DrawerClosedNotPlayingGetInstanceTest(){
        DrawerClosedNotPlaying status=DrawerClosedNotPlaying.getInstance();
        assertNotNull(status);
    }
    @Test
    public void DrawerClosedNotPlayingOpenCloseTest(){
        DVDPlayer player = DVDPlayer.getInstance();
        player.changeState(DrawerClosedNotPlaying.getInstance());
        player.openCloseButtonPushed();
        assertEquals(DrawerOpen.getInstance(),player.getState());
    }
    @Test
    public void DrawerClosedNotPlayingPlayTest(){
        DVDPlayer player = DVDPlayer.getInstance();
        player.changeState(DrawerClosedNotPlaying.getInstance());
        player.playButtonPushed();
        assertEquals(DrawerClosedPlaying.getInstance(),player.getState());
    }
    @Test
    public void DrawerClosedNotPlayingStopTest(){
        DVDPlayer player = DVDPlayer.getInstance();
        player.changeState(DrawerClosedNotPlaying.getInstance());
        player.stopButtonPushed();
        assertEquals(DrawerClosedNotPlaying.getInstance(),player.getState());
    }

    @Test
    public void DrawerClosedPlayingGetInstanceTest(){
        DrawerClosedPlaying status=DrawerClosedPlaying.getInstance();
        assertNotNull(status);
    }
    @Test
    public void DrawerClosedPlayingOpenCloseTest(){
        DVDPlayer player = DVDPlayer.getInstance();
        player.changeState(DrawerClosedPlaying.getInstance());
        player.openCloseButtonPushed();
        assertEquals(DrawerOpen.getInstance(),player.getState());
    }
    @Test
    public void DrawerClosedPlayingPlayTest(){
        DVDPlayer player = DVDPlayer.getInstance();
        player.changeState(DrawerClosedPlaying.getInstance());
        player.playButtonPushed();
        assertEquals(DrawerClosedPlaying.getInstance(),player.getState());
    }
    @Test
    public void DrawerClosedPlayingStopTest(){
        DVDPlayer player = DVDPlayer.getInstance();
        player.changeState(DrawerClosedPlaying.getInstance());
        player.stopButtonPushed();
        assertEquals(DrawerClosedNotPlaying.getInstance(),player.getState());
    }

    @Test
    public void DrawerOpenGetInstanceTest(){
        DrawerOpen status=DrawerOpen.getInstance();
        assertNotNull(status);
    }
    @Test
    public void DrawerOpenOpenCloseTest(){
        DVDPlayer player = DVDPlayer.getInstance();
        player.changeState(DrawerOpen.getInstance());
        player.openCloseButtonPushed();
        assertEquals(DrawerClosedNotPlaying.getInstance(),player.getState());
    }
    @Test
    public void DrawerOpenPlayTest(){
        DVDPlayer player = DVDPlayer.getInstance();
        player.changeState(DrawerOpen.getInstance());
        player.playButtonPushed();
        assertEquals(DrawerClosedPlaying.getInstance(),player.getState());
    }
    @Test
    public void DrawerOpenStopTest(){
        DVDPlayer player = DVDPlayer.getInstance();
        player.changeState(DrawerOpen.getInstance());
        player.stopButtonPushed();
        assertEquals(DrawerOpen.getInstance(),player.getState());
    }

}
