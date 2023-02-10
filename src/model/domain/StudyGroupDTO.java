package model.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class StudyGroupDTO {

	private long memNo;	//그룹원 번호
	private Date regdate;	//가입날짜
	private long studyListNo; //방번호fk
	private String studyMemberId;	//그룹원 아이디
}
