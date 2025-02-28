package shigarov.practicum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//@Controller
//public class FileUploadController {
//
//    private static final String UPLOAD_DIR = "classpath:/images"; //""uploads"; // Директория для сохранения файлов
//
//    @PostMapping("/upload")
//    public String handleFileUpload(@RequestParam("image") MultipartFile file) {
//        if (file.isEmpty()) {
//            return "redirect:/posts"; // Если файл не выбран, перенаправляем обратно
//        }
//
//        try {
//            // Создаем директорию, если она не существует
//            File uploadDir = new File(UPLOAD_DIR);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdirs();
//            }
//
//            // Сохраняем файл
//            byte[] bytes = file.getBytes();
//            Path path = Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename());
//            Files.write(path, bytes);
//
//            // Возвращаем имя файла (можно сохранить его в базе данных)
//            return "redirect:/posts?image=" + file.getOriginalFilename();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "redirect:/posts"; // В случае ошибки перенаправляем обратно
//        }
//    }
//}

@Controller
public class FileUploadController {

    private static final String UPLOAD_DIR = "uploads";

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("image") MultipartFile file) {
        if (file.isEmpty()) {
            return "redirect:/posts";
        }

        try {
            // Создаем директорию, если она не существует
            Files.createDirectories(Paths.get(UPLOAD_DIR));

            // Сохраняем файл
            Path path = Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename());
            Files.write(path, file.getBytes());

            return "redirect:/posts?image=" + file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/posts";
        }
    }
}