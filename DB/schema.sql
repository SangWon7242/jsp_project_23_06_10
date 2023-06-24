# DB 생성
DROP DATABASE IF EXISTS jspboard;
CREATE DATABASE jspboard;

# DB 선택
USE jspboard;

# 게시물 테이블 생성
CREATE TABLE article (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	title CHAR(200) NOT NULL,
	content LONGTEXT NOT NULL
);

# 게시물 테스트 데이터
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
content = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
content = '내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
content = '내용3';

# 멤버 테이블 생성
CREATE TABLE `member` (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	loginId CHAR(100) NOT NULL,
	loginPw CHAR(100) NOT NULL,
	`name` CHAR(100) NOT NULL
);

# 회원 테이블 데이터 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
`name` = '관리자';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user1',
loginPw = 'user1',
`name` = '유저1';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user2',
loginPw = 'user2',
`name` = '유저2';

# 게시물 테이블에 memberId 칼럼 추가
ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER regDate;

# 기존 겟기물은 그냥 2번 회원이 전부 작성한걸로 정한다.
UPDATE article
SET memberId = 2
WHERE memberId = 0;