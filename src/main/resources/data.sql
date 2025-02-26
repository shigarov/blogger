---- 10 тестовых постов
--INSERT INTO posts (title, image, text, tags, likes) VALUES
--('Пост 1', 'image1.png', 'Текст поста 1, разбитый на абзацы.', 'технологии, блог', 10),
--('Пост 2', 'image2.png', 'Текст поста 2, разбитый на абзацы.', 'программирование', 5),
--('Пост 3', 'image3.png', 'Текст поста 3, разбитый на абзацы.', 'блог, жизнь', 15),
--('Пост 4', NULL, 'Текст поста 4, разбитый на абзацы.', 'тест, блог', 20),
--('Пост 5', NULL, 'Текст поста 5, разбитый на абзацы.', 'технологии, программирование', 8),
--('Пост 6', NULL, 'Текст поста 6, разбитый на абзацы.', 'блог, тест', 12),
--('Пост 7', NULL, 'Текст поста 7, разбитый на абзацы.', 'жизнь, блог', 7),
--('Пост 8', NULL, 'Текст поста 8, разбитый на абзацы.', 'технологии', 30),
--('Пост 9', NULL, 'Текст поста 9, разбитый на абзацы.', 'программирование, тест', 25),
--('Пост 10', NULL, 'Текст поста 10, разбитый на абзацы.', 'блог, технологии', 18);
--
---- Комментарии для поста 1
--INSERT INTO comments (comment_text, post_id) VALUES
--('Отличный пост!', 1),
--('Спасибо за информацию.', 1);
--
---- Комментарии для поста 2
--INSERT INTO comments (comment_text, post_id) VALUES
--('Интересно, но можно подробнее?', 2),
--('Полезный материал.', 2),
--('Согласен с автором.', 2);
--
---- Комментарии для поста 3
--INSERT INTO comments (comment_text, post_id) VALUES
--('Первый комментарий к посту 3.', 3);
--
---- Комментарии для поста 4
--INSERT INTO comments (comment_text, post_id) VALUES
--('Классный пост!', 4),
--('Много нового узнал.', 4);
--
---- Комментарии для поста 5
--INSERT INTO comments (comment_text, post_id) VALUES
--('Спасибо за статью.', 5),
--('Интересная точка зрения.', 5),
--('Жду продолжения.', 5);
--
---- Комментарии для поста 6
--INSERT INTO comments (comment_text, post_id) VALUES
--('Отлично написано!', 6);
--
---- Комментарии для поста 7
--INSERT INTO comments (comment_text, post_id) VALUES
--('Полезно для начинающих.', 7),
--('Спасибо за советы.', 7);
--
---- Комментарии для поста 8
--INSERT INTO comments (comment_text, post_id) VALUES
--('Интересный материал.', 8),
--('Много полезной информации.', 8),
--('Рекомендую к прочтению.', 8);
--
---- Комментарии для поста 9
--INSERT INTO comments (comment_text, post_id) VALUES
--('Отличный пост!', 9),
--('Спасибо за труд.', 9);
--
---- Комментарии для поста 10
--INSERT INTO comments (comment_text, post_id) VALUES
--('Очень познавательно.', 10),
--('Спасибо за подробности.', 10);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(1, 'Пост 1', 'image1.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', '', 117);

INSERT INTO comments (id, text, post_id) VALUES
(1, 'замечательный пост', 1),
(2, 'отличный пост', 1),
(3, 'самый лучший пост в мире', 1),
(4, 'отвратительный пост', 1),
(5, 'замечательный пост', 1);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(2, 'Пост 2', 'image2.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'жизнь, блог, программирование, технологии', 627);

INSERT INTO comments (id, text, post_id) VALUES
(6, 'отвратительный пост', 2),
(7, 'отличный пост', 2),
(8, 'самый лучший пост в мире', 2);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(3, 'Пост 3', 'image3.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3', 'жизнь, блог, программирование, котики, технологии', 761);

INSERT INTO comments (id, text, post_id) VALUES
(9, 'ужасный пост', 3);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(4, 'Пост 4', 'image4.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1', 'блог, программирование, котики, тест, технологии', 838);

INSERT INTO comments (id, text, post_id) VALUES
(10, 'самый лучший пост в мире', 4),
(11, 'хуже ничего в жизни не читал', 4),
(12, 'самый лучший пост в мире', 4),
(13, 'самый лучший пост в мире', 4);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(5, 'Пост 5', 'image5.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2', 'жизнь, блог, программирование, котики, технологии', 450);

INSERT INTO comments (id, text, post_id) VALUES
(14, 'замечательный пост', 5),
(15, 'замечательный пост', 5);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(6, 'Пост 6', 'image6.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 3: Строка 1', 'блог, программирование, тест, технологии', 285);

INSERT INTO comments (id, text, post_id) VALUES
(16, 'отличный пост', 6),
(17, 'отвратительный пост', 6);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(7, 'Пост 7', 'image7.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3', 'блог, программирование, тест, технологии', 665);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(8, 'Пост 8', 'image8.png', 'Абзац 1: Строка 1', 'блог, программирование, тест, технологии', 575);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(9, 'Пост 9', 'image9.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'жизнь, котики, технологии', 712);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(10, 'Пост 10', 'image10.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 2: Строка 1', 'блог, котики, технологии', 834);

INSERT INTO comments (id, text, post_id) VALUES
(18, 'хуже ничего в жизни не читал', 10);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(11, 'Пост 11', 'image11.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'котики, тест, технологии', 725);

INSERT INTO comments (id, text, post_id) VALUES
(19, 'хуже ничего в жизни не читал', 11),
(20, 'ужасный пост', 11),
(21, 'замечательный пост', 11),
(22, 'замечательный пост', 11),
(23, 'самый лучший пост в мире', 11);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(12, 'Пост 12', 'image12.png', 'Абзац 1: Строка 1 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'жизнь, блог, котики', 88);

INSERT INTO comments (id, text, post_id) VALUES
(24, 'ужасный пост', 12),
(25, 'ужасный пост', 12),
(26, 'самый лучший пост в мире', 12);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(13, 'Пост 13', 'image13.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3', 'технологии', 392);

INSERT INTO comments (id, text, post_id) VALUES
(27, 'самый лучший пост в мире', 13),
(28, 'отвратительный пост', 13),
(29, 'отличный пост', 13);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(14, 'Пост 14', 'image14.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', '', 545);

INSERT INTO comments (id, text, post_id) VALUES
(30, 'отличный пост', 14),
(31, 'самый лучший пост в мире', 14),
(32, 'хуже ничего в жизни не читал', 14),
(33, 'замечательный пост', 14),
(34, 'хуже ничего в жизни не читал', 14);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(15, 'Пост 15', 'image15.png', 'Абзац 1: Строка 1 \r\n Строка 2', 'программирование, котики', 528);

INSERT INTO comments (id, text, post_id) VALUES
(35, 'отличный пост', 15),
(36, 'отвратительный пост', 15),
(37, 'ужасный пост', 15);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(16, 'Пост 16', 'image16.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'технологии', 681);

INSERT INTO comments (id, text, post_id) VALUES
(38, 'отвратительный пост', 16),
(39, 'ужасный пост', 16),
(40, 'хуже ничего в жизни не читал', 16);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(17, 'Пост 17', 'image17.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3', 'тест, котики', 549);

INSERT INTO comments (id, text, post_id) VALUES
(41, 'отвратительный пост', 17),
(42, 'ужасный пост', 17),
(43, 'хуже ничего в жизни не читал', 17),
(44, 'отличный пост', 17);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(18, 'Пост 18', 'image18.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'жизнь, блог, программирование, тест, котики', 663);

INSERT INTO comments (id, text, post_id) VALUES
(45, 'самый лучший пост в мире', 18),
(46, 'хуже ничего в жизни не читал', 18),
(47, 'отвратительный пост', 18),
(48, 'замечательный пост', 18);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(19, 'Пост 19', 'image19.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3', 'блог, программирование, тест, технологии', 66);

INSERT INTO comments (id, text, post_id) VALUES
(49, 'самый лучший пост в мире', 19),
(50, 'хуже ничего в жизни не читал', 19),
(51, 'замечательный пост', 19),
(52, 'хуже ничего в жизни не читал', 19),
(53, 'замечательный пост', 19);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(20, 'Пост 20', 'image20.png', 'Абзац 1: Строка 1 \r\n Строка 2', 'жизнь, блог, программирование, котики, технологии', 645);

INSERT INTO comments (id, text, post_id) VALUES
(54, 'ужасный пост', 20),
(55, 'хуже ничего в жизни не читал', 20),
(56, 'ужасный пост', 20),
(57, 'отличный пост', 20),
(58, 'отличный пост', 20);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(21, 'Пост 21', 'image21.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'жизнь, программирование, тест, котики, технологии', 529);

INSERT INTO comments (id, text, post_id) VALUES
(59, 'хуже ничего в жизни не читал', 21),
(60, 'замечательный пост', 21),
(61, 'отличный пост', 21);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(22, 'Пост 22', 'image22.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1', '', 195);

INSERT INTO comments (id, text, post_id) VALUES
(62, 'замечательный пост', 22);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(23, 'Пост 23', 'image23.png', 'Абзац 1: Строка 1 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 3: Строка 1', 'блог, программирование, тест, технологии', 961);

INSERT INTO comments (id, text, post_id) VALUES
(63, 'отличный пост', 23),
(64, 'отвратительный пост', 23),
(65, 'отвратительный пост', 23),
(66, 'хуже ничего в жизни не читал', 23);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(24, 'Пост 24', 'image24.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2 \r\n Строка 3', 'блог, программирование, тест', 875);

INSERT INTO comments (id, text, post_id) VALUES
(67, 'замечательный пост', 24),
(68, 'отличный пост', 24),
(69, 'хуже ничего в жизни не читал', 24),
(70, 'хуже ничего в жизни не читал', 24);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(25, 'Пост 25', 'image25.png', 'Абзац 1: Строка 1 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3', 'жизнь, программирование, котики, технологии', 103);

INSERT INTO comments (id, text, post_id) VALUES
(71, 'отвратительный пост', 25),
(72, 'замечательный пост', 25),
(73, 'замечательный пост', 25),
(74, 'ужасный пост', 25),
(75, 'отличный пост', 25);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(26, 'Пост 26', 'image26.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2', 'технологии', 557);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(27, 'Пост 27', 'image27.png', 'Абзац 1: Строка 1 \r\n Строка 2', 'жизнь, котики, технологии', 97);

INSERT INTO comments (id, text, post_id) VALUES
(76, 'отличный пост', 27),
(77, 'отвратительный пост', 27),
(78, 'самый лучший пост в мире', 27),
(79, 'отличный пост', 27),
(80, 'замечательный пост', 27);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(28, 'Пост 28', 'image28.png', 'Абзац 1: Строка 1 \r\n Строка 2', 'блог, программирование, котики', 246);

INSERT INTO comments (id, text, post_id) VALUES
(81, 'отвратительный пост', 28),
(82, 'самый лучший пост в мире', 28);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(29, 'Пост 29', 'image29.png', 'Абзац 1: Строка 1 \r\n Строка 2', 'блог, тест', 566);

INSERT INTO comments (id, text, post_id) VALUES
(83, 'хуже ничего в жизни не читал', 29),
(84, 'отвратительный пост', 29),
(85, 'ужасный пост', 29),
(86, 'отличный пост', 29),
(87, 'хуже ничего в жизни не читал', 29);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(30, 'Пост 30', 'image30.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2 \r\n Строка 3', 'блог, тест', 848);

INSERT INTO comments (id, text, post_id) VALUES
(88, 'хуже ничего в жизни не читал', 30);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(31, 'Пост 31', 'image31.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2', '', 616);

INSERT INTO comments (id, text, post_id) VALUES
(89, 'хуже ничего в жизни не читал', 31);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(32, 'Пост 32', 'image32.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 2: Строка 1', 'блог, котики, технологии', 131);

INSERT INTO comments (id, text, post_id) VALUES
(90, 'отличный пост', 32),
(91, 'отвратительный пост', 32),
(92, 'хуже ничего в жизни не читал', 32),
(93, 'замечательный пост', 32),
(94, 'хуже ничего в жизни не читал', 32);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(33, 'Пост 33', 'image33.png', 'Абзац 1: Строка 1 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'блог, программирование, котики, тест', 762);

INSERT INTO comments (id, text, post_id) VALUES
(95, 'хуже ничего в жизни не читал', 33),
(96, 'отличный пост', 33),
(97, 'ужасный пост', 33),
(98, 'самый лучший пост в мире', 33);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(34, 'Пост 34', 'image34.png', 'Абзац 1: Строка 1', 'тест', 740);

INSERT INTO comments (id, text, post_id) VALUES
(99, 'замечательный пост', 34),
(100, 'отвратительный пост', 34),
(101, 'самый лучший пост в мире', 34),
(102, 'ужасный пост', 34);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(35, 'Пост 35', 'image35.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'жизнь, блог, котики, тест', 112);

INSERT INTO comments (id, text, post_id) VALUES
(103, 'замечательный пост', 35),
(104, 'хуже ничего в жизни не читал', 35),
(105, 'отвратительный пост', 35),
(106, 'замечательный пост', 35),
(107, 'хуже ничего в жизни не читал', 35);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(36, 'Пост 36', 'image36.png', 'Абзац 1: Строка 1 \r\n Строка 2', 'жизнь, котики, технологии', 569);

INSERT INTO comments (id, text, post_id) VALUES
(108, 'отвратительный пост', 36),
(109, 'отличный пост', 36),
(110, 'ужасный пост', 36);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(37, 'Пост 37', 'image37.png', 'Абзац 1: Строка 1 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 3: Строка 1', 'жизнь, тест, котики', 553);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(38, 'Пост 38', 'image38.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3', 'котики', 129);

INSERT INTO comments (id, text, post_id) VALUES
(111, 'отличный пост', 38),
(112, 'отличный пост', 38),
(113, 'хуже ничего в жизни не читал', 38),
(114, 'отвратительный пост', 38);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(39, 'Пост 39', 'image39.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'блог, программирование, тест, котики', 610);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(40, 'Пост 40', 'image40.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'жизнь, программирование, тест, котики, технологии', 455);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(41, 'Пост 41', 'image41.png', 'Абзац 1: Строка 1', 'жизнь, котики, тест, технологии', 841);

INSERT INTO comments (id, text, post_id) VALUES
(115, 'хуже ничего в жизни не читал', 41),
(116, 'самый лучший пост в мире', 41),
(117, 'замечательный пост', 41),
(118, 'отвратительный пост', 41),
(119, 'самый лучший пост в мире', 41);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(42, 'Пост 42', 'image42.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'жизнь, программирование, технологии', 621);

INSERT INTO comments (id, text, post_id) VALUES
(120, 'отвратительный пост', 42),
(121, 'отличный пост', 42),
(122, 'отвратительный пост', 42),
(123, 'отличный пост', 42);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(43, 'Пост 43', 'image43.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'жизнь, программирование, тест, котики, технологии', 570);

INSERT INTO comments (id, text, post_id) VALUES
(124, 'отличный пост', 43),
(125, 'замечательный пост', 43),
(126, 'хуже ничего в жизни не читал', 43);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(44, 'Пост 44', 'image44.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3', 'блог, котики, тест, технологии', 309);

INSERT INTO comments (id, text, post_id) VALUES
(127, 'самый лучший пост в мире', 44),
(128, 'отличный пост', 44);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(45, 'Пост 45', 'image45.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 2: Строка 1', 'жизнь, программирование, котики, тест', 955);

INSERT INTO comments (id, text, post_id) VALUES
(129, 'ужасный пост', 45),
(130, 'ужасный пост', 45),
(131, 'хуже ничего в жизни не читал', 45);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(46, 'Пост 46', 'image46.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3', 'тест', 898);

INSERT INTO comments (id, text, post_id) VALUES
(132, 'ужасный пост', 46);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(47, 'Пост 47', 'image47.png', 'Абзац 1: Строка 1 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2', '', 277);

INSERT INTO comments (id, text, post_id) VALUES
(133, 'ужасный пост', 47),
(134, 'отличный пост', 47),
(135, 'отличный пост', 47),
(136, 'хуже ничего в жизни не читал', 47),
(137, 'ужасный пост', 47);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(48, 'Пост 48', 'image48.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 2: Строка 1 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'жизнь, тест, технологии', 366);

INSERT INTO comments (id, text, post_id) VALUES
(138, 'замечательный пост', 48),
(139, 'отличный пост', 48),
(140, 'хуже ничего в жизни не читал', 48);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(49, 'Пост 49', 'image49.png', 'Абзац 1: Строка 1', 'жизнь, блог, тест', 935);

INSERT INTO comments (id, text, post_id) VALUES
(141, 'самый лучший пост в мире', 49),
(142, 'ужасный пост', 49);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(50, 'Пост 50', 'image50.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 2: Строка 1', 'котики', 504);

INSERT INTO comments (id, text, post_id) VALUES
(143, 'отличный пост', 50),
(144, 'отличный пост', 50),
(145, 'отличный пост', 50),
(146, 'отличный пост', 50),
(147, 'замечательный пост', 50);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(51, 'Пост 51', 'image51.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3', 'блог, программирование, технологии', 232);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(52, 'Пост 52', 'image52.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2', 'программирование, котики, тест, технологии', 935);

INSERT INTO comments (id, text, post_id) VALUES
(148, 'хуже ничего в жизни не читал', 52),
(149, 'замечательный пост', 52),
(150, 'отличный пост', 52),
(151, 'отличный пост', 52);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(53, 'Пост 53', 'image53.png', 'Абзац 1: Строка 1', 'жизнь, котики, тест', 895);

INSERT INTO comments (id, text, post_id) VALUES
(152, 'ужасный пост', 53),
(153, 'отвратительный пост', 53),
(154, 'отличный пост', 53),
(155, 'замечательный пост', 53),
(156, 'отличный пост', 53);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(54, 'Пост 54', 'image54.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 2: Строка 1 \r\n \r\n Абзац 3: Строка 1', 'блог, программирование, тест, котики, технологии', 808);

INSERT INTO comments (id, text, post_id) VALUES
(157, 'самый лучший пост в мире', 54),
(158, 'самый лучший пост в мире', 54),
(159, 'отвратительный пост', 54);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(55, 'Пост 55', 'image55.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3', 'котики', 487);

INSERT INTO comments (id, text, post_id) VALUES
(160, 'хуже ничего в жизни не читал', 55),
(161, 'ужасный пост', 55),
(162, 'ужасный пост', 55);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(56, 'Пост 56', 'image56.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2', 'тест, котики', 217);

INSERT INTO comments (id, text, post_id) VALUES
(163, 'отличный пост', 56),
(164, 'отвратительный пост', 56),
(165, 'отличный пост', 56),
(166, 'отвратительный пост', 56);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(57, 'Пост 57', 'image57.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'блог, тест, технологии', 33);

INSERT INTO comments (id, text, post_id) VALUES
(167, 'отличный пост', 57),
(168, 'замечательный пост', 57),
(169, 'ужасный пост', 57),
(170, 'ужасный пост', 57),
(171, 'хуже ничего в жизни не читал', 57);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(58, 'Пост 58', 'image58.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'жизнь, блог, программирование, котики, технологии', 419);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(59, 'Пост 59', 'image59.png', 'Абзац 1: Строка 1', 'жизнь, программирование, тест', 295);

INSERT INTO comments (id, text, post_id) VALUES
(172, 'хуже ничего в жизни не читал', 59),
(173, 'ужасный пост', 59),
(174, 'хуже ничего в жизни не читал', 59),
(175, 'самый лучший пост в мире', 59),
(176, 'хуже ничего в жизни не читал', 59);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(60, 'Пост 60', 'image60.png', 'Абзац 1: Строка 1 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3', 'жизнь, программирование, котики, тест', 870);

INSERT INTO comments (id, text, post_id) VALUES
(177, 'ужасный пост', 60),
(178, 'отвратительный пост', 60),
(179, 'самый лучший пост в мире', 60),
(180, 'самый лучший пост в мире', 60);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(61, 'Пост 61', 'image61.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'блог, тест', 621);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(62, 'Пост 62', 'image62.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 3: Строка 1', 'технологии', 80);

INSERT INTO comments (id, text, post_id) VALUES
(181, 'отличный пост', 62),
(182, 'хуже ничего в жизни не читал', 62),
(183, 'самый лучший пост в мире', 62),
(184, 'ужасный пост', 62),
(185, 'отвратительный пост', 62);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(63, 'Пост 63', 'image63.png', 'Абзац 1: Строка 1', 'жизнь, программирование, котики, технологии', 939);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(64, 'Пост 64', 'image64.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3', '', 291);

INSERT INTO comments (id, text, post_id) VALUES
(186, 'хуже ничего в жизни не читал', 64),
(187, 'замечательный пост', 64),
(188, 'замечательный пост', 64),
(189, 'хуже ничего в жизни не читал', 64),
(190, 'хуже ничего в жизни не читал', 64);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(65, 'Пост 65', 'image65.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2 \r\n Строка 3', 'тест', 561);

INSERT INTO comments (id, text, post_id) VALUES
(191, 'ужасный пост', 65),
(192, 'замечательный пост', 65);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(66, 'Пост 66', 'image66.png', 'Абзац 1: Строка 1', 'жизнь, программирование, котики, тест, технологии', 611);

INSERT INTO comments (id, text, post_id) VALUES
(193, 'отвратительный пост', 66),
(194, 'ужасный пост', 66),
(195, 'хуже ничего в жизни не читал', 66),
(196, 'самый лучший пост в мире', 66);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(67, 'Пост 67', 'image67.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3', 'блог, программирование, котики, технологии', 84);

INSERT INTO comments (id, text, post_id) VALUES
(197, 'ужасный пост', 67),
(198, 'замечательный пост', 67);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(68, 'Пост 68', 'image68.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'жизнь, блог, программирование, тест, технологии', 16);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(69, 'Пост 69', 'image69.png', 'Абзац 1: Строка 1 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2', 'жизнь, блог, программирование, тест, технологии', 551);

INSERT INTO comments (id, text, post_id) VALUES
(199, 'замечательный пост', 69);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(70, 'Пост 70', 'image70.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3', 'блог, программирование, тест, технологии', 204);

INSERT INTO comments (id, text, post_id) VALUES
(200, 'замечательный пост', 70),
(201, 'замечательный пост', 70);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(71, 'Пост 71', 'image71.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'тест', 375);

INSERT INTO comments (id, text, post_id) VALUES
(202, 'замечательный пост', 71),
(203, 'хуже ничего в жизни не читал', 71);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(72, 'Пост 72', 'image72.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'жизнь, котики, технологии', 984);

INSERT INTO comments (id, text, post_id) VALUES
(204, 'отличный пост', 72),
(205, 'отвратительный пост', 72);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(73, 'Пост 73', 'image73.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 2: Строка 1', '', 753);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(74, 'Пост 74', 'image74.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'программирование, тест, котики', 690);

INSERT INTO comments (id, text, post_id) VALUES
(206, 'хуже ничего в жизни не читал', 74);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(75, 'Пост 75', 'image75.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'жизнь, блог, тест', 303);

INSERT INTO comments (id, text, post_id) VALUES
(207, 'отличный пост', 75);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(76, 'Пост 76', 'image76.png', 'Абзац 1: Строка 1 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'блог, программирование, тест, технологии', 100);

INSERT INTO comments (id, text, post_id) VALUES
(208, 'отвратительный пост', 76),
(209, 'замечательный пост', 76),
(210, 'замечательный пост', 76);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(77, 'Пост 77', 'image77.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3', '', 235);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(78, 'Пост 78', 'image78.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'жизнь, блог, котики', 877);

INSERT INTO comments (id, text, post_id) VALUES
(211, 'отвратительный пост', 78),
(212, 'хуже ничего в жизни не читал', 78);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(79, 'Пост 79', 'image79.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'блог, котики, тест', 768);

INSERT INTO comments (id, text, post_id) VALUES
(213, 'самый лучший пост в мире', 79),
(214, 'самый лучший пост в мире', 79),
(215, 'ужасный пост', 79);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(80, 'Пост 80', 'image80.png', 'Абзац 1: Строка 1 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'жизнь, блог, тест, котики, технологии', 477);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(81, 'Пост 81', 'image81.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2 \r\n Строка 3', 'жизнь, блог, программирование, технологии', 147);

INSERT INTO comments (id, text, post_id) VALUES
(216, 'ужасный пост', 81),
(217, 'отвратительный пост', 81),
(218, 'замечательный пост', 81),
(219, 'замечательный пост', 81);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(82, 'Пост 82', 'image82.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', '', 403);

INSERT INTO comments (id, text, post_id) VALUES
(220, 'отличный пост', 82),
(221, 'отличный пост', 82),
(222, 'ужасный пост', 82),
(223, 'замечательный пост', 82);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(83, 'Пост 83', 'image83.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2', 'блог, технологии', 171);

INSERT INTO comments (id, text, post_id) VALUES
(224, 'хуже ничего в жизни не читал', 83),
(225, 'ужасный пост', 83),
(226, 'хуже ничего в жизни не читал', 83),
(227, 'отвратительный пост', 83);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(84, 'Пост 84', 'image84.png', 'Абзац 1: Строка 1', 'жизнь, тест, технологии', 101);

INSERT INTO comments (id, text, post_id) VALUES
(228, 'ужасный пост', 84),
(229, 'замечательный пост', 84),
(230, 'самый лучший пост в мире', 84);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(85, 'Пост 85', 'image85.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'жизнь, блог, программирование, тест, котики', 425);

INSERT INTO comments (id, text, post_id) VALUES
(231, 'отличный пост', 85),
(232, 'хуже ничего в жизни не читал', 85),
(233, 'отвратительный пост', 85);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(86, 'Пост 86', 'image86.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2', 'программирование', 290);

INSERT INTO comments (id, text, post_id) VALUES
(234, 'ужасный пост', 86),
(235, 'отвратительный пост', 86),
(236, 'замечательный пост', 86),
(237, 'хуже ничего в жизни не читал', 86),
(238, 'отвратительный пост', 86);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(87, 'Пост 87', 'image87.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'блог, программирование, котики', 723);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(88, 'Пост 88', 'image88.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', '', 904);

INSERT INTO comments (id, text, post_id) VALUES
(239, 'замечательный пост', 88);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(89, 'Пост 89', 'image89.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2', 'жизнь, блог, программирование, тест', 634);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(90, 'Пост 90', 'image90.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', '', 352);

INSERT INTO comments (id, text, post_id) VALUES
(240, 'замечательный пост', 90),
(241, 'самый лучший пост в мире', 90),
(242, 'ужасный пост', 90),
(243, 'отвратительный пост', 90);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(91, 'Пост 91', 'image91.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5', 'жизнь, блог, программирование, котики, технологии', 940);

INSERT INTO comments (id, text, post_id) VALUES
(244, 'самый лучший пост в мире', 91);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(92, 'Пост 92', 'image92.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'блог, котики', 802);

INSERT INTO comments (id, text, post_id) VALUES
(245, 'отвратительный пост', 92),
(246, 'хуже ничего в жизни не читал', 92),
(247, 'самый лучший пост в мире', 92),
(248, 'замечательный пост', 92),
(249, 'самый лучший пост в мире', 92);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(93, 'Пост 93', 'image93.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 2: Строка 1 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2 \r\n Строка 3', 'жизнь, котики, технологии', 597);

INSERT INTO comments (id, text, post_id) VALUES
(250, 'хуже ничего в жизни не читал', 93),
(251, 'отличный пост', 93),
(252, 'ужасный пост', 93);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(94, 'Пост 94', 'image94.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 3: Строка 1', 'жизнь, блог, котики, технологии', 447);

INSERT INTO comments (id, text, post_id) VALUES
(253, 'замечательный пост', 94);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(95, 'Пост 95', 'image95.png', 'Абзац 1: Строка 1 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2', 'жизнь, блог, программирование, котики, тест', 770);

INSERT INTO comments (id, text, post_id) VALUES
(254, 'хуже ничего в жизни не читал', 95),
(255, 'замечательный пост', 95),
(256, 'отвратительный пост', 95),
(257, 'замечательный пост', 95);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(96, 'Пост 96', 'image96.png', 'Абзац 1: Строка 1', 'программирование, тест, котики', 176);

INSERT INTO comments (id, text, post_id) VALUES
(258, 'ужасный пост', 96),
(259, 'замечательный пост', 96);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(97, 'Пост 97', 'image97.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'блог, технологии', 130);

INSERT INTO comments (id, text, post_id) VALUES
(260, 'отличный пост', 97);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(98, 'Пост 98', 'image98.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 3: Строка 1', '', 793);

INSERT INTO comments (id, text, post_id) VALUES
(261, 'хуже ничего в жизни не читал', 98),
(262, 'отвратительный пост', 98);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(99, 'Пост 99', 'image99.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2', 'жизнь, блог, технологии', 816);

INSERT INTO comments (id, text, post_id) VALUES
(263, 'ужасный пост', 99),
(264, 'отвратительный пост', 99),
(265, 'замечательный пост', 99);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(100, 'Пост 100', 'image100.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'блог', 842);

INSERT INTO comments (id, text, post_id) VALUES
(266, 'отвратительный пост', 100);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(101, 'Пост 101', 'image101.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3', 'блог, котики, тест', 235);

INSERT INTO comments (id, text, post_id) VALUES
(267, 'ужасный пост', 101),
(268, 'ужасный пост', 101),
(269, 'замечательный пост', 101);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(102, 'Пост 102', 'image102.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n \r\n Абзац 2: Строка 1 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'жизнь, блог, технологии', 337);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(103, 'Пост 103', 'image103.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2', 'блог, программирование, тест, котики', 565);

INSERT INTO comments (id, text, post_id) VALUES
(270, 'самый лучший пост в мире', 103),
(271, 'отвратительный пост', 103);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(104, 'Пост 104', 'image104.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'программирование', 606);

INSERT INTO comments (id, text, post_id) VALUES
(272, 'хуже ничего в жизни не читал', 104),
(273, 'хуже ничего в жизни не читал', 104),
(274, 'хуже ничего в жизни не читал', 104),
(275, 'хуже ничего в жизни не читал', 104),
(276, 'хуже ничего в жизни не читал', 104);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(105, 'Пост 105', 'image105.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'жизнь, программирование, технологии', 658);

INSERT INTO comments (id, text, post_id) VALUES
(277, 'хуже ничего в жизни не читал', 105),
(278, 'отличный пост', 105),
(279, 'замечательный пост', 105),
(280, 'самый лучший пост в мире', 105),
(281, 'замечательный пост', 105);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(106, 'Пост 106', 'image106.png', 'Абзац 1: Строка 1 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2', 'блог, программирование, котики', 916);

INSERT INTO comments (id, text, post_id) VALUES
(282, 'отвратительный пост', 106);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(107, 'Пост 107', 'image107.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3', 'технологии', 655);

INSERT INTO comments (id, text, post_id) VALUES
(283, 'самый лучший пост в мире', 107),
(284, 'ужасный пост', 107),
(285, 'хуже ничего в жизни не читал', 107),
(286, 'замечательный пост', 107);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(108, 'Пост 108', 'image108.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', '', 578);

INSERT INTO comments (id, text, post_id) VALUES
(287, 'отвратительный пост', 108),
(288, 'замечательный пост', 108),
(289, 'самый лучший пост в мире', 108),
(290, 'хуже ничего в жизни не читал', 108),
(291, 'замечательный пост', 108);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(109, 'Пост 109', 'image109.png', 'Абзац 1: Строка 1 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2', 'блог, программирование, котики, тест', 234);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(110, 'Пост 110', 'image110.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1', 'блог', 786);

INSERT INTO comments (id, text, post_id) VALUES
(292, 'ужасный пост', 110),
(293, 'хуже ничего в жизни не читал', 110),
(294, 'хуже ничего в жизни не читал', 110),
(295, 'отвратительный пост', 110);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(111, 'Пост 111', 'image111.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n \r\n Абзац 2: Строка 1 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2 \r\n Строка 3', 'жизнь, блог', 745);

INSERT INTO comments (id, text, post_id) VALUES
(296, 'замечательный пост', 111),
(297, 'отвратительный пост', 111),
(298, 'ужасный пост', 111);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(112, 'Пост 112', 'image112.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'жизнь, блог, программирование, котики, технологии', 370);

INSERT INTO comments (id, text, post_id) VALUES
(299, 'хуже ничего в жизни не читал', 112),
(300, 'замечательный пост', 112),
(301, 'хуже ничего в жизни не читал', 112),
(302, 'хуже ничего в жизни не читал', 112);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(113, 'Пост 113', 'image113.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4 \r\n Строка 5 \r\n \r\n Абзац 2: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n \r\n Абзац 3: Строка 1 \r\n Строка 2', 'программирование, котики, тест, технологии', 304);

INSERT INTO comments (id, text, post_id) VALUES
(303, 'отличный пост', 113),
(304, 'хуже ничего в жизни не читал', 113),
(305, 'хуже ничего в жизни не читал', 113),
(306, 'замечательный пост', 113);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(114, 'Пост 114', 'image114.png', 'Абзац 1: Строка 1 \r\n Строка 2 \r\n Строка 3 \r\n Строка 4', 'жизнь, блог, программирование, котики', 650);

INSERT INTO comments (id, text, post_id) VALUES
(307, 'самый лучший пост в мире', 114);

INSERT INTO posts (id, title, image, text, tags, likes) VALUES
(115, 'Пост 115', 'image115.png', 'Абзац 1: Строка 1', 'блог, программирование, тест, котики, технологии', 884);

INSERT INTO comments (id, text, post_id) VALUES
(308, 'замечательный пост', 115),
(309, 'хуже ничего в жизни не читал', 115);