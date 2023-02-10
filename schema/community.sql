DROP TABLE community;


CREATE TABLE community (
	comNo INT NOT NULL AUTO_INCREMENT,
	comTitle VARCHAR(50) NOT NULL,
	membernick VARCHAR(20) NOT NULL,
	subject VARCHAR(15) NOT NULL,
	comPw VARCHAR(6) ,
	comRegdate DATETIME  DEFAULT now(),
	comContent VARCHAR(255) NOT NULL,
	comViewCount INT NOT NULL,
	PRIMARY KEY(comNo)
);

ALTER TABLE community ADD constraint community_nick_fk FOREIGN KEY (membernick) REFERENCES studymembers(nickname);








