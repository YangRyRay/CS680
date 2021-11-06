import java.util.logging.Logger;

public class DrawerClosedPlaying implements State{
    private DrawerClosedPlaying(){}
    private static DrawerClosedPlaying instance = null;

    public static DrawerClosedPlaying getInstance(){
        if (instance==null)
            instance = new DrawerClosedPlaying();
        return instance;
    }

    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public void openCloseButtonPushed() {
        DVDPlayer.stop();
        DVDPlayer.open();
        DVDPlayer.changeState(DrawerOpen.getInstance());
    }

    @Override
    public void playButtonPushed() {
        LOGGER.info("Already Playing");
    }

    @Override
    public void stopButtonPushed() {
        DVDPlayer.stop();
        DVDPlayer.changeState(DrawerClosedNotPlaying.getInstance());
    }
}
