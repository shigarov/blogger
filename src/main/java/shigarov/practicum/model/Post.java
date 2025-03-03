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

    public String getTextPreview() {
        return "";
    }

    public List<Tag> getTags() {
        return new ArrayList<>(tags.values());
    }

    public List<Long> getTagIds() {
        return new ArrayList<>(tags.keySet());
    }

    public void addTag(Tag tag) {
        tags.put(tag.getId(), tag);
    }

    public void removeAllTags() {
        tags.clear();
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

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("image", image)
                .append("text", text)
                .append("tags", tags)
                .append("likes", likes)
                .append("comments", comments)
                .toString();
    }
}