package model.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString

@Entity
@SequenceGenerator(name = "RECOMMEND_SEQ_GEN", // 시퀀스 제너레이터 이름
		sequenceName = "RECOMMEND_SEQ", // 시퀀스 이름
		initialValue = 1, // 시작값
		allocationSize = 1 // 메모리를 통해 할당할 범위 사이즈!
)
public class Recommend {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RECOMMEND_SEQ_GEN")
	private Long recommNo; // 추천 일련번호

	@NonNull
	@Column(nullable = false)
	private Integer recommcheck;

	// 게시물번호 커뮤니티테이블 참조!
	@ManyToOne
	@JoinColumn(name = "commid")
	private Community community;

	// 회원id 회원테이블 참조!
	@ManyToOne
	@JoinColumn(name = "memid")
	private StudyMembers studymembers;
}
