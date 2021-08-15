package youtube2;

import java.util.ArrayList;
import java.util.List;

public class User {
    
    private String name;
    private List<User> subscribers = new ArrayList<User>();
    private List<Video> videos = new ArrayList<Video>();

    public User(String name) {
        this.name = name;
    }

    public void subscribeTo(User user) {
        user.addSubscriber(this);
    }

    private void addSubscriber(User user) {
        subscribers.add(user);
    }

    public void postVideo(String name, int length) {
        Video video = new Video(name, length, this);
        videos.add(video);

        for (User subscriber : subscribers) {
            subscriber.alertNewVideo(video);
        }
    }

    public void alertNewVideo(Video video) {
        System.out.println(name + ": A new video " + video.getName() + " was just posted from " 
                            + video.getProducer() + "!");
    }

    @Override
    public String toString() {
        return name;
    }

}