drop table studymembers;

CREATE TABLE studymembers(
	id VARCHAR(20) NOT NULL,	
	password VARCHAR(16) NOT NULL,
	nickname VARCHAR(20) NOT NULL,
	email VARCHAR(100) NOT NULL,	
	regdate DATETIME  DEFAULT now(),		
	grade VARCHAR(10) NOT NULL, 
	goal VARCHAR(255),
	PRIMARY KEY (id)
);

ALTER TABLE studymembers ADD UNIQUE (nickname);

INSERT INTO studymembers VALUES('test1', 'testpw', '테스트1', 'test1@gmail.com', now(), 'FREE', '');
INSERT INTO studymembers VALUES('test2', 'testpw', '테스트2', 'test2@gmail.com', now(), 'FREE', '');
INSERT INTO studymembers VALUES('test3', 'testpw', '테스트3', 'test3@gmail.com', now(), 'FREE', '');
INSERT INTO studymembers VALUES('test4', 'testpw', '테스트4', 'test4@gmail.com', now(), 'FREE', '');
INSERT INTO studymembers VALUES('admin', 'admin', '관리자', 'admin@gmail.com', now(), 'ADMIN', '');



