DROP TABLE studygroup;

CREATE TABLE studygroup (
	memNo INT NOT NULL AUTO_INCREMENT, 
	regdate DATETIME  DEFAULT now(), 	
	roomNo INT NOT NULL, 
	nickname VARCHAR(20) NOT NULL,
	PRIMARY KEY(memNo)
);

ALTER TABLE studygroup ADD constraint studygroup_nick_fk FOREIGN KEY (nickname) REFERENCES studymembers(nickname);
ALTER TABLE studygroup ADD constraint studygroup_no_fk FOREIGN KEY (roomNo) REFERENCES studylists(roomNo);
