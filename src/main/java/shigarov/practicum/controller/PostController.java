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
    public String posts(@RequestParam(name = "tagId", required = false, defaultValue = "0") Long tagId,
                        //Pageable pageable,
                        @RequestParam(name = "page", required = false, defaultValue = "0") int pageNumber,
                        @RequestParam(name = "size", required = false, defaultValue = "10") int pageSize,
                        Model model) {
        // Данные теперь получаются программно
//        List<Post> posts = Arrays.asList(
//                new Post(1L, "Пост 1", "image1.png", "Текст поста 1, разбитый на абзацы.", "технологии, блог", 10, 2),
//                new Post(2L, "Пост 2", "image2.png", "Текст поста 2, разбитый на абзацы.", "программирование", 5, 3),
//                new Post(3L, "Пост 3", "image3.png", "Текст поста 3, разбитый на абзацы.", "блог, жизнь", 15, 1)
//        );

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

        List<Tag> tags = service.findAllTags();

        // Передаём данные в виде атрибута users
        model.addAttribute("posts", posts);
        model.addAttribute("tagId", tagId);
        model.addAttribute("tags", tags);

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
    public String postById(@PathVariable("id") long id, Model model) {
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

//    @GetMapping
//    public String postsByTag(@RequestParam("tag") String tag, Model model){
//        List<Post> posts = service.findAllByTag(tag);
//
//        // Передаём данные в виде атрибута users
//        model.addAttribute("posts", posts);
//
//        return "posts"; // Возвращаем название шаблона — posts.html
//    }

    @PostMapping
    public String create(@ModelAttribute Post post) {
        //service.create(post);

        return "redirect:/posts"; // Возвращаем страницу, чтобы она перезагрузилась
    }

}
