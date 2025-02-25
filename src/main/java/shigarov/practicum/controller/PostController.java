package shigarov.practicum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shigarov.practicum.model.Comment;
import shigarov.practicum.model.Post;
import shigarov.practicum.service.PostService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts") // Контроллер обрабатывает запросы /posts
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping // GET запрос /posts
    public String posts(Model model) {
        // Данные теперь получаются программно
//        List<Post> posts = Arrays.asList(
//                new Post(1L, "Пост 1", "image1.png", "Текст поста 1, разбитый на абзацы.", "технологии, блог", 10, 2),
//                new Post(2L, "Пост 2", "image2.png", "Текст поста 2, разбитый на абзацы.", "программирование", 5, 3),
//                new Post(3L, "Пост 3", "image3.png", "Текст поста 3, разбитый на абзацы.", "блог, жизнь", 15, 1)
//        );

        List<Post> posts = service.findAll();

        // Передаём данные в виде атрибута users
        model.addAttribute("posts", posts);

        return "posts"; // Возвращаем название шаблона — posts.html
    }

//    @GetMapping(value = "/{id}")
//    public String post(Model model, @PathVariable(name = "id") Long id) {
//        Optional<Post> post = service.findById(id);
//        // Передаём данные в виде атрибута users
//        model.addAttribute("post", post);
//        return "post"; // Возвращаем название шаблона — post.html
//    }

    @GetMapping("/{id}")
    public String post(@PathVariable("id") long id, Model model) {
        // Получаем пост по ID из репозитория
        Optional<Post> postOptional = service.findById(id);

//        Post post = new Post();
//        post.setId(100L);
//        post.setTitle("Мой пост");
//        post.setImage("image1.png");
//        post.setText("бла бла бла бла");
//        post.setTags("технологии, жизнь");
//        post.setLikes(1000);
//
//        List<Comment> comments = Arrays.asList(
//                new Comment(1L, "Вах вах!"),
//                new Comment(2L, "ням ням!")
//        );
//        post.setComments(comments);
//        Optional<Post> postOptional = Optional.ofNullable(post);

        // Если пост найден, добавляем его в модель
        if (postOptional.isPresent()) {
            model.addAttribute("post", postOptional.get());
            return "post";  // Имя Thymeleaf шаблона для отображения поста
        } else {
            // Если пост не найден, возвращаем страницу с ошибкой
            return "error/404";  // Имя Thymeleaf шаблона для страницы 404
        }
    }

    @PostMapping
    public String save(@ModelAttribute Post post) {
        service.save(post);

        return "redirect:/posts"; // Возвращаем страницу, чтобы она перезагрузилась
    }

}
