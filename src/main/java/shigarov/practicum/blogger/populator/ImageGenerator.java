package shigarov.practicum.blogger.populator;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Component
public class ImageGenerator {

    public BufferedImage generateImage(final int width, final int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        // Заливка фона белым цветом
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Генерация 10 случайных геометрических примитивов
        Random random = new Random();
        for (int j = 0; j < 10; j++) {
            // Случайный цвет для примитива
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            g2d.setColor(color);

            // Случайные координаты и размеры примитива
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int w = random.nextInt(width / 2); // Ограничиваем размер, чтобы примитив не выходил за границы
            int h = random.nextInt(height / 2);

            // Случайный выбор типа примитива
            int shapeType = random.nextInt(3); // 0 - прямоугольник, 1 - эллипс, 2 - линия
            switch (shapeType) {
                case 0:
                    g2d.fillRect(x, y, w, h);
                    break;
                case 1:
                    g2d.fillOval(x, y, w, h);
                    break;
                case 2:
                    int x2 = random.nextInt(width);
                    int y2 = random.nextInt(height);
                    g2d.drawLine(x, y, x2, y2);
                    break;
            }
        }

        g2d.dispose();

        return image;
    }
}