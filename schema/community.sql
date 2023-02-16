DROP TABLE community;


CREATE TABLE community (
	comNo LONG NOT NULL AUTO_INCREMENT,
	comTitle VARCHAR(50) NOT NULL,
	id VARCHAR(20) NOT NULL,
	subject VARCHAR(15) NOT NULL,
	comPw VARCHAR(6) ,
	comRegdate DATETIME  DEFAULT now(),
	comContent VARCHAR(255) NOT NULL,
	comViewCount INT NOT NULL,
	PRIMARY KEY(comNo)
);

ALTER TABLE community ADD constraint community_nick_fk FOREIGN KEY (id) REFERENCES studymembers(id);







