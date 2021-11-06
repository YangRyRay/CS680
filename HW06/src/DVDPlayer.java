import java.util.logging.Logger;

public class DVDPlayer {
    static State state;

    static void changeState(State state){
        DVDPlayer.state=state;
    }

    public State getState(){
        return state;
    }

    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    static void open(){
        LOGGER.info("Drawer Opened");
    }
    static void close(){
        LOGGER.info("Drawer Closed");
    }
    static void play(){
        LOGGER.info("DVD Playing");
    }
    static void stop(){
        LOGGER.info("DVD Stopped");
    }
    public void openCloseButtonPushed(){
        state.openCloseButtonPushed();
    }
    public void playButtonPushed(){
        state.playButtonPushed();
    }
    public void stopButtonPushed(){
        state.stopButtonPushed();
    }

    private DVDPlayer(State state){
        DVDPlayer.state =state;
    }

    private static DVDPlayer instance = null;

    public static DVDPlayer getInstance(){
        if(instance==null)
            instance = new DVDPlayer(DrawerClosedNotPlaying.getInstance());
        return instance;
    }

}
