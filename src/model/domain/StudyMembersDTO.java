package model.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class StudyMembersDTO {
	private String id;
	private String email;
	private String goal;
	private String grade;
	private String nickname;
	private String password;
	private Date regdate;
	
	
	public StudyMembersDTO(String id, String email, String grade, String nickname, Date regdate) {
		super();
		this.id = id;
		this.email = email;
		this.grade = grade;
		this.nickname = nickname;
		this.regdate = regdate;
	}
	
	
}
