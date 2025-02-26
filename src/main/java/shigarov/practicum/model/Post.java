package shigarov.practicum.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Post {

    private Long id;
    private String title;
    private String image;
    private String text;
    private Map<Long, Tag> tags = new LinkedHashMap<>();
    private int likes;
    private Map<Long, Comment> comments = new LinkedHashMap<>();

    // Конструктор без аргументов
    public Post() {}

    // Конструктор с аргументами для удобства использования
//    public Post(Long id, String title, String image, String text, List<Tag> tags, int likes, List<Comment> comments) {
//        this.id = id;
//        this.title = title;
//        this.image = image;
//        this.text = text;
//        this.tags = tags;
//        this.likes = likes;
//        this.comments = comments;
//    }

    // Геттеры и сеттеры ...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Tag> getTags() {
        return new ArrayList<>(tags.values());
    }

    public void addTag(Tag tag) {
        tags.put(tag.getId(), tag);
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments.values());
    }

    public void addComment(Comment comment) {
        comments.put(comment.getId(), comment);
    }

//    public int numOfComments() {
//        return comments == null ? 0 : comments.size();
//    }
}