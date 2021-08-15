package youtube2;

public class Video {
    private int length; // Length of the video in seconds
    private User producer;
    private String name;

    public Video(String name, int length, User producer) {
        this.name = name;
        this.length = length;
        this.producer = producer;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public User getProducer() {
        return producer;
    }

    public void setProducer(User producer) {
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}