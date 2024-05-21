DROP DATABASE IF EXISTS ssafy_healing_forest;

CREATE DATABASE ssafy_healing_forest;

USE ssafy_healing_forest;

CREATE TABLE `user`(
	`user_id` varchar(100) NOT NULL PRIMARY KEY,
    `password` varchar(100) NOT NULL,
    `nickname` varchar(100) NOT NULL,
    `user_image_url` varchar(255) DEFAULT 'URL',
    `user_score` INT DEFAULT 0
);

CREATE TABLE `addiction_list`(
	`addiction_id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` varchar(100) NOT NULL,
	`title` varchar(100) NOT NULL,
    `start_time` TIMESTAMP DEFAULT now(),
    `target_time` INT NOT NULL,
    `end_time` TIMESTAMP NULL,
    `icon_path` varchar(255) NOT NULL,
    `addiction_score` INT DEFAULT 0,
	`success` BOOLEAN DEFAULT FALSE,
    CONSTRAINT `addiction_user_fk` FOREIGN KEY(`user_id`) REFERENCES `user`(`user_id`)
);

CREATE TABLE `badge`(
	`badge_id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` varchar(100) NOT NULL,
    `addiction_id` INT NOT NULL,
    `badge_date` INT NOT NULL,
    `badge_score` INT NOT NULL,
    `badge_img_url` varchar(255) NOT NULL,
    CONSTRAINT `badge_user_fk` FOREIGN KEY(`user_id`) REFERENCES `user`(`user_id`),
    CONSTRAINT `badge_addiction_fk` FOREIGN KEY(`addiction_id`) REFERENCES `addiction_list`(`addiction_id`)
    ON DELETE CASCADE
);

CREATE TABLE `comment`(
	`comment_id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` varchar(100) NOT NULL,
    `content` varchar(100) NOT NULL,
    `create_date` TIMESTAMP DEFAULT now(),
    CONSTRAINT `comment_user_fk` FOREIGN KEY(`user_id`) REFERENCES `user`(`user_id`)
);

-- dummy data

INSERT INTO `user` (user_id, password, nickname)
VALUES ('ssafy', 'ssafy', '싸피'),
		('ssafy2', 'ssafy2', '싸피2'),
        ('ssafy3', 'ssafy3', '싸피3');

INSERT INTO `addiction_list` (user_id, title, start_time, target_time, end_time, icon_path)
VALUES ('ssafy', '알코올','2024-05-10 09:00', 10, DATE_ADD(start_time, INTERVAL target_time DAY), '/src/assets/addiction/icons/Alcohol.png'),
		('ssafy', '담배','2024-05-12 09:00',  1, DATE_ADD(start_time, INTERVAL target_time DAY), '/src/assets/addiction/icons/Smoke.png'),
        ('ssafy2', '담배','2024-05-11 09:00',  2, DATE_ADD(start_time, INTERVAL target_time DAY), '/src/assets/addiction/icons/Smoke.png'),
        ('ssafy2', '핸드폰','2024-05-13 09:00',  5, DATE_ADD(start_time, INTERVAL target_time DAY), '/src/assets/addiction/icons/Phone.png');

-- INSERT INTO `badge` (user_id, addiction_id, badge_date, badge_score, badge_img_url)
-- VALUES ('ssafy', '1', 1, 10, 'URL'),
-- 		('ssafy', '1', 2, 20, 'URL'),
-- 		('ssafy', '1', 3, 30, 'URL'),
-- 		('ssafy', '1', 4, 40, 'URL'),
-- 		('ssafy', '1', 5, 50, 'URL'),
-- 		('ssafy', '1', 6, 60, 'URL'),
-- 		('ssafy', '1', 7, 70, 'URL'),
-- 		('ssafy', '1', 10, 100, 'URL'),
-- 		('ssafy', '1', 14, 140, 'URL'),
-- 		('ssafy', '1', 20, 200, 'URL'),
-- 		('ssafy', '1', 30, 300, 'URL'),
-- 		('ssafy', '1', 50, 500, 'URL'),
-- 		('ssafy', '1', 70, 700, 'URL'),
-- 		('ssafy', '1', 100, 1000, 'URL'),
-- 		('ssafy', '1', 20, 200, 'URL'),
-- 		('ssafy', '2', 1, 10, 'URL'),
-- 		('ssafy2', '3', 1, 10, 'URL');
            
INSERT INTO comment (user_id, content)
VALUES ('ssafy', 'test');

INSERT INTO `comment` (`user_id`, `content`, `create_date`) VALUES
('ssafy', 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX', '2024-05-21 07:28:04'),
('ssafy', 'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX', '2024-05-21 07:28:04'),
('ssafy', 'This is a much longer comment designed to fill the entire varchar limit of one hundred characters', '2024-05-21 07:28:04'),
('ssafy', 'This is a much longer comment designed to fill the entire varchar limit of one hundred characters. 1', '2024-05-21 07:28:04'),
('ssafy2', 'This is a test comment. Let''s see how this goes.', '2024-05-21 07:28:04'),
('ssafy2', 'This is a test comment. Let''s see how this goes.', '2024-05-21 07:28:04'),
('ssafy2', 'This is a much longer comment designed to fill the entire varchar limit of one hundred characters..', '2024-05-21 07:28:04'),
('ssafy3', 'Short comment.', '2024-05-21 07:28:04'),
('ssafy3', 'This is a much longer comment designed to fill the entire varchar limit of one hundred characters. 2', '2024-05-21 07:28:04'),
('ssafy3', 'Short comment.', '2024-05-21 07:28:04');


-- test code

SELECT * FROM user;
SELECT * FROM addiction_list;
SELECT * FROM badge;
SELECT * FROM comment;

-- SELECT * FROM badge
-- WHERE addiction_id = 1;

-- SELECT * 
-- FROM addiction_list AS a 
-- 	LEFT JOIN badge AS b 
-- 		ON a.user_id = b.user_id AND a.addiction_id = b.addiction_id
-- WHERE a.user_id = 'ssafy' AND a.addiction_id = 1 ;

-- UPDATE addiction_list
-- SET start_time = DATE_SUB(NOW(), INTERVAL 10 DAY)
-- WHERE user_id = 'ssafy' AND addiction_id = 1;

-- UPDATE addiction_list
-- SET start_time = DATE_SUB(NOW(), INTERVAL 5 DAY)
-- WHERE user_id = 'ssafy' AND addiction_id = 2;

-- SELECT * 
-- FROM user
-- ORDER BY user_score DESC 
-- LIMIT 3;
