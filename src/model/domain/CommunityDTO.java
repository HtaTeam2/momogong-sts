package model.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class CommunityDTO { 

	private int comNo; //게시번호 - 시퀀스
	private String comTitle; // 제목
	private String membernick; //멤버 아이디
	private String subject; //말머리
	private String comPw; //비밀번호
	private Date comRegdate; //가입날짜
	private String comContent; // 게시글 내용
	private int comViewCount; //조회수

}

