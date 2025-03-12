package shigarov.practicum.blogger.model;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.*;

public class Post {

    private Long id;
    private String title;
    private String imageFileName;
    private String text;
    private Map<Long, Tag> tags = new LinkedHashMap<>();
    private int likes;
    private Map<Long, Comment> comments = new LinkedHashMap<>();

    // Конструктор без аргументов
    public Post() {}

    public Post(Long id, String title, String imageFileName, String text) {
        this.id = id;
        this.title = title;
        this.imageFileName = imageFileName;
        this.text = text;
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

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextPreview() {
        int endOfFirstParagraph =  text.indexOf("</p>");
        if (endOfFirstParagraph > 0) {
            String firstParagraph = text.substring(0,endOfFirstParagraph);

            // Разделяем первый абзац на строки
            String[] lines = firstParagraph.split("<br>");

            // Берем первые три строки
            StringBuilder preview = new StringBuilder();
            for (int i = 0; i < Math.min(3, lines.length); i++) {
                preview.append(lines[i]).append("<br>");
            }
            return preview.toString().trim();
        } else {
            return text;
        }
    }

    public List<Tag> getTags() {
        return new ArrayList<>(tags.values());
    }

    public List<Long> getTagIds() {
        return new ArrayList<>(tags.keySet());
    }

    public void addTag(@NonNull Tag tag) {
        tags.put(tag.getId(), tag);
    }

    public void removeAllTags() {
        tags.clear();
    }

    public void addAllTags(@NonNull Iterable<Tag> tags) {
        for (Tag tag : tags) {
            addTag(tag);
        }
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

}