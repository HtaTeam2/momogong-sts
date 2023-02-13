package model.domain.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

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

//수정해서 사용 => 모든 검색 일치
//@NamedQuery(name = "Community.updateBycomNo", query = "update Community set ")
//수정해서 사용 => 부분검색
//@NamedQuery(name = "Community.findAll", query = "select c from Community p")

//수정해서 사용 => 모든 검색 일치
//@NamedQuery(name = "Player.findByTeamPlayers", query = "select p from Player p where p.team.tname = :name")
//수정해서 사용 => 부분검색
//@NamedQuery(name = "Player.findByLikePlayer", query = "select p from Player p where p.name like :name")

@Entity
@SequenceGenerator(
        name="COMMUNITY_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="COMMUNITY_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈!
        )
public class Community {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COMMUNITY_SEQ_GEN")
	private Long comNo;
	
	@NonNull
	@Column(length = 50, nullable = false)
	private String comTitle;

	@NonNull
	@Column(nullable = false)
	private String subject;
	
	@NonNull
	@Column(nullable = false)
	private String comContent;
	
	@NonNull
	@Column(length = 6, nullable = false)
	private String comPw;
	
	@CreationTimestamp
	private Date comRegdate;
	
	@NonNull
	@Column(nullable = false)
	private Integer comViewCount;
	
	//글쓴이 회원테이블 참조!
	@ManyToOne
	@JoinColumn(name = "memberid")
	private StudyMembers studymembers;
}
