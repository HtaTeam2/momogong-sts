package model.domain.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@SequenceGenerator(
        name="STUDYGROUP_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="STUDYGROUP_SEQ", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
        )
public class StudyGroup { 
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDYGROUP_SEQ_GEN")
	private Long memNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studylists_no")
	private StudyLists studyLists;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studymembers_id")
	private StudyMembers studyMembers;
	
	
	@CreationTimestamp
	private Date regdate;

}
