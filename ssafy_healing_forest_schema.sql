DROP DATABASE IF EXISTS ssafy_healing_forest;

CREATE DATABASE ssafy_healing_forest;

USE ssafy_healing_forest;

CREATE TABLE `user`(
	`user_id` varchar(20) NOT NULL PRIMARY KEY,
    `password` varchar(30) NOT NULL,
    `nickname` varchar(15) NOT NULL,
    `user_image_url` varchar(255) DEFAULT 'URL',
    `user_score` INT DEFAULT 0
);

CREATE TABLE `addiction_list`(
	`addiction_id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` varchar(100) NOT NULL,
	`title` varchar(30) NOT NULL,
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
    `nickname` varchar(100) NOT NULL,
    `content` varchar(100) NOT NULL,
    `create_date` TIMESTAMP DEFAULT now(),
    CONSTRAINT `comment_user_fk` FOREIGN KEY(`user_id`) REFERENCES `user`(`user_id`)
);

-- dummy data

INSERT INTO `user` (user_id, password, nickname, user_score) VALUES
('ssafy', 'asdf1234%', '웃는고래', 3380),
('ssafy2', 'asdf1234%', '똑똑한여우', 1620),
('ssafy3', 'asdf1234%', '날아라호랑이', 1100),
('ssafy4', 'asdf1234%', '희망찬아침', FLOOR(RAND() * 1051)),
('ssafy5', 'asdf1234%', '행복한나무', FLOOR(RAND() * 1051)),
('ssafy6', 'asdf1234%', '용감한심장', FLOOR(RAND() * 1051)),
('ssafy7', 'asdf1234%', '기운찬호랑이', FLOOR(RAND() * 1051)),
('ssafy8', 'asdf1234%', '자유로운새', FLOOR(RAND() * 1051)),
('ssafy9', 'asdf1234%', '열정적인불꽃', FLOOR(RAND() * 1051)),
('ssafy10', 'asdf1234%', '평온한바다', FLOOR(RAND() * 1051)),
('ssafy11', 'asdf1234%', '밝은햇살', FLOOR(RAND() * 1051)),
('ssafy12', 'asdf1234%', '신나는바람', FLOOR(RAND() * 1051)),
('ssafy13', 'asdf1234%', '우아한나비', FLOOR(RAND() * 1051)),
('ssafy14', 'asdf1234%', '명랑한돌고래', FLOOR(RAND() * 1051)),
('ssafy15', 'asdf1234%', '용맹한사자', FLOOR(RAND() * 1051)),
('ssafy16', 'asdf1234%', '순수한꽃', FLOOR(RAND() * 1051)),
('ssafy17', 'asdf1234%', '차분한나무', FLOOR(RAND() * 1051)),
('ssafy18', 'asdf1234%', '열정적인호랑이', FLOOR(RAND() * 1051)),
('ssafy19', 'asdf1234%', '창의적인여우', FLOOR(RAND() * 1051)),
('ssafy20', 'asdf1234%', '상냥한고양이', FLOOR(RAND() * 1051)),
('ssafy21', 'asdf1234%', '화사한장미', FLOOR(RAND() * 1051)),
('ssafy22', 'asdf1234%', '도전적인독수리', FLOOR(RAND() * 1051)),
('ssafy23', 'asdf1234%', '지혜로운부엉이', FLOOR(RAND() * 1051)),
('ssafy24', 'asdf1234%', '강인한곰', FLOOR(RAND() * 1051)),
('ssafy25', 'asdf1234%', '활기찬사슴', FLOOR(RAND() * 1051)),
('ssafy26', 'asdf1234%', '용감한독수리', FLOOR(RAND() * 1051)),
('ssafy27', 'asdf1234%', '즐거운너구리', FLOOR(RAND() * 1051)),
('ssafy28', 'asdf1234%', '상냥한다람쥐', FLOOR(RAND() * 1051)),
('ssafy29', 'asdf1234%', '지혜로운올빼미', FLOOR(RAND() * 1051)),
('ssafy30', 'asdf1234%', '용맹한호랑이', FLOOR(RAND() * 1051)),
('ssafy31', 'asdf1234%', '밝은햇빛', FLOOR(RAND() * 1051)),
('ssafy32', 'asdf1234%', '활기찬물개', FLOOR(RAND() * 1051)),
('ssafy33', 'asdf1234%', '명랑한기린', FLOOR(RAND() * 1051)),
('ssafy34', 'asdf1234%', '기운찬코끼리', FLOOR(RAND() * 1051)),
('ssafy35', 'asdf1234%', '용맹한토끼', FLOOR(RAND() * 1051)),
('ssafy36', 'asdf1234%', '희망찬하늘', FLOOR(RAND() * 1051)),
('ssafy37', 'asdf1234%', '행복한산', FLOOR(RAND() * 1051)),
('ssafy38', 'asdf1234%', '활기찬바람', FLOOR(RAND() * 1051)),
('ssafy39', 'asdf1234%', '용감한물고기', FLOOR(RAND() * 1051)),
('ssafy40', 'asdf1234%', '밝은하늘', FLOOR(RAND() * 1051)),
('ssafy41', 'asdf1234%', '상쾌한바람', FLOOR(RAND() * 1051)),
('ssafy42', 'asdf1234%', '따뜻한햇살', FLOOR(RAND() * 1051)),
('ssafy43', 'asdf1234%', '빛나는별', FLOOR(RAND() * 1051)),
('ssafy44', 'asdf1234%', '활발한사자', FLOOR(RAND() * 1051)),
('ssafy45', 'asdf1234%', '싱그러운나무', FLOOR(RAND() * 1051)),
('ssafy46', 'asdf1234%', '온화한햇빛', FLOOR(RAND() * 1051)),
('ssafy47', 'asdf1234%', '평화로운물결', FLOOR(RAND() * 1051)),
('ssafy48', 'asdf1234%', '따뜻한기운', FLOOR(RAND() * 1051)),
('ssafy49', 'asdf1234%', '기분좋은날', FLOOR(RAND() * 1051)),
('ssafy50', 'asdf1234%', '산뜻한바람', FLOOR(RAND() * 1051)),
('ssafy51', 'asdf1234%', '밝은미소', FLOOR(RAND() * 1051)),
('ssafy52', 'asdf1234%', '찬란한햇살', FLOOR(RAND() * 1051)),
('ssafy53', 'asdf1234%', '순수한하늘', FLOOR(RAND() * 1051)),
('ssafy54', 'asdf1234%', '맑은바다', FLOOR(RAND() * 1051)),
('ssafy55', 'asdf1234%', '행복한하루', FLOOR(RAND() * 1051)),
('ssafy56', 'asdf1234%', '상쾌한날씨', FLOOR(RAND() * 1051)),
('ssafy57', 'asdf1234%', '밝은희망', FLOOR(RAND() * 1051)),
('ssafy58', 'asdf1234%', '유쾌한모임', FLOOR(RAND() * 1051)),
('ssafy59', 'asdf1234%', '따뜻한미소', FLOOR(RAND() * 1051)),
('ssafy60', 'asdf1234%', '편안한쉼터', FLOOR(RAND() * 1051)),
('ssafy61', 'asdf1234%', '활기찬아침', FLOOR(RAND() * 1051)),
('ssafy62', 'asdf1234%', '즐거운산책', FLOOR(RAND() * 1051)),
('ssafy63', 'asdf1234%', '행복한미소', FLOOR(RAND() * 1051)),
('ssafy64', 'asdf1234%', '평화로운밤', FLOOR(RAND() * 1051)),
('ssafy65', 'asdf1234%', '따뜻한커피', FLOOR(RAND() * 1051)),
('ssafy66', 'asdf1234%', '맑은공기', FLOOR(RAND() * 1051)),
('ssafy67', 'asdf1234%', '신선한과일', FLOOR(RAND() * 1051)),
('ssafy68', 'asdf1234%', '밝은미래', FLOOR(RAND() * 1051)),
('ssafy69', 'asdf1234%', '행복한기억', FLOOR(RAND() * 1051)),
('ssafy70', 'asdf1234%', '활기찬하루', FLOOR(RAND() * 1051)),
('ssafy71', 'asdf1234%', '건강한습관', FLOOR(RAND() * 1051)),
('ssafy72', 'asdf1234%', '따뜻한말', FLOOR(RAND() * 1051)),
('ssafy73', 'asdf1234%', '순수한웃음', FLOOR(RAND() * 1051)),
('ssafy74', 'asdf1234%', '밝은길', FLOOR(RAND() * 1051)),
('ssafy75', 'asdf1234%', '활기찬노래', FLOOR(RAND() * 1051)),
('ssafy76', 'asdf1234%', '행복한기분', FLOOR(RAND() * 1051)),
('ssafy77', 'asdf1234%', '따뜻한차', FLOOR(RAND() * 1051)),
('ssafy78', 'asdf1234%', '맑은날씨', FLOOR(RAND() * 1051)),
('ssafy79', 'asdf1234%', '유쾌한시간', FLOOR(RAND() * 1051)),
('ssafy80', 'asdf1234%', '밝은아침', FLOOR(RAND() * 1051)),
('ssafy81', 'asdf1234%', '상쾌한하루', FLOOR(RAND() * 1051)),
('ssafy82', 'asdf1234%', '편안한밤', FLOOR(RAND() * 1051)),
('ssafy83', 'asdf1234%', '행복한시간', FLOOR(RAND() * 1051)),
('ssafy84', 'asdf1234%', '맑은하늘', FLOOR(RAND() * 1051)),
('ssafy85', 'asdf1234%', '유쾌한웃음', FLOOR(RAND() * 1051)),
('ssafy86', 'asdf1234%', '따뜻한햇빛', FLOOR(RAND() * 1051)),
('ssafy87', 'asdf1234%', '활기찬운동', FLOOR(RAND() * 1051)),
('ssafy88', 'asdf1234%', '상쾌한공기', FLOOR(RAND() * 1051)),
('ssafy89', 'asdf1234%', '밝은햇살', FLOOR(RAND() * 1051)),
('ssafy90', 'asdf1234%', '즐거운시간', FLOOR(RAND() * 1051)),
('ssafy91', 'asdf1234%', '따뜻한하루', FLOOR(RAND() * 1051)),
('ssafy92', 'asdf1234%', '맑은물', FLOOR(RAND() * 1051)),
('ssafy93', 'asdf1234%', '유쾌한하루', FLOOR(RAND() * 1051)),
('ssafy94', 'asdf1234%', '밝은기분', FLOOR(RAND() * 1051)),
('ssafy95', 'asdf1234%', '활기찬시간', FLOOR(RAND() * 1051)),
('ssafy96', 'asdf1234%', '행복한밤', FLOOR(RAND() * 1051)),
('ssafy97', 'asdf1234%', '상쾌한아침', FLOOR(RAND() * 1051)),
('ssafy98', 'asdf1234%', '따뜻한저녁', FLOOR(RAND() * 1051)),
('ssafy99', 'asdf1234%', '맑은하루', FLOOR(RAND() * 1051)),
('ssafy100', 'asdf1234%', '유쾌한기분', FLOOR(RAND() * 1051)),
('ssafy101', 'asdf1234%', '밝은하루', FLOOR(RAND() * 1051)),
('ssafy102', 'asdf1234%', '활기찬몸', FLOOR(RAND() * 1051)),
('ssafy103', 'asdf1234%', '행복한운동', FLOOR(RAND() * 1051)),
('ssafy104', 'asdf1234%', '따뜻한미래', FLOOR(RAND() * 1051)),
('ssafy105', 'asdf1234%', '맑은정신', FLOOR(RAND() * 1051)),
('ssafy106', 'asdf1234%', '유쾌한토론', FLOOR(RAND() * 1051)),
('ssafy107', 'asdf1234%', '밝은아이디어', FLOOR(RAND() * 1051)),
('ssafy108', 'asdf1234%', '활기찬팀', FLOOR(RAND() * 1051)),
('ssafy109', 'asdf1234%', '행복한생각', FLOOR(RAND() * 1051)),
('ssafy110', 'asdf1234%', '따뜻한마음', FLOOR(RAND() * 1051)),
('ssafy111', 'asdf1234%', '맑은정원', FLOOR(RAND() * 1051)),
('ssafy112', 'asdf1234%', '유쾌한토크', FLOOR(RAND() * 1051)),
('ssafy113', 'asdf1234%', '밝은세계', FLOOR(RAND() * 1051)),
('ssafy114', 'asdf1234%', '활기찬미래', FLOOR(RAND() * 1051)),
('ssafy115', 'asdf1234%', '행복한여행', FLOOR(RAND() * 1051));

INSERT INTO `addiction_list` (user_id, title, start_time, target_time, end_time, icon_path, addiction_score) VALUES
-- Inserting various scenarios for addiction list
('ssafy', '알코올', NOW() - INTERVAL 1 DAY, 3, DATE_ADD(start_time, INTERVAL target_time DAY), '/src/assets/addiction/icons/alcohol.png', 10),
('ssafy', '커피', NOW() - INTERVAL 10 DAY, 5, DATE_ADD(start_time, INTERVAL target_time DAY), '/src/assets/addiction/icons/Coffee.png', 150),
('ssafy', '게임', NOW() - INTERVAL 110 DAY, 100, DATE_ADD(start_time, INTERVAL target_time DAY), '/src/assets/addiction/icons/Game.png', 3220),
('ssafy2', '유튜브', NOW() - INTERVAL 7 DAY, 4, DATE_ADD(start_time, INTERVAL target_time DAY), '/src/assets/addiction/icons/Youtube.png', 100),
('ssafy2', '패스트푸드', NOW() - INTERVAL 67 DAY, 50, DATE_ADD(start_time, INTERVAL target_time DAY), '/src/assets/addiction/icons/Fastfood.png', 1520),
('ssafy3', '담배', NOW() - INTERVAL 17 DAY, 10, DATE_ADD(start_time, INTERVAL target_time DAY), '/src/assets/addiction/icons/Smoke.png', 380),
('ssafy3', '인스타그램', NOW() - INTERVAL 20 DAY, 30, DATE_ADD(start_time, INTERVAL target_time DAY), '/src/assets/addiction/icons/alcohol.png', 720);

INSERT INTO `comment` (`user_id`, `nickname`, `content`, `create_date`) VALUES
('ssafy', '웃는고래', '명상과 호흡 운동이 큰 도움이 되었습니다. 추천해요!', '2024-05-23 17:28:04'),
('ssafy', '웃는고래', '처음이 제일 힘들지만, 꾸준히 하니 점점 쉬워져요.', '2024-05-21 07:28:04'),
('ssafy', '웃는고래', '작은 목표부터 차근차근 이루어 나가고 있어요. 함께해요!', NOW() - INTERVAL 17 DAY),
('ssafy2', '똑똑한여우', '자연 속에서 걷는 것이 중독을 잊게 해줘요. 함께 걸어요!', '2024-05-24 06:28:04'),
('ssafy2', '똑똑한여우', '중독에서 벗어나니 삶의 질이 확실히 좋아졌어요.', NOW() - INTERVAL 5 DAY),
('ssafy2', '똑똑한여우', '긍정적인 생각을 유지하는 것이 중요한 것 같아요. 힘내세요!', NOW() - INTERVAL 7 DAY),
('ssafy2', '똑똑한여우','하루하루 기록하면서 목표에 다가가고 있어요. 함께 힘내요!', NOW() - INTERVAL 17 DAY),
('ssafy3', '날아라호랑이','좋은 습관을 만들기 위해 오늘도 열심히 노력 중입니다!', NOW() - INTERVAL 1 DAY),
('ssafy3', '날아라호랑이','음악을 들으며 스트레스를 풀고 있어요. 음악의 힘!', NOW() - INTERVAL 27 DAY);


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
