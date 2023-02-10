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

public class StudyMembersDTO {
	private String id;
	private String email;
	private String goal;
	private String grade;
	private String nickname;
	private String password;
	private Date regdate;
	
}
