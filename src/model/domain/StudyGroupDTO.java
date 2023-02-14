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

public class StudyGroupDTO {
	
	private long memNo; //그룹원 번호
	private Date regdate; //가입날짜
	private int roomNo; //방번호 fk
	private String nickname; //닉네임
}


