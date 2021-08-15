package unsw.piazza;

import java.util.ArrayList;
import java.util.List;

/**
 * A Piazza Forum 
 * 
 * @author Nick Patrikeos
 */
public class PiazzaForum {
    private String className;
    private List<Thread> threads;
    
    /**
     * Initialises the new PiazzaForum with the given group name
     */
    public PiazzaForum(String className) {
        this.className = className;
        this.threads = new ArrayList<>();
    }

    /**
     * @return The name of the forum
     */
    public String getName() {
        return className;
    }

    /**
     * Sets the name of the group of the Forum
     * @param name
     */
    public void setName(String name) {
        this.className = name;
    }

    /**
     * Returns a list of Threads in the Forum, in the order that they were published
     */
    public List<Thread> getThreads() {
        return threads;
    }

    /**
     * Creates a new thread with the given title and adds it to the Forum.
     * The content and author are provided to allow you to create the first Post object.
     * Threads are stored in the order that they are published.
     * Returns the new Thread object
     * @param title
     * @param content
     * @param author
     */
    public Thread publish(String title, String content, User author) {
        Thread thread = new Thread(title, new Post(content, author));
        threads.add(thread);
        return thread;
    }

    /**
     * Searches all forum Threads for any that contain the given tag.
     * Returns a list of all matching Thread objects in the order that they were published.
     * @param tag
     * @return
     */
    public List<Thread> searchByTag(String tag) {
        ArrayList<Thread> threadArrayList = new ArrayList<>();
        for (Thread thread : this.threads) {
            List<String> tags = thread.getTags();
            for (String s : tags) {
                if (s.equals(tag)) {
                    threadArrayList.add(thread);
                    break;
                }
            }
        }
        return threadArrayList;
    }

    /**
     * Searches all forum Threads for Posts by the given author.
     * Returns a list of matching Post objects in the order that they were published.
     * @param author
     * @return
     */
    public List<Post> searchByAuthor(User author) {
        ArrayList<Post> postArrayList = new ArrayList<>();
        for (Thread thread : this.threads) {
            for (Post post : thread.getPosts()) {
                if (post.getAuthor().equals(author)) {
                    postArrayList.add(post);
                }
            }
        }
        return postArrayList;
    }

}