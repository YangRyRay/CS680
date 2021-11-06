import java.util.logging.Level;
import java.util.logging.Logger;

public class DrawerClosedNotPlaying implements State{
    private DrawerClosedNotPlaying(){}
    private static DrawerClosedNotPlaying instance = null;

    public static DrawerClosedNotPlaying getInstance(){
        if (instance==null)
            instance = new DrawerClosedNotPlaying();
        return instance;
    }

    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void openCloseButtonPushed() {
        DVDPlayer.open();
        DVDPlayer.changeState(DrawerOpen.getInstance());
    }

    @Override
    public void playButtonPushed() {
        DVDPlayer.play();
        DVDPlayer.changeState(DrawerClosedPlaying.getInstance());
    }

    @Override
    public void stopButtonPushed() {
        LOGGER.info("Nothing Happened");
    }
}
