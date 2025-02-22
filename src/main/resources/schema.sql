-- Создание таблицы для постов
CREATE TABLE posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,          -- Уникальный идентификатор поста
    title VARCHAR(255) NOT NULL,                   -- Название поста
    image BLOB,                                    -- Бинарные данные картинки
    content CLOB NOT NULL,                         -- Текст поста, разбитый на абзацы
    tags VARCHAR(255),                             -- Теги поста (можно хранить как строку, разделённую запятыми)
    likes INT DEFAULT 0                            -- Счётчик лайков (по умолчанию 0)
);

-- Создание таблицы для комментариев
CREATE TABLE comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,          -- Уникальный идентификатор комментария
    comment_text CLOB NOT NULL,                    -- Текст комментария
    post_id BIGINT NOT NULL,                       -- ID поста, к которому относится комментарий
    FOREIGN KEY (post_id) REFERENCES posts(id)     -- Внешний ключ на таблицу posts
);