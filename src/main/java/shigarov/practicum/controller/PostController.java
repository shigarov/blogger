package shigarov.practicum.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shigarov.practicum.model.Post;
import shigarov.practicum.model.Tag;
import shigarov.practicum.service.PostService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@RequestMapping("/posts") // Контроллер обрабатывает запросы /posts
public class PostController {
    @Value("${upload.path}") // Относительный путь
    private String uploadPath;

    @Value("${upload.dir}") // Относительный путь
    private String uploadDir;

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping // GET запрос /posts
    public String posts(
            @RequestParam(name = "tagId", required = false, defaultValue = "0") Long tagId,
            @RequestParam(name = "page", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", required = false, defaultValue = "10") int pageSize,
            Model model
    ) {

        final Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<Post> posts;

        if (tagId != null && tagId > 0) {
            Page<Post> postsPage = postService.findAllByTag(pageable, tagId);
            posts = postsPage.stream().toList();
            model.addAttribute("page", postsPage);
        } else {
            Page<Post> postsPage = postService.findAll(pageable);
            posts = postsPage.stream().toList();
            model.addAttribute("page", postsPage);
        }

        List<Tag> allTags = postService.findAllTags();

        // Передаём данные в виде атрибута users
        model.addAttribute("posts", posts);
        model.addAttribute("uploadDir", uploadDir);
        model.addAttribute("tagId", tagId);
        model.addAttribute("allTags", allTags);

        // Создаем пустой пост для формы
        model.addAttribute("newPost", new Post());

        return "posts"; // Возвращаем название шаблона — posts.html
    }

    @GetMapping("/{id}")
    public String postById(
            @PathVariable("id") long id,
            @RequestParam(value = "editingCommentId", required = false, defaultValue = "0") long editingCommentId,
            Model model
    ) {
        // Получаем пост по ID из репозитория
        Optional<Post> postOptional = postService.findById(id);

        List<Tag> allTags = postService.findAllTags();

        // Если пост найден, добавляем его в модель
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            model.addAttribute("post", post);
            model.addAttribute("uploadDir", uploadDir);
            model.addAttribute("allTags", allTags);
            if (editingCommentId > 0) {
                model.addAttribute("editingCommentId", editingCommentId); // ID комментария, который редактируется
            }
        }
        return "post";
    }

    @PostMapping("/add")
    public String addPost(
            @ModelAttribute Post post,
            @RequestParam(name = "tagIds", required = false) List<Long> tagIds,
            @RequestParam(name = "imageFile", required = false) MultipartFile file
    ) {
        // Обработка загрузки файла
        if (file != null && !file.isEmpty()) {
            try {
                // Сохраняем файл
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(uploadPath, uploadDir, fileName);
                Files.write(path, file.getBytes());

                // Устанавливаем имя файла в объект Post
                post.setImage(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (tagIds != null) {
            // Получаем выбранные теги по их ID и добавляем их в пост
            for (Long tagId : tagIds) {
                Tag tag = postService.findTagById(tagId).orElse(null);
                if (tag != null) {
                    post.addTag(tag);
                }
            }
        }

        // Сохраняем пост
        postService.addPost(post);

        return "redirect:/posts"; // Перенаправляем на страницу со списком постов
    }

    @PostMapping("/update")
    public String updatePost(
            @ModelAttribute Post post, // Автоматическое связывание данных формы с объектом Post
            @RequestParam(name = "tagIds", required = false) List<Long> tagIds,
            @RequestParam(name = "imageFile", required = false) MultipartFile imageFile
    ) {
        // Находим пост по ID
        Post savedPost = postService.findById(post.getId()).orElseThrow(() -> new RuntimeException("Пост не найден"));

        // Обновляем заголовок и текст
        savedPost.setTitle(post.getTitle());
        savedPost.setText(post.getText());

        // Обработка загрузки нового изображения
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                // Удаляем старое изображение, если оно есть
                if (savedPost.getImage() != null) {
                    Path oldImagePath = Paths.get(uploadPath, uploadDir, savedPost.getImage());
                    Files.deleteIfExists(oldImagePath);
                }

                // Сохраняем новое изображение
                String fileName = imageFile.getOriginalFilename();
                Path path = Paths.get(uploadPath, uploadDir, fileName);
                Files.write(path, imageFile.getBytes());

                // Обновляем имя файла в объекте Post
                savedPost.setImage(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Обновляем теги
        if (tagIds != null) {
            savedPost.removeAllTags();
            for (Long tagId : tagIds) {
                Tag tag = postService.findTagById(tagId).orElse(null);
                if (tag != null) {
                    savedPost.addTag(tag);
                }
            }
        }

        // Сохраняем обновленный пост
        postService.updatePost(savedPost);

        return "redirect:/posts/" + savedPost.getId();
    }

    // Добавление нового комментария
    @PostMapping("/addComment")
    public String addComment(
            @RequestParam(name = "commentText") String text,
            @RequestParam(name = "postId") Long postId
    ) {
        postService.addComment(text, postId);
        return "redirect:/posts/" + postId;
    }

    // Начало редактирования комментария
    @GetMapping("/editComment")
    public String editComment(
            @RequestParam(name = "editingCommentId") Long editingCommentId,
            @RequestParam(name = "postId") Long postId
    ) {
        return "redirect:/posts/" + postId + "?editingCommentId=" + editingCommentId;
    }

    @PostMapping("/updateComment")
    public String updateComment(
            @RequestParam(name = "commentText") String text,
            @RequestParam(name = "commentId") Long id,
            @RequestParam(name = "postId") Long postId
    ) {
        postService.updateComment(id, text);
        return "redirect:/posts/" + postId;
    }

    @PostMapping("/incrementPostLikes")
    public String incrementPostLikes(@RequestParam(name = "postId") Long postId) {
        postService.incrementLikes(postId);
        return "redirect:/posts/" + postId;
    }

    @PostMapping(value = "/delete/{id}", params = "_method=delete")
    public String delete(@PathVariable(name = "id") Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
