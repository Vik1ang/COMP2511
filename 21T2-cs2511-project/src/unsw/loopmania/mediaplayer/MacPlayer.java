package unsw.loopmania.mediaplayer;

public class MacPlayer implements computerPlayers {
    public String mediaPlayer(String filename) {
        return filename.substring(filename.lastIndexOf('/') + 1);
    }
}
