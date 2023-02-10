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

public class CommunityDTO { 
	private long comNo; //게시번호 - 시퀀스
	private String comContent; // 게시글 내용
	private String comPw; //비밀번호
	private Date comRegdate; //가입날짜
	private String comTitle; // 제목
	private int comViewCount; //조회수
	private String subject; //말머리
	private String memberid; //멤버 아이디
}
