package model.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

<<<<<<< HEAD
@AllArgsConstructor
@NoArgsConstructor
=======
@NoArgsConstructor
@AllArgsConstructor
>>>>>>> origin/ebwork
@Setter
@Getter
@ToString

public class StudyGroupDTO {
<<<<<<< HEAD

=======
>>>>>>> origin/ebwork
	private long memNo;	//그룹원 번호
	private Date regdate;	//가입날짜
	private long studyListNo; //방번호fk
	private String studyMemberId;	//그룹원 아이디
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/ebwork
