DROP TABLE community_seq;
DROP TABLE member_id_seq;
DROP TABLE notice_seq;
DROP TABLE studylists_seq;

DROP TABLE studylists;

CREATE TABLE studylists (
	roomNo LONG NOT NULL AUTO_INCREMENT,		
	category VARCHAR(10) NOT NULL, 
	roomTitle VARCHAR(20) NOT NULL, 
	roomPw VARCHAR(10)DEFAULT NULL,		
	roomDesc VARCHAR(255) ,		
	hostId VARCHAR(20) NOT NULL,	
	maxMem INT NOT NULL,	
	memNum INT NOT NULL,
	PRIMARY KEY(roomNo)
);

alter table studylists add constraint studylists_id_fk foreign key(hostId) references studymembers (id); 




