package youtube2;

public class YoutubeTest {
    public static void main(String[] args) {
        User u1 = new User("Nick");
        User u2 = new User("Braedon");
        User u3 = new User("Ian");

        u2.subscribeTo(u1);
        u3.subscribeTo(u1);

        u1.postVideo("The Observer Pattern", 20);

    }
}