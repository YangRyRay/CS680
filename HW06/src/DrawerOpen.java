import java.util.logging.Level;
import java.util.logging.Logger;

public class DrawerOpen implements State{
    private DrawerOpen(){}
    private static DrawerOpen instance = null;

    public static DrawerOpen getInstance(){
        if (instance==null)
            instance = new DrawerOpen();
        return instance;
    }

    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    @Override
    public void openCloseButtonPushed() {
        DVDPlayer.close();
        DVDPlayer.changeState(DrawerClosedNotPlaying.getInstance());
    }

    @Override
    public void playButtonPushed() {
        DVDPlayer.close();
        DVDPlayer.play();
        DVDPlayer.changeState(DrawerClosedPlaying.getInstance());
    }

    @Override
    public void stopButtonPushed() {
        LOGGER.info("Nothing Happened");
    }
}
