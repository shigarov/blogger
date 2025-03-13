# Blogger
Веб-приложение для управления постами. 
Оно позволяет создавать, редактировать, удалять и просматривать посты, 
а также добавлять к ним теги и комментарии.

## Функциональность

### 1. Просмотр списка постов
- **Эндпоинт:** `GET /posts`
- **Параметры:**
    - `tagId` (опционально): Фильтрация постов по тегу.
    - `page` (опционально): Номер страницы (по умолчанию 0).
    - `size` (опционально): Количество постов на странице (по умолчанию 10).
- **Описание:** Возвращает список постов с возможностью пагинации и фильтрации по тегам.

### 2. Просмотр отдельного поста
- **Эндпоинт:** `GET /posts/{id}`
- **Параметры:**
    - `editingCommentId` (опционально): ID комментария, который редактируется.
- **Описание:** Возвращает детали поста по его ID.

### 3. Добавление нового поста
- **Эндпоинт:** `POST /posts/add`
- **Параметры:**
    - `post`: Объект поста (заголовок, текст).
    - `tagIds` (опционально): Список ID тегов, связанных с постом.
    - `imageFile` (опционально): Изображение для поста.
- **Описание:** Создает новый пост с возможностью добавления тегов и изображения.

### 4. Редактирование поста
- **Эндпоинт:** `POST /posts/update/{postId}`
- **Параметры:**
    - `title` (опционально): Новый заголовок поста.
    - `text` (опционально): Новый текст поста.
    - `tagIds` (опционально): Список ID тегов, связанных с постом.
    - `imageFile` (опционально): Новое изображение для поста.
- **Описание:** Обновляет существующий пост, включая заголовок, текст, теги и изображение.

### 5. Увеличение количества лайков
- **Эндпоинт:** `POST /posts/incrementLikes/{postId}`
- **Описание:** Увеличивает количество лайков у поста на 1.

### 6. Удаление поста
- **Эндпоинт:** `POST /posts/delete/{postId}`
- **Параметры:**
    - `_method=delete`: Параметр для указания метода DELETE.
- **Описание:** Удаляет пост по его ID.

## Запуск проекта

1. Убедитесь, что у вас установлены Java и Maven, а также Tomcat или Jetty.
2. Клонируйте репозиторий:
    ```bash
    git clone https://github.com/shigarov/blogger.git
    ```
3. Перейдите в директорию проекта:
    ```bash 
    cd <project-directory>
    ```
4. Соберите проект:
    ```bash 
    gradlew clean build
    ```
5. Запустите приложение:
    ```bash 
    cp java -jar build/libs/blogger-2.0-SNAPSHOT.jar
    ```
6. Откройте браузер и перейдите по адресу:
    ```bash 
    http://localhost:8081/posts
    ```

