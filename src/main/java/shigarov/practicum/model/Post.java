package shigarov.practicum.model;

import java.util.List;

public class Post {

    private Long id;
    private String title;
    private String image;
    private String text;
    private String tags;
    private int likes;
    private List<Comment> comments;

    // Конструктор без аргументов
    public Post() {}

    // Конструктор с аргументами для удобства использования
    public Post(Long id, String title, String image, String text, String tags, int likes, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.text = text;
        this.tags = tags;
        this.likes = likes;
        this.comments = comments;
    }

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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

//    public int numOfComments() {
//        return comments == null ? 0 : comments.size();
//    }
}