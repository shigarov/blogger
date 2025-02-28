package shigarov.practicum.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shigarov.practicum.model.Post;
import shigarov.practicum.model.Tag;
import shigarov.practicum.service.PostService;

import java.util.*;

@Controller
@RequestMapping("/posts") // Контроллер обрабатывает запросы /posts
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping // GET запрос /posts
    public String posts(@RequestParam(name = "tagId", required = false, defaultValue = "0") Long tagId,
                        @RequestParam(name = "page", required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(name = "size", required = false, defaultValue = "10") int pageSize,
                        Model model) {

        final Pageable pageable = PageRequest.of(pageNumber, pageSize);

        List<Post> posts;

        if (tagId != null && tagId > 0) {
            Page<Post> postsPage = service.findAllByTag(pageable, tagId);
            posts = postsPage.stream().toList();
            model.addAttribute("page", postsPage);
        } else {
            Page<Post> postsPage = service.findAll(pageable);
            posts = postsPage.stream().toList();
            model.addAttribute("page", postsPage);
        }

        List<Tag> allTags = service.findAllTags();

        // Передаём данные в виде атрибута users
        model.addAttribute("posts", posts);
        model.addAttribute("tagId", tagId);
        model.addAttribute("allTags", allTags);

        // Создаем пустой пост для формы
        model.addAttribute("newPost", new Post());

        return "posts"; // Возвращаем название шаблона — posts.html
    }

    @GetMapping("/{id}")
    public String postById(@PathVariable("id") long id, Model model) {
        // Получаем пост по ID из репозитория
        Optional<Post> postOptional = service.findById(id);

        // Если пост найден, добавляем его в модель
        if (postOptional.isPresent()) {
            model.addAttribute("post", postOptional.get());
            return "post";
        } else {
            // Если пост не найден, возвращаем страницу с ошибкой
            return "error/404";  // Имя Thymeleaf шаблона для страницы 404
        }
    }

    @PostMapping
    public String addPost(
            @ModelAttribute Post post,
            @RequestParam(name = "tagIds", required = false) List<Long> tagIds
    )
    {
        // Получаем выбранные теги по их ID и добавляем их в пост
        for (Long tagId : tagIds) {
            Tag tag = service.findTagById(tagId).orElse(null);
            if (tag != null) {
                post.addTag(tag);
            }
        }

        // Сохраняем пост
        service.addPost(post);

        return "redirect:/posts"; // Перенаправляем на страницу со списком постов
    }

}
