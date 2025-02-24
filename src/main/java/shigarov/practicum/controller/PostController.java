package shigarov.practicum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shigarov.practicum.model.Post;
import shigarov.practicum.service.PostService;

import java.util.List;

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



}
