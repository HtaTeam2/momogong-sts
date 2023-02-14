package model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//SELECT g.studylists_no, m.id, m.nickname, m.goal FROM studygroup g, studymembers m WHERE g.studymembers_id = m.id AND g.studylists_no = 1;	

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class StudyGroupMembersDTO {
	
	private long studyListNo; //방번호fk
	private String roomTitle;	//그룹원 아이디
	private String id;	//그룹원 아이디
	private String nickname; //닉네임
	private String goal; //목표
	
	
}
