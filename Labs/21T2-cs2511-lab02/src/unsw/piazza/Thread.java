package unsw.piazza;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A thread in the Piazza forum.
 */
public class Thread {

    private String title;
    private Post firstPost;
    private User owner;
    private List<String> tags;
    private List<Post> posts;

    /**
     * Creates a new thread with a title and an initial first post.
     * The author of the first post at the time of thread creation is the owner of the thread.
     * The owner cannot change once the thread is created.
     * @param title
     * @param firstPost
     */
    public Thread(String title, Post firstPost) {
        this.title = title;
        this.firstPost = firstPost;
        this.owner = firstPost.getAuthor();
        tags = new ArrayList<>();
        posts = new ArrayList<>();
        posts.add(firstPost);
    }

    /**
     * @return The owner of the thread
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @return The title of the thread
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return A SORTED list of unique tags
     */
    public List<String> getTags() {
        Collections.sort(tags);
        return tags;
    }

    /**
     * @return A list of posts in this thread, in the order that they were published
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * Adds the given post object into the list of posts in the thread.
     * @param post
     */
    public void publishPost(Post post) {
        posts.add(post);
    }

    /**
     * Allows the given user to remove the Post from the thread.
     * Does nothing if the post is not in the thread.
     *
     * @param post
     * @throws PermissionDeniedException if the given user is not an author of the post
     */
    public void removePost(Post post, User by) throws PermissionDeniedException {
        if (!by.equals(owner)) {
            throw new PermissionDeniedException("given user is not the author");
        }
        for (Post postObject : posts) {
            if (post.equals(postObject)) {
                posts.remove(post);
                break;
            }
        }
    }

    /**
     * Allows the given uer to edit the thread title.
     * @param title
     * @param by
     * @throws PermissionDeniedException if the given user is not the owner of the thread.
     */
    public void setTitle(String title, User by) throws PermissionDeniedException {
        if (!by.equals(owner)) {
            throw new PermissionDeniedException("given user is not the author");
        }
        this.title = title;
    }

    /**
     * Allows the given user to replace the thread tags (list of strings)
     * @param tags
     * @param by
     * @throws PermissionDeniedException if the given user is not the owner of the thread.
     */
    public void setTags(String[] tags, User by) throws PermissionDeniedException {
        if (!by.equals(owner)) {
            throw new PermissionDeniedException("given user is not the author");
        }
        this.tags = Arrays.asList(tags);
    }
}
