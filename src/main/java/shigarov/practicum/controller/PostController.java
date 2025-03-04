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
import shigarov.practicum.service.TagService;

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
    private final TagService tagService;

    public PostController(
            PostService postService,
            TagService tagService
    ) {
        this.postService = postService;
        this.tagService = tagService;
    }

    @GetMapping
    public String getPosts(
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

        List<Tag> allTags = tagService.findAllTags();

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
    public String getPost(
            @PathVariable("id") long id,
            @RequestParam(value = "editingCommentId", required = false, defaultValue = "0") long editingCommentId,
            Model model
    ) {
        // Получаем пост по ID из репозитория
        Optional<Post> postOptional = postService.findById(id);

        List<Tag> allTags = tagService.findAllTags();

        // Если пост найден, добавляем его в модель
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            model.addAttribute("post", post);
            model.addAttribute("uploadDir", uploadDir);
            model.addAttribute("allTags", allTags);
            if (editingCommentId > 0) {
                // ID комментария, который будет редактироваться
                model.addAttribute("editingCommentId", editingCommentId);
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
                Tag tag = tagService.findTagById(tagId).orElse(null);
                if (tag != null) {
                    post.addTag(tag);
                }
            }
        }

        // Сохраняем пост
        postService.addPost(post);

        return "redirect:/posts"; // Перенаправляем на страницу со списком постов
    }

    //@PostMapping("/update")
    @PostMapping("/update/{postId}")
    public String updatePost(
            //@ModelAttribute Post post, // Автоматическое связывание данных формы с объектом Post
            @PathVariable(name = "postId") Long postId,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "text", required = false) String text,
            @RequestParam(name = "tagIds", required = false) List<Long> tagIds,
            @RequestParam(name = "imageFile", required = false) MultipartFile imageFile
    ) {
        // Находим пост по ID
        Post savedPost = postService.findById(postId).orElseThrow(() -> new RuntimeException("Пост не найден"));

        // Обновляем заголовок
        if (title != null)
            savedPost.setTitle(title);

        // Обновляем текст
        if (text != null)
            savedPost.setText(text);

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
                Tag tag = tagService.findTagById(tagId).orElse(null);
                if (tag != null) {
                    savedPost.addTag(tag);
                }
            }
        }

        // Сохраняем обновленный пост
        postService.updatePost(savedPost);

        return "redirect:/posts/" + savedPost.getId();
    }

    @PostMapping("/incrementPostLikes")
    public String incrementPostLikes(@RequestParam(name = "postId") Long postId) {
        postService.incrementLikes(postId);
        return "redirect:/posts/" + postId;
    }

    @PostMapping(value = "/delete/{postId}", params = "_method=delete")
    public String delete(@PathVariable(name = "postId") Long postId) {
        postService.deletePost(postId);
        return "redirect:/posts";
    }
}
