DROP DATABASE IF EXISTS ssafy_healing_forest;

CREATE DATABASE ssafy_healing_forest;

USE ssafy_healing_forest;

CREATE TABLE `user`(
	`user_id` varchar(100) NOT NULL PRIMARY KEY,
    `password` varchar(100) NOT NULL,
    `nickname` varchar(100) NOT NULL,
    `user_image_url` varchar(255) DEFAULT 'URL',
    `user_score` int DEFAULT 0
);

CREATE TABLE `addiction_list`(
	`item_id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` varchar(100) NOT NULL,
	`title` varchar(100) NOT NULL,
    `start_time` TIMESTAMP DEFAULT now(),
    `target_time` INT NOT NULL,
    `item_score` INT DEFAULT 0,
    `badge1` BOOLEAN DEFAULT FALSE,
    `badge2` BOOLEAN DEFAULT FALSE,
    `badge3` BOOLEAN DEFAULT FALSE,
    `badge4` BOOLEAN DEFAULT FALSE,
    `badge5` BOOLEAN DEFAULT FALSE,
    `badge6` BOOLEAN DEFAULT FALSE,
    `badge7` BOOLEAN DEFAULT FALSE,
    CONSTRAINT `user_fk` FOREIGN KEY(`user_id`) REFERENCES `user`(`user_id`)
);

CREATE TABLE `badge`(
	`badge_name` varchar(50) NOT NULL PRIMARY KEY,
    `badge_score` int NOT NULL,
    `badge_img_url` varchar(255) NOT NULL
);

INSERT INTO `badge`
	VALUES ('badge1', 10, 'url1'), 
			('badge2', 20, 'url2'), 
			('badge3', 30, 'url3'), 
			('badge4', 40, 'url4'),
            ('badge5', 50, 'url5'),
            ('badge6', 60, 'url6'),
            ('badge7', 70, 'url7');
            
SELECT * FROM user;
SELECT * FROM addiction_list;
SELECT * FROM badge;
            
