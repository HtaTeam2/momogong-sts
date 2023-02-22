package model.domain.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter

//수정해서 사용 => 모든 검색 일치
//@NamedQuery(name = "Player.findByTeamPlayers", query = "select p from Player p where p.team.tname = :name")
//수정해서 사용 => 부분검색
//@NamedQuery(name = "Player.findByLikePlayer", query = "select p from Player p where p.name like :name")

@Entity
@SequenceGenerator(name = "NOTICE_SEQ_GEN", // 시퀀스 제너레이터 이름
		sequenceName = "NOTICE_SEQ", // 시퀀스 이름
		initialValue = 1, // 시작값
		allocationSize = 1 // 메모리를 통해 할당할 범위 사이즈
)
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICE_SEQ_GEN")
	private Long noticeNo;

	@NonNull
	@Column(length = 50, nullable = false)
	private String noticeTitle;

	@NonNull
	@Column(nullable = false)
	private String noticeContent;

	@CreationTimestamp
	private Date noticeRegdate;

	@NonNull
	@Column(nullable = false)
	private Integer viewCount;
}