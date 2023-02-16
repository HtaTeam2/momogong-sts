DROP TABLE notice;

CREATE TABLE notice(
	noticeNo LONG NOT NULL AUTO_INCREMENT,
	noticeTitle	VARCHAR(50) NOT NULL,
	noticeContent VARCHAR(255) NOT NULL,
	noticeRegdate DATETIME DEFAULT now(),
	viewCount INT NOT NULL, 
	PRIMARY KEY(noticeNo)
);

INSERT INTO notice(noticeTitle, noticeContent, viewCount) VALUES('공지입니다.', '공지내용입니다.', 0);
INSERT INTO notice(noticeTitle, noticeContent, viewCount) VALUES('공지입니다.', '공지내용입니다.', 0);
