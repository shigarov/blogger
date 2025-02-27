DROP TABLE IF EXISTS post_tags;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS tags;

-- Таблица постов
CREATE TABLE posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,          -- Уникальный идентификатор поста
    title VARCHAR(255) NOT NULL,                   -- Название поста
    image VARCHAR(255),                            -- Ссылка на картинку (путь или URL)
    text CLOB NOT NULL,                            -- Текст поста, разбитый на абзацы
    likes INT DEFAULT 0                            -- Счётчик лайков (по умолчанию 0)
);

-- Таблица комментариев
CREATE TABLE comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,          -- Уникальный идентификатор комментария
    text CLOB NOT NULL,                            -- Текст комментария
    post_id BIGINT NOT NULL,                       -- ID поста, к которому относится комментарий
    FOREIGN KEY (post_id) REFERENCES posts(id)     -- Внешний ключ на таблицу posts
);

-- Таблица уникальных тегов
CREATE TABLE tags (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Уникальный идентификатор тега
    name VARCHAR(255) NOT NULL UNIQUE      -- Название тега (уникальное)
);

-- Связь постов и тегов
CREATE TABLE posts_tags (
    post_id BIGINT NOT NULL,                                       -- ID поста
    tag_id BIGINT NOT NULL,                                        -- ID тега
    PRIMARY KEY (post_id, tag_id),                                 -- Составной первичный ключ
    FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE CASCADE,  -- Внешний ключ на posts
    FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE     -- Внешний ключ на tags
);