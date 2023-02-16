DROP TABLE studygroup;

CREATE TABLE studygroup (
	memNo LONG NOT NULL AUTO_INCREMENT, 
	regdate DATETIME  DEFAULT now(), 	
	roomNo LONG NOT NULL, 
	nickname VARCHAR(20) NOT NULL,
	PRIMARY KEY(memNo)
);

ALTER TABLE studygroup ADD constraint studygroup_nick_fk FOREIGN KEY (nickname) REFERENCES studymembers(id);
ALTER TABLE studygroup ADD constraint studygroup_no_fk FOREIGN KEY (roomNo) REFERENCES studylists(roomNo);
