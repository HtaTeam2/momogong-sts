package model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import model.domain.entity.Community;
import model.domain.entity.Recommend;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class RecommendDTO { 

	private long recommNo; //추천번호 - 시퀀스
	private long commid; //게시글 번호 - fk
	private String memid; //멤버 아이디 - fk
	private int recommcheck; //추천 여부
	
	
	public static RecommendDTO fromEntity(Recommend recommend) {
		return RecommendDTO.builder()
				.recommNo(recommend.getRecommNo())
				.commid(recommend.getCommunity().getComNo())
				.memid(recommend.getStudymembers().getId())
				.recommcheck(recommend.getRecommcheck())
				.build();
	}

}
