package model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//내 스터디에 필요한 정보. id를 조건으로 적어도 방 타이틀은 얻어야 함
//SELECT g.studyListNo, l.roomTitle, m.id, m.nickname, m.goal FROM studylists l, studygroup g, studymembers m WHERE g.nickname = m.nickname AND g.studylists_no = l.roomNo AND m.id = ?;	

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class MyStudyDTO {
	
	private long studyListNo; //방번호fk
	private String roomTitle; //방이름
	private String id;	//아이디
	private String nickname; //닉네임
	private String goal; //목표
	
	
}
