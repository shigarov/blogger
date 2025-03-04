package shigarov.practicum.controller; // Класс находится в пакете с контроллерами

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller // Указываем Spring, что этот компонент является контроллером
public class HomeController {

    @GetMapping("/home") // Принимаем GET-запрос по адресу /home
    @ResponseBody        // Указываем, что возвращаемое значение является ответом
    public String homePage() {
        return "<h1>0</h1>"; // Ответ
    }

}