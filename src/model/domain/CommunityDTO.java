package model.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import model.domain.entity.Community;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class CommunityDTO { 

	private long comNo; //게시번호 - 시퀀스
	private String comTitle; // 제목
	private String memberid; //멤버 아이디
	private String subject; //말머리
	private String comPw; //비밀번호
	private Date comRegdate; //가입날짜
	private String comContent; // 게시글 내용
	private int comViewCount; //조회수
	private int recommCount; //추천수
	
	/* ? ModelMapper 
	 * 
	 */ 
	
	public static CommunityDTO fromEntity(Community community) {
		return CommunityDTO.builder()
				.comNo(community.getComNo())
				.comTitle(community.getComTitle())
				.memberid(community.getStudymembers().getId())
				.subject(community.getSubject())
				.comPw(community.getComPw())
				.comRegdate(community.getComRegdate())
				.comContent(community.getComContent())
				.comViewCount(community.getComViewCount())
				.recommCount(community.getRecommCount())
				.build();
	}

}
