---- 6 тестовых тегов

INSERT INTO tags (id, name) VALUES (1, 'технологии');
INSERT INTO tags (id, name) VALUES (2, 'блог');
INSERT INTO tags (id, name) VALUES (3, 'программирование');
INSERT INTO tags (id, name) VALUES (4, 'жизнь');
INSERT INTO tags (id, name) VALUES (5, 'тест');
INSERT INTO tags (id, name) VALUES (6, 'котики');

---- 115 тестовых постов

INSERT INTO posts (id, title, image, text, likes) VALUES
(1, 'Пост 1', 'image1.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 2: Строка 1<br>Строка 2</p>', 887);

INSERT INTO comments (id, text, post_id) VALUES
(1, 'хуже ничего в жизни не читал', 1);

INSERT INTO posts (id, title, image, text, likes) VALUES
(2, 'Пост 2', 'image2.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 97);

INSERT INTO comments (id, text, post_id) VALUES
(2, 'отличный пост', 2),
(3, 'замечательный пост', 2),
(4, 'хуже ничего в жизни не читал', 2);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(2, 5);

INSERT INTO posts (id, title, image, text, likes) VALUES
(3, 'Пост 3', 'image3.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 382);

INSERT INTO comments (id, text, post_id) VALUES
(5, 'отвратительный пост', 3),
(6, 'отличный пост', 3);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(3, 5),
(3, 1);

INSERT INTO posts (id, title, image, text, likes) VALUES
(4, 'Пост 4', 'image4.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 2: Строка 1<br>Строка 2</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3</p>', 692);

INSERT INTO comments (id, text, post_id) VALUES
(7, 'ужасный пост', 4),
(8, 'отличный пост', 4),
(9, 'замечательный пост', 4),
(10, 'отвратительный пост', 4),
(11, 'замечательный пост', 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(5, 'Пост 5', 'image5.png', '<p>Абзац 1: Строка 1<br>Строка 2</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 242);

INSERT INTO comments (id, text, post_id) VALUES
(12, 'отличный пост', 5),
(13, 'самый лучший пост в мире', 5),
(14, 'самый лучший пост в мире', 5),
(15, 'хуже ничего в жизни не читал', 5);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(5, 1),
(5, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(6, 'Пост 6', 'image6.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 500);

INSERT INTO comments (id, text, post_id) VALUES
(16, 'отличный пост', 6),
(17, 'отличный пост', 6);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(6, 4),
(6, 2);

INSERT INTO posts (id, title, image, text, likes) VALUES
(7, 'Пост 7', 'image7.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 362);

INSERT INTO comments (id, text, post_id) VALUES
(18, 'хуже ничего в жизни не читал', 7),
(19, 'отвратительный пост', 7),
(20, 'хуже ничего в жизни не читал', 7);

INSERT INTO posts (id, title, image, text, likes) VALUES
(8, 'Пост 8', 'image8.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1<br>Строка 2</p>', 566);

INSERT INTO comments (id, text, post_id) VALUES
(21, 'отвратительный пост', 8);

INSERT INTO posts (id, title, image, text, likes) VALUES
(9, 'Пост 9', 'image9.png', '<p>Абзац 1: Строка 1<br>Строка 2</p>', 388);

INSERT INTO comments (id, text, post_id) VALUES
(22, 'отличный пост', 9),
(23, 'хуже ничего в жизни не читал', 9),
(24, 'хуже ничего в жизни не читал', 9),
(25, 'замечательный пост', 9);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(9, 1),
(9, 4),
(9, 2);

INSERT INTO posts (id, title, image, text, likes) VALUES
(10, 'Пост 10', 'image10.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 2: Строка 1</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3</p>', 773);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(10, 2),
(10, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(11, 'Пост 11', 'image11.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p>', 490);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(11, 3),
(11, 6),
(11, 1),
(11, 2);

INSERT INTO posts (id, title, image, text, likes) VALUES
(12, 'Пост 12', 'image12.png', '<p>Абзац 1: Строка 1<br>Строка 2</p><p>Абзац 2: Строка 1</p><p>Абзац 3: Строка 1<br>Строка 2</p>', 567);

INSERT INTO comments (id, text, post_id) VALUES
(26, 'самый лучший пост в мире', 12),
(27, 'хуже ничего в жизни не читал', 12);

INSERT INTO posts (id, title, image, text, likes) VALUES
(13, 'Пост 13', 'image13.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 584);

INSERT INTO comments (id, text, post_id) VALUES
(28, 'замечательный пост', 13);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(13, 3),
(13, 2),
(13, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(14, 'Пост 14', 'image14.png', '<p>Абзац 1: Строка 1</p>', 351);

INSERT INTO comments (id, text, post_id) VALUES
(29, 'отвратительный пост', 14),
(30, 'хуже ничего в жизни не читал', 14);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(14, 1),
(14, 4),
(14, 5),
(14, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(15, 'Пост 15', 'image15.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 3: Строка 1</p>', 920);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(15, 5),
(15, 1),
(15, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(16, 'Пост 16', 'image16.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 300);

INSERT INTO comments (id, text, post_id) VALUES
(31, 'замечательный пост', 16),
(32, 'отличный пост', 16),
(33, 'отличный пост', 16),
(34, 'отличный пост', 16);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(16, 4),
(16, 2),
(16, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(17, 'Пост 17', 'image17.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 644);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(17, 1),
(17, 2),
(17, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(18, 'Пост 18', 'image18.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1</p>', 374);

INSERT INTO comments (id, text, post_id) VALUES
(35, 'ужасный пост', 18),
(36, 'замечательный пост', 18);

INSERT INTO posts (id, title, image, text, likes) VALUES
(19, 'Пост 19', 'image19.png', '<p>Абзац 1: Строка 1<br>Строка 2</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 272);

INSERT INTO comments (id, text, post_id) VALUES
(37, 'самый лучший пост в мире', 19);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(19, 2);

INSERT INTO posts (id, title, image, text, likes) VALUES
(20, 'Пост 20', 'image20.png', '<p>Абзац 1: Строка 1<br>Строка 2</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 3: Строка 1</p>', 417);

INSERT INTO comments (id, text, post_id) VALUES
(38, 'ужасный пост', 20),
(39, 'замечательный пост', 20),
(40, 'ужасный пост', 20),
(41, 'отвратительный пост', 20),
(42, 'ужасный пост', 20);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(20, 2),
(20, 3),
(20, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(21, 'Пост 21', 'image21.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1<br>Строка 2</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 999);

INSERT INTO comments (id, text, post_id) VALUES
(43, 'самый лучший пост в мире', 21),
(44, 'замечательный пост', 21),
(45, 'ужасный пост', 21),
(46, 'самый лучший пост в мире', 21),
(47, 'хуже ничего в жизни не читал', 21);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(21, 2),
(21, 4),
(21, 3),
(21, 1);

INSERT INTO posts (id, title, image, text, likes) VALUES
(22, 'Пост 22', 'image22.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 961);

INSERT INTO comments (id, text, post_id) VALUES
(48, 'ужасный пост', 22);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(22, 2),
(22, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(23, 'Пост 23', 'image23.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 345);

INSERT INTO comments (id, text, post_id) VALUES
(49, 'отличный пост', 23);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(23, 1),
(23, 6),
(23, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(24, 'Пост 24', 'image24.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1</p>', 99);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(24, 2),
(24, 6),
(24, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(25, 'Пост 25', 'image25.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 2: Строка 1<br>Строка 2</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 721);

INSERT INTO comments (id, text, post_id) VALUES
(50, 'ужасный пост', 25),
(51, 'отличный пост', 25),
(52, 'замечательный пост', 25);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(25, 5),
(25, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(26, 'Пост 26', 'image26.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 3: Строка 1<br>Строка 2</p>', 485);

INSERT INTO comments (id, text, post_id) VALUES
(53, 'хуже ничего в жизни не читал', 26);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(26, 1),
(26, 5);

INSERT INTO posts (id, title, image, text, likes) VALUES
(27, 'Пост 27', 'image27.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 3: Строка 1<br>Строка 2</p>', 93);

INSERT INTO comments (id, text, post_id) VALUES
(54, 'самый лучший пост в мире', 27);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(27, 5);

INSERT INTO posts (id, title, image, text, likes) VALUES
(28, 'Пост 28', 'image28.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 544);

INSERT INTO comments (id, text, post_id) VALUES
(55, 'ужасный пост', 28),
(56, 'самый лучший пост в мире', 28),
(57, 'ужасный пост', 28),
(58, 'ужасный пост', 28);

INSERT INTO posts (id, title, image, text, likes) VALUES
(29, 'Пост 29', 'image29.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3</p>', 689);

INSERT INTO comments (id, text, post_id) VALUES
(59, 'самый лучший пост в мире', 29),
(60, 'хуже ничего в жизни не читал', 29),
(61, 'отвратительный пост', 29);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(29, 6),
(29, 4),
(29, 2);

INSERT INTO posts (id, title, image, text, likes) VALUES
(30, 'Пост 30', 'image30.png', '<p>Абзац 1: Строка 1<br>Строка 2</p>', 956);

INSERT INTO comments (id, text, post_id) VALUES
(62, 'отвратительный пост', 30),
(63, 'самый лучший пост в мире', 30),
(64, 'самый лучший пост в мире', 30);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(30, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(31, 'Пост 31', 'image31.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 3: Строка 1</p>', 262);

INSERT INTO comments (id, text, post_id) VALUES
(65, 'хуже ничего в жизни не читал', 31);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(31, 6),
(31, 4),
(31, 2);

INSERT INTO posts (id, title, image, text, likes) VALUES
(32, 'Пост 32', 'image32.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 48);

INSERT INTO comments (id, text, post_id) VALUES
(66, 'отличный пост', 32);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(32, 6),
(32, 2),
(32, 1);

INSERT INTO posts (id, title, image, text, likes) VALUES
(33, 'Пост 33', 'image33.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 520);

INSERT INTO comments (id, text, post_id) VALUES
(67, 'самый лучший пост в мире', 33),
(68, 'самый лучший пост в мире', 33);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(33, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(34, 'Пост 34', 'image34.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 3: Строка 1<br>Строка 2</p>', 920);

INSERT INTO comments (id, text, post_id) VALUES
(69, 'ужасный пост', 34),
(70, 'отвратительный пост', 34),
(71, 'хуже ничего в жизни не читал', 34),
(72, 'ужасный пост', 34);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(34, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(35, 'Пост 35', 'image35.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 256);

INSERT INTO comments (id, text, post_id) VALUES
(73, 'самый лучший пост в мире', 35),
(74, 'самый лучший пост в мире', 35),
(75, 'самый лучший пост в мире', 35);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(35, 2),
(35, 3),
(35, 5);

INSERT INTO posts (id, title, image, text, likes) VALUES
(36, 'Пост 36', 'image36.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 908);

INSERT INTO comments (id, text, post_id) VALUES
(76, 'замечательный пост', 36);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(36, 2);

INSERT INTO posts (id, title, image, text, likes) VALUES
(37, 'Пост 37', 'image37.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 452);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(37, 2),
(37, 4),
(37, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(38, 'Пост 38', 'image38.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 303);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(38, 1),
(38, 3),
(38, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(39, 'Пост 39', 'image39.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p>', 226);

INSERT INTO comments (id, text, post_id) VALUES
(77, 'отличный пост', 39),
(78, 'замечательный пост', 39),
(79, 'ужасный пост', 39),
(80, 'замечательный пост', 39);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(39, 2),
(39, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(40, 'Пост 40', 'image40.png', '<p>Абзац 1: Строка 1<br>Строка 2</p>', 369);

INSERT INTO comments (id, text, post_id) VALUES
(81, 'отвратительный пост', 40),
(82, 'отвратительный пост', 40);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(40, 4),
(40, 2);

INSERT INTO posts (id, title, image, text, likes) VALUES
(41, 'Пост 41', 'image41.png', '<p>Абзац 1: Строка 1<br>Строка 2</p><p>Абзац 2: Строка 1</p><p>Абзац 3: Строка 1</p>', 798);

INSERT INTO comments (id, text, post_id) VALUES
(83, 'отвратительный пост', 41),
(84, 'замечательный пост', 41),
(85, 'самый лучший пост в мире', 41);

INSERT INTO posts (id, title, image, text, likes) VALUES
(42, 'Пост 42', 'image42.png', '<p>Абзац 1: Строка 1<br>Строка 2</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 3: Строка 1</p>', 753);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(42, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(43, 'Пост 43', 'image43.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 361);

INSERT INTO comments (id, text, post_id) VALUES
(86, 'замечательный пост', 43),
(87, 'отличный пост', 43);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(43, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(44, 'Пост 44', 'image44.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 79);

INSERT INTO comments (id, text, post_id) VALUES
(88, 'отвратительный пост', 44),
(89, 'отличный пост', 44),
(90, 'отвратительный пост', 44),
(91, 'самый лучший пост в мире', 44);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(44, 2),
(44, 1);

INSERT INTO posts (id, title, image, text, likes) VALUES
(45, 'Пост 45', 'image45.png', '<p>Абзац 1: Строка 1<br>Строка 2</p>', 359);

INSERT INTO comments (id, text, post_id) VALUES
(92, 'самый лучший пост в мире', 45),
(93, 'отвратительный пост', 45),
(94, 'замечательный пост', 45),
(95, 'хуже ничего в жизни не читал', 45);

INSERT INTO posts (id, title, image, text, likes) VALUES
(46, 'Пост 46', 'image46.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 501);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(46, 2),
(46, 5);

INSERT INTO posts (id, title, image, text, likes) VALUES
(47, 'Пост 47', 'image47.png', '<p>Абзац 1: Строка 1<br>Строка 2</p>', 543);

INSERT INTO comments (id, text, post_id) VALUES
(96, 'хуже ничего в жизни не читал', 47),
(97, 'самый лучший пост в мире', 47);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(47, 6),
(47, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(48, 'Пост 48', 'image48.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 230);

INSERT INTO comments (id, text, post_id) VALUES
(98, 'хуже ничего в жизни не читал', 48),
(99, 'отличный пост', 48);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(48, 3),
(48, 2),
(48, 4),
(48, 5);

INSERT INTO posts (id, title, image, text, likes) VALUES
(49, 'Пост 49', 'image49.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 3: Строка 1</p>', 168);

INSERT INTO comments (id, text, post_id) VALUES
(100, 'самый лучший пост в мире', 49),
(101, 'отвратительный пост', 49),
(102, 'замечательный пост', 49),
(103, 'отвратительный пост', 49);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(49, 5),
(49, 1);

INSERT INTO posts (id, title, image, text, likes) VALUES
(50, 'Пост 50', 'image50.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 976);

INSERT INTO posts (id, title, image, text, likes) VALUES
(51, 'Пост 51', 'image51.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p>', 743);

INSERT INTO comments (id, text, post_id) VALUES
(104, 'ужасный пост', 51);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(51, 2),
(51, 3),
(51, 5),
(51, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(52, 'Пост 52', 'image52.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 74);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(52, 4),
(52, 3),
(52, 5);

INSERT INTO posts (id, title, image, text, likes) VALUES
(53, 'Пост 53', 'image53.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 348);

INSERT INTO comments (id, text, post_id) VALUES
(105, 'ужасный пост', 53),
(106, 'отличный пост', 53);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(53, 1),
(53, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(54, 'Пост 54', 'image54.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 2: Строка 1</p><p>Абзац 3: Строка 1<br>Строка 2</p>', 370);

INSERT INTO comments (id, text, post_id) VALUES
(107, 'ужасный пост', 54),
(108, 'ужасный пост', 54);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(54, 2),
(54, 1),
(54, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(55, 'Пост 55', 'image55.png', '<p>Абзац 1: Строка 1</p>', 459);

INSERT INTO comments (id, text, post_id) VALUES
(109, 'отвратительный пост', 55),
(110, 'отличный пост', 55),
(111, 'хуже ничего в жизни не читал', 55),
(112, 'хуже ничего в жизни не читал', 55);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(55, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(56, 'Пост 56', 'image56.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 2: Строка 1</p>', 812);

INSERT INTO comments (id, text, post_id) VALUES
(113, 'замечательный пост', 56),
(114, 'замечательный пост', 56);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(56, 3),
(56, 4),
(56, 1);

INSERT INTO posts (id, title, image, text, likes) VALUES
(57, 'Пост 57', 'image57.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 832);

INSERT INTO comments (id, text, post_id) VALUES
(115, 'ужасный пост', 57),
(116, 'хуже ничего в жизни не читал', 57),
(117, 'отличный пост', 57),
(118, 'самый лучший пост в мире', 57),
(119, 'ужасный пост', 57);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(57, 3),
(57, 6),
(57, 2);

INSERT INTO posts (id, title, image, text, likes) VALUES
(58, 'Пост 58', 'image58.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1</p><p>Абзац 3: Строка 1<br>Строка 2</p>', 84);

INSERT INTO comments (id, text, post_id) VALUES
(120, 'хуже ничего в жизни не читал', 58),
(121, 'отличный пост', 58),
(122, 'самый лучший пост в мире', 58);

INSERT INTO posts (id, title, image, text, likes) VALUES
(59, 'Пост 59', 'image59.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 346);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(59, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(60, 'Пост 60', 'image60.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 844);

INSERT INTO comments (id, text, post_id) VALUES
(123, 'хуже ничего в жизни не читал', 60),
(124, 'хуже ничего в жизни не читал', 60),
(125, 'самый лучший пост в мире', 60),
(126, 'хуже ничего в жизни не читал', 60),
(127, 'отличный пост', 60);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(60, 3),
(60, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(61, 'Пост 61', 'image61.png', '<p>Абзац 1: Строка 1<br>Строка 2</p><p>Абзац 2: Строка 1</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 618);

INSERT INTO comments (id, text, post_id) VALUES
(128, 'замечательный пост', 61);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(61, 2),
(61, 4),
(61, 1);

INSERT INTO posts (id, title, image, text, likes) VALUES
(62, 'Пост 62', 'image62.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p>', 628);

INSERT INTO comments (id, text, post_id) VALUES
(129, 'самый лучший пост в мире', 62);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(62, 5),
(62, 1),
(62, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(63, 'Пост 63', 'image63.png', '<p>Абзац 1: Строка 1</p>', 628);

INSERT INTO comments (id, text, post_id) VALUES
(130, 'отвратительный пост', 63);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(63, 5),
(63, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(64, 'Пост 64', 'image64.png', '<p>Абзац 1: Строка 1<br>Строка 2</p><p>Абзац 2: Строка 1<br>Строка 2</p>', 365);

INSERT INTO comments (id, text, post_id) VALUES
(131, 'самый лучший пост в мире', 64),
(132, 'отвратительный пост', 64),
(133, 'замечательный пост', 64),
(134, 'ужасный пост', 64);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(64, 5),
(64, 1),
(64, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(65, 'Пост 65', 'image65.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 740);

INSERT INTO comments (id, text, post_id) VALUES
(135, 'замечательный пост', 65),
(136, 'отличный пост', 65);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(65, 6),
(65, 3),
(65, 4),
(65, 5);

INSERT INTO posts (id, title, image, text, likes) VALUES
(66, 'Пост 66', 'image66.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 878);

INSERT INTO comments (id, text, post_id) VALUES
(137, 'хуже ничего в жизни не читал', 66),
(138, 'отвратительный пост', 66),
(139, 'ужасный пост', 66),
(140, 'ужасный пост', 66),
(141, 'замечательный пост', 66);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(66, 5),
(66, 4),
(66, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(67, 'Пост 67', 'image67.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 766);

INSERT INTO comments (id, text, post_id) VALUES
(142, 'замечательный пост', 67),
(143, 'хуже ничего в жизни не читал', 67);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(67, 6),
(67, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(68, 'Пост 68', 'image68.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 581);

INSERT INTO comments (id, text, post_id) VALUES
(144, 'замечательный пост', 68),
(145, 'отличный пост', 68),
(146, 'самый лучший пост в мире', 68),
(147, 'отличный пост', 68),
(148, 'самый лучший пост в мире', 68);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(68, 1);

INSERT INTO posts (id, title, image, text, likes) VALUES
(69, 'Пост 69', 'image69.png', '<p>Абзац 1: Строка 1</p>', 42);

INSERT INTO comments (id, text, post_id) VALUES
(149, 'ужасный пост', 69),
(150, 'замечательный пост', 69),
(151, 'отличный пост', 69);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(69, 2);

INSERT INTO posts (id, title, image, text, likes) VALUES
(70, 'Пост 70', 'image70.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 2: Строка 1</p><p>Абзац 3: Строка 1<br>Строка 2</p>', 132);

INSERT INTO comments (id, text, post_id) VALUES
(152, 'отвратительный пост', 70),
(153, 'самый лучший пост в мире', 70);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(70, 6),
(70, 2),
(70, 1),
(70, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(71, 'Пост 71', 'image71.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p>', 319);

INSERT INTO comments (id, text, post_id) VALUES
(154, 'хуже ничего в жизни не читал', 71),
(155, 'отвратительный пост', 71),
(156, 'отвратительный пост', 71),
(157, 'отличный пост', 71);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(71, 5),
(71, 2),
(71, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(72, 'Пост 72', 'image72.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 666);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(72, 6),
(72, 5),
(72, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(73, 'Пост 73', 'image73.png', '<p>Абзац 1: Строка 1</p>', 767);

INSERT INTO comments (id, text, post_id) VALUES
(158, 'отвратительный пост', 73),
(159, 'хуже ничего в жизни не читал', 73);

INSERT INTO posts (id, title, image, text, likes) VALUES
(74, 'Пост 74', 'image74.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 815);

INSERT INTO comments (id, text, post_id) VALUES
(160, 'хуже ничего в жизни не читал', 74),
(161, 'отличный пост', 74),
(162, 'отличный пост', 74),
(163, 'ужасный пост', 74);

INSERT INTO posts (id, title, image, text, likes) VALUES
(75, 'Пост 75', 'image75.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 653);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(75, 5);

INSERT INTO posts (id, title, image, text, likes) VALUES
(76, 'Пост 76', 'image76.png', '<p>Абзац 1: Строка 1<br>Строка 2</p>', 961);

INSERT INTO comments (id, text, post_id) VALUES
(164, 'отвратительный пост', 76),
(165, 'отвратительный пост', 76),
(166, 'самый лучший пост в мире', 76),
(167, 'замечательный пост', 76);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(76, 3),
(76, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(77, 'Пост 77', 'image77.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 151);

INSERT INTO comments (id, text, post_id) VALUES
(168, 'замечательный пост', 77),
(169, 'отличный пост', 77),
(170, 'самый лучший пост в мире', 77),
(171, 'отличный пост', 77);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(77, 4),
(77, 1),
(77, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(78, 'Пост 78', 'image78.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1<br>Строка 2</p>', 107);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(78, 5),
(78, 2),
(78, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(79, 'Пост 79', 'image79.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1<br>Строка 2</p>', 988);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(79, 2);

INSERT INTO posts (id, title, image, text, likes) VALUES
(80, 'Пост 80', 'image80.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 2: Строка 1</p>', 252);

INSERT INTO comments (id, text, post_id) VALUES
(172, 'замечательный пост', 80),
(173, 'самый лучший пост в мире', 80),
(174, 'отличный пост', 80);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(80, 3),
(80, 2),
(80, 4),
(80, 5);

INSERT INTO posts (id, title, image, text, likes) VALUES
(81, 'Пост 81', 'image81.png', '<p>Абзац 1: Строка 1<br>Строка 2</p>', 243);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(81, 4),
(81, 1);

INSERT INTO posts (id, title, image, text, likes) VALUES
(82, 'Пост 82', 'image82.png', '<p>Абзац 1: Строка 1</p>', 346);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(82, 5),
(82, 2);

INSERT INTO posts (id, title, image, text, likes) VALUES
(83, 'Пост 83', 'image83.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 120);

INSERT INTO comments (id, text, post_id) VALUES
(175, 'хуже ничего в жизни не читал', 83),
(176, 'ужасный пост', 83),
(177, 'самый лучший пост в мире', 83),
(178, 'замечательный пост', 83);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(83, 6),
(83, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(84, 'Пост 84', 'image84.png', '<p>Абзац 1: Строка 1<br>Строка 2</p><p>Абзац 2: Строка 1</p>', 842);

INSERT INTO comments (id, text, post_id) VALUES
(179, 'ужасный пост', 84),
(180, 'отличный пост', 84),
(181, 'отличный пост', 84),
(182, 'самый лучший пост в мире', 84);

INSERT INTO posts (id, title, image, text, likes) VALUES
(85, 'Пост 85', 'image85.png', '<p>Абзац 1: Строка 1</p>', 972);

INSERT INTO comments (id, text, post_id) VALUES
(183, 'ужасный пост', 85),
(184, 'отличный пост', 85);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(85, 5),
(85, 4),
(85, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(86, 'Пост 86', 'image86.png', '<p>Абзац 1: Строка 1<br>Строка 2</p>', 776);

INSERT INTO comments (id, text, post_id) VALUES
(185, 'отвратительный пост', 86),
(186, 'хуже ничего в жизни не читал', 86),
(187, 'хуже ничего в жизни не читал', 86);

INSERT INTO posts (id, title, image, text, likes) VALUES
(87, 'Пост 87', 'image87.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3</p>', 890);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(87, 2),
(87, 4),
(87, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(88, 'Пост 88', 'image88.png', '<p>Абзац 1: Строка 1<br>Строка 2</p><p>Абзац 2: Строка 1<br>Строка 2</p>', 627);

INSERT INTO comments (id, text, post_id) VALUES
(188, 'отличный пост', 88),
(189, 'отвратительный пост', 88);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(88, 2),
(88, 1),
(88, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(89, 'Пост 89', 'image89.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 701);

INSERT INTO comments (id, text, post_id) VALUES
(190, 'ужасный пост', 89),
(191, 'хуже ничего в жизни не читал', 89),
(192, 'отличный пост', 89);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(89, 5),
(89, 3),
(89, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(90, 'Пост 90', 'image90.png', '<p>Абзац 1: Строка 1<br>Строка 2</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 580);

INSERT INTO comments (id, text, post_id) VALUES
(193, 'самый лучший пост в мире', 90),
(194, 'отвратительный пост', 90);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(90, 4),
(90, 5),
(90, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(91, 'Пост 91', 'image91.png', '<p>Абзац 1: Строка 1<br>Строка 2</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3</p>', 12);

INSERT INTO comments (id, text, post_id) VALUES
(195, 'замечательный пост', 91),
(196, 'ужасный пост', 91),
(197, 'замечательный пост', 91),
(198, 'отвратительный пост', 91),
(199, 'замечательный пост', 91);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(91, 2),
(91, 5),
(91, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(92, 'Пост 92', 'image92.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 480);

INSERT INTO comments (id, text, post_id) VALUES
(200, 'ужасный пост', 92),
(201, 'замечательный пост', 92),
(202, 'ужасный пост', 92),
(203, 'ужасный пост', 92);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(92, 4),
(92, 5),
(92, 1),
(92, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(93, 'Пост 93', 'image93.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 77);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(93, 5),
(93, 1),
(93, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(94, 'Пост 94', 'image94.png', '<p>Абзац 1: Строка 1<br>Строка 2</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3</p>', 18);

INSERT INTO comments (id, text, post_id) VALUES
(204, 'замечательный пост', 94),
(205, 'замечательный пост', 94),
(206, 'самый лучший пост в мире', 94),
(207, 'хуже ничего в жизни не читал', 94);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(94, 6),
(94, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(95, 'Пост 95', 'image95.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3</p>', 774);

INSERT INTO comments (id, text, post_id) VALUES
(208, 'хуже ничего в жизни не читал', 95);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(95, 5);

INSERT INTO posts (id, title, image, text, likes) VALUES
(96, 'Пост 96', 'image96.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3</p>', 991);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(96, 1),
(96, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(97, 'Пост 97', 'image97.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p>', 135);

INSERT INTO comments (id, text, post_id) VALUES
(209, 'самый лучший пост в мире', 97);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(97, 1),
(97, 2);

INSERT INTO posts (id, title, image, text, likes) VALUES
(98, 'Пост 98', 'image98.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3</p>', 404);

INSERT INTO comments (id, text, post_id) VALUES
(210, 'отличный пост', 98),
(211, 'отличный пост', 98);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(98, 2),
(98, 6);

INSERT INTO posts (id, title, image, text, likes) VALUES
(99, 'Пост 99', 'image99.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 3: Строка 1<br>Строка 2</p>', 100);

INSERT INTO comments (id, text, post_id) VALUES
(212, 'хуже ничего в жизни не читал', 99),
(213, 'самый лучший пост в мире', 99),
(214, 'отвратительный пост', 99),
(215, 'отвратительный пост', 99),
(216, 'ужасный пост', 99);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(99, 4),
(99, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(100, 'Пост 100', 'image100.png', '<p>Абзац 1: Строка 1</p>', 69);

INSERT INTO comments (id, text, post_id) VALUES
(217, 'отвратительный пост', 100),
(218, 'самый лучший пост в мире', 100),
(219, 'замечательный пост', 100),
(220, 'самый лучший пост в мире', 100);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(100, 5);

INSERT INTO posts (id, title, image, text, likes) VALUES
(101, 'Пост 101', 'image101.png', '<p>Абзац 1: Строка 1<br>Строка 2</p>', 403);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(101, 5);

INSERT INTO posts (id, title, image, text, likes) VALUES
(102, 'Пост 102', 'image102.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 9);

INSERT INTO comments (id, text, post_id) VALUES
(221, 'замечательный пост', 102);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(102, 4),
(102, 1);

INSERT INTO posts (id, title, image, text, likes) VALUES
(103, 'Пост 103', 'image103.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p>', 632);

INSERT INTO comments (id, text, post_id) VALUES
(222, 'самый лучший пост в мире', 103),
(223, 'отличный пост', 103);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(103, 6),
(103, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(104, 'Пост 104', 'image104.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1</p>', 165);

INSERT INTO comments (id, text, post_id) VALUES
(224, 'замечательный пост', 104),
(225, 'замечательный пост', 104),
(226, 'отвратительный пост', 104),
(227, 'ужасный пост', 104);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(104, 2),
(104, 3),
(104, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(105, 'Пост 105', 'image105.png', '<p>Абзац 1: Строка 1<br>Строка 2</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 770);

INSERT INTO comments (id, text, post_id) VALUES
(228, 'замечательный пост', 105),
(229, 'самый лучший пост в мире', 105),
(230, 'ужасный пост', 105),
(231, 'хуже ничего в жизни не читал', 105);

INSERT INTO posts (id, title, image, text, likes) VALUES
(106, 'Пост 106', 'image106.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 309);

INSERT INTO posts (id, title, image, text, likes) VALUES
(107, 'Пост 107', 'image107.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3</p>', 785);

INSERT INTO comments (id, text, post_id) VALUES
(232, 'отвратительный пост', 107),
(233, 'отвратительный пост', 107),
(234, 'ужасный пост', 107),
(235, 'замечательный пост', 107),
(236, 'ужасный пост', 107);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(107, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(108, 'Пост 108', 'image108.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 966);

INSERT INTO comments (id, text, post_id) VALUES
(237, 'отвратительный пост', 108);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(108, 5),
(108, 2),
(108, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(109, 'Пост 109', 'image109.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 2: Строка 1</p>', 577);

INSERT INTO comments (id, text, post_id) VALUES
(238, 'замечательный пост', 109),
(239, 'отвратительный пост', 109);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(109, 2),
(109, 5);

INSERT INTO posts (id, title, image, text, likes) VALUES
(110, 'Пост 110', 'image110.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p><p>Абзац 2: Строка 1<br>Строка 2</p>', 197);

INSERT INTO comments (id, text, post_id) VALUES
(240, 'ужасный пост', 110);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(110, 4),
(110, 3);

INSERT INTO posts (id, title, image, text, likes) VALUES
(111, 'Пост 111', 'image111.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 2);

INSERT INTO comments (id, text, post_id) VALUES
(241, 'отвратительный пост', 111),
(242, 'замечательный пост', 111);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(111, 1);

INSERT INTO posts (id, title, image, text, likes) VALUES
(112, 'Пост 112', 'image112.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3</p><p>Абзац 3: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 534);

INSERT INTO comments (id, text, post_id) VALUES
(243, 'замечательный пост', 112);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(112, 5),
(112, 2),
(112, 1),
(112, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(113, 'Пост 113', 'image113.png', '<p>Абзац 1: Строка 1<br>Строка 2</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4</p>', 74);

INSERT INTO comments (id, text, post_id) VALUES
(244, 'замечательный пост', 113);

INSERT INTO posts (id, title, image, text, likes) VALUES
(114, 'Пост 114', 'image114.png', '<p>Абзац 1: Строка 1</p><p>Абзац 2: Строка 1</p>', 744);

INSERT INTO comments (id, text, post_id) VALUES
(245, 'ужасный пост', 114),
(246, 'ужасный пост', 114);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(114, 2),
(114, 3),
(114, 5),
(114, 4);

INSERT INTO posts (id, title, image, text, likes) VALUES
(115, 'Пост 115', 'image115.png', '<p>Абзац 1: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p><p>Абзац 2: Строка 1<br>Строка 2<br>Строка 3<br>Строка 4<br>Строка 5</p>', 585);

INSERT INTO posts_tags (post_id, tag_id) VALUES
(115, 4),
(115, 2),
(115, 1);

---- Обновление счетчиков автоинкрементных полей

ALTER TABLE posts ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM posts);
ALTER TABLE tags ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM tags);
ALTER TABLE comments ALTER COLUMN id RESTART WITH (SELECT MAX(id) + 1 FROM comments);