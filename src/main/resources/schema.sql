DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS posts;

-- Таблица постов
CREATE TABLE posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,          -- Уникальный идентификатор поста
    title VARCHAR(255) NOT NULL,                   -- Название поста
    image VARCHAR(255),                            -- Ссылка на картинку (путь или URL)
    text CLOB NOT NULL,                            -- Текст поста, разбитый на абзацы
    tags VARCHAR(255),                             -- Теги поста (можно хранить как строку, разделённую запятыми)
    likes INT DEFAULT 0                            -- Счётчик лайков (по умолчанию 0)
);


-- Таблица комментариев
CREATE TABLE comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,          -- Уникальный идентификатор комментария
    text CLOB NOT NULL,                    -- Текст комментария
    post_id BIGINT NOT NULL,                       -- ID поста, к которому относится комментарий
    FOREIGN KEY (post_id) REFERENCES posts(id)     -- Внешний ключ на таблицу posts
);
