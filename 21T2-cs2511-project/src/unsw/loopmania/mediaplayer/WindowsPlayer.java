package unsw.loopmania.mediaplayer;

public class WindowsPlayer implements computerPlayers {
    public String mediaPlayer(String filename) {
        return filename.substring(filename.lastIndexOf('\\') + 1);
    }
}
