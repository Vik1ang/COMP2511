package youtube;

public class User {
    
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void subscribeTo(Producer user) {
        user.addSubscriber(this);
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