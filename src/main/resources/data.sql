-- Вставляем 10 тестовых постов
INSERT INTO posts (title, image, content, tags, likes) VALUES
('Пост 1', FILE_READ('images/image.png'), 'Текст поста 1, разбитый на абзацы.', 'технологии, блог', 10),
('Пост 2', FILE_READ('images/image.png'), 'Текст поста 2, разбитый на абзацы.', 'программирование', 5),
('Пост 3', FILE_READ('images/image.png'), 'Текст поста 3, разбитый на абзацы.', 'блог, жизнь', 15),
('Пост 4', FILE_READ('images/image.png'), 'Текст поста 4, разбитый на абзацы.', 'тест, блог', 20),
('Пост 5', FILE_READ('images/image.png'), 'Текст поста 5, разбитый на абзацы.', 'технологии, программирование', 8),
('Пост 6', FILE_READ('images/image.png'), 'Текст поста 6, разбитый на абзацы.', 'блог, тест', 12),
('Пост 7', FILE_READ('images/image.png'), 'Текст поста 7, разбитый на абзацы.', 'жизнь, блог', 7),
('Пост 8', FILE_READ('images/image.png'), 'Текст поста 8, разбитый на абзацы.', 'технологии', 30),
('Пост 9', FILE_READ('images/image.png'), 'Текст поста 9, разбитый на абзацы.', 'программирование, тест', 25),
('Пост 10', FILE_READ('images/image.png'), 'Текст поста 10, разбитый на абзацы.', 'блог, технологии', 18);

-- Комментарии для поста 1
INSERT INTO comments (comment_text, post_id) VALUES
('Отличный пост!', 1),
('Спасибо за информацию.', 1);

-- Комментарии для поста 2
INSERT INTO comments (comment_text, post_id) VALUES
('Интересно, но можно подробнее?', 2),
('Полезный материал.', 2),
('Согласен с автором.', 2);

-- Комментарии для поста 3
INSERT INTO comments (comment_text, post_id) VALUES
('Первый комментарий к посту 3.', 3);

-- Комментарии для поста 4
INSERT INTO comments (comment_text, post_id) VALUES
('Классный пост!', 4),
('Много нового узнал.', 4);

-- Комментарии для поста 5
INSERT INTO comments (comment_text, post_id) VALUES
('Спасибо за статью.', 5),
('Интересная точка зрения.', 5),
('Жду продолжения.', 5);

-- Комментарии для поста 6
INSERT INTO comments (comment_text, post_id) VALUES
('Отлично написано!', 6);

-- Комментарии для поста 7
INSERT INTO comments (comment_text, post_id) VALUES
('Полезно для начинающих.', 7),
('Спасибо за советы.', 7);

-- Комментарии для поста 8
INSERT INTO comments (comment_text, post_id) VALUES
('Интересный материал.', 8),
('Много полезной информации.', 8),
('Рекомендую к прочтению.', 8);

-- Комментарии для поста 9
INSERT INTO comments (comment_text, post_id) VALUES
('Отличный пост!', 9),
('Спасибо за труд.', 9);

-- Комментарии для поста 10
INSERT INTO comments (comment_text, post_id) VALUES
('Очень познавательно.', 10),
('Спасибо за подробности.', 10);