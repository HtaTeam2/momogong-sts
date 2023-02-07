package model.domain.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Getter
@Setter

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
	
	@Column(length = 50, nullable = false)
	private String comTitle;
	
	@Column(nullable = false)
	private String subject;
	
	@Column(nullable = false)
	private String comContent;
	
	@Column(length = 6, nullable = false)
	private String comPw;
	
	@CreationTimestamp
	private Date comRegdate;
	
	@Column(nullable = false)
	private Integer comViewCount;
	
	//글쓴이 회원테이블 참조
	@ManyToOne
	@JoinColumn(name = "memberid")
	private StudyMembers studymembers;
}
