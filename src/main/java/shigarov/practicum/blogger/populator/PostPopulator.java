package shigarov.practicum.blogger.populator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import shigarov.practicum.blogger.model.Comment;
import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.model.Tag;
import shigarov.practicum.blogger.service.CommentService;
import shigarov.practicum.blogger.service.PostService;
import shigarov.practicum.blogger.service.TagService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
public class PostPopulator {

    @Value("${upload.dir}")
    private String location;

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Autowired
    TagService tagService;

    @Autowired
    ImageGenerator imageGenerator;

    private int tagsCount;

    // Список возможных текстов для комментариев
    private static final String[] COMMENT_TEXTS = {
            "отличный пост",
            "замечательный пост",
            "ужасный пост",
            "отвратительный пост",
            "самый лучший пост в мире",
            "хуже ничего в жизни не читал"
    };

    private static final Random random = new Random();

    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;

    public void populate(final int postsCount) {
        tagsCount = tagService.findAll().size();

        for (int i = 1; i <= postsCount; i ++) {
            // Генерируем пост
            final String title = "Заголовок " + i;
            final String imageFileName = "image.png";
            final String text = generatePostText();
            int likes = random.nextInt(1001);

            final Post post = new Post(null, title, imageFileName, text, likes);

            // Сохраняем пост
            final Post savedPost = postService.save(post);

            if (savedPost == null) {
                throw new RuntimeException("Не удалось сохранить сгенерированный пост");
            }

            // Генерируем комментарии
            int commentCount = random.nextInt(6); // От 0 до 5 комментариев

            for (int j = 0; j < commentCount; j ++) {
                final String commentText = COMMENT_TEXTS[random.nextInt(COMMENT_TEXTS.length)];
                final Comment comment = new Comment(null, commentText, savedPost);

                // Сохраняем комментарий
                final Comment savedComment = commentService.save(comment);
                if (savedComment == null) {
                    throw new RuntimeException("Не удалось сохранить сгенерированный комментарий");
                }
            }

            // Генерируем связи с тегами
            final int tagCount = random.nextInt(6); // От 0 до 5 тегов

            if (tagCount > 0) {
                final Set<Integer> usedTags = new HashSet<>(); // Для уникальных тегов

                for (int j = 0; j < tagCount; j ++) {
                    final int randomTagId = random.nextInt(tagsCount) + 1; // ID тега от 1 до 6

                    if (usedTags.add(randomTagId)) { // Проверка на уникальность
                        Optional<Tag> tagOptional = tagService.findById(randomTagId);

                        if (tagOptional.isPresent()) {
                            Tag tag = tagOptional.get();
                            savedPost.addTag(tag);
                        }
                    }
                }
            }

            // Обновляем пост для сохранения добавленных тегов
            final Post updatedPost = postService.save(savedPost);

            if (updatedPost != null) {
                // Генерируем картинку
                final BufferedImage image = imageGenerator.generateImage(WIDTH, HEIGHT);
                writeImage(updatedPost.getId(), image);
            } else {
                throw new RuntimeException("Не удалось обновить сгенерированный пост");
            }
        }
    }

    // Генерация текста поста
    private String generatePostText() {
        int paragraphCount = random.nextInt(3) + 1; // От 1 до 3 абзацев
        StringBuilder text = new StringBuilder();

        for (int p = 1; p <= paragraphCount; p++) {
            text.append("<p>");
            text.append("Абзац ").append(p).append(": ");
            int lineCount = random.nextInt(5) + 1; // От 1 до 5 строк

            for (int l = 1; l <= lineCount; l++) {
                text.append("Строка ").append(l);
                if (l < lineCount) {
                    text.append("<br>");
                }
            }

            text.append("</p>");
        }

        return text.toString();
    }

    private void writeImage(long postId, BufferedImage image) {
        // Сохранение изображения в файл
        final Path dirPath = Paths.get(location, Long.toString(postId));
        try {
            Files.createDirectories(dirPath);
            Path filePath = dirPath.resolve("image.png");
            File outFile = filePath.toFile();
            ImageIO.write(image, "PNG", outFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}