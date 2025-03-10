package shigarov.practicum.blogger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import shigarov.practicum.blogger.model.Post;
import shigarov.practicum.blogger.model.Tag;
import shigarov.practicum.blogger.service.PostService;
import shigarov.practicum.blogger.service.TagService;
import shigarov.practicum.blogger.storage.StorageService;

import java.util.*;

@Controller
public class PostController {

    @Value("${upload.dir}") // Относительный путь
    private String uploadDir;

    private final PostService postService;
    private final TagService tagService;
    private final StorageService storageService;

    @Autowired
    public PostController(
            PostService postService,
            TagService tagService,
            StorageService storageService
    ) {
        this.postService = postService;
        this.tagService = tagService;
        this.storageService = storageService;
    }

    @GetMapping("/posts")
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

        List<Tag> allTags = tagService.findAll();

        // Передаём данные в виде атрибута users
        model.addAttribute("posts", posts);
        model.addAttribute("uploadDir", uploadDir);
        model.addAttribute("tagId", tagId);
        model.addAttribute("allTags", allTags);

        // Создаем пустой пост для формы
        model.addAttribute("newPost", new Post());

        return "posts"; // Возвращаем название шаблона — posts.html
    }

    @GetMapping("/posts/{id}")
    public String getPost(
            @PathVariable("id") long id,
            @RequestParam(value = "editingCommentId", required = false, defaultValue = "0") long editingCommentId,
            Model model
    ) {
        // Получаем пост по ID из репозитория
        Optional<Post> postOptional = postService.findById(id);

        List<Tag> allTags = tagService.findAll();

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

    @PostMapping("/posts/add")
    public String addPost(
            @ModelAttribute Post post,
            @RequestParam(name = "tagIds", required = false) List<Long> tagIds,
            @RequestParam(name = "imageFile", required = false) MultipartFile imageFile
    ) {
        // Обработка загрузки файла
        if (imageFile != null && !imageFile.isEmpty()) {
            storageService.store(imageFile);

            // Устанавливаем имя файла в объект Post
            String fileName = imageFile.getOriginalFilename();
            post.setImageFileName(fileName);
        }

        if (tagIds != null) {
            // Получаем выбранные теги по их ID и добавляем их в пост
            for (Long tagId : tagIds) {
                Tag tag = tagService.findById(tagId).orElse(null);
                if (tag != null) {
                    post.addTag(tag);
                }
            }
        }

        // Сохраняем пост
        postService.add(post);

        return "redirect:/posts"; // Перенаправляем на страницу со списком постов
    }

    @PostMapping("/posts/update/{postId}")
    public String updatePost(
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
            // Сохраняем новое изображение
            storageService.store(imageFile);

            // Обновляем имя файла в объекте Post
            String fileName = imageFile.getOriginalFilename();

            savedPost.setImageFileName(fileName);
        }

        // Обновляем теги
        savedPost.removeAllTags();
        if (tagIds != null) {
            for (Long tagId : tagIds) {
                Tag tag = tagService.findById(tagId).orElse(null);
                if (tag != null) {
                    savedPost.addTag(tag);
                }
            }
        }

        // Сохраняем обновленный пост
        postService.update(savedPost);

        return "redirect:/posts/" + savedPost.getId();
    }

    @PostMapping("/posts/incrementLikes/{postId}")
    public String incrementPostLikes(@PathVariable(name = "postId") Long postId) {
        postService.incrementLikes(postId);
        return "redirect:/posts/" + postId;
    }

    @PostMapping(value = "/posts/delete/{postId}", params = "_method=delete")
    public String deletePost(@PathVariable(name = "postId") Long postId) {
        postService.deleteById(postId);
        return "redirect:/posts";
    }
}
