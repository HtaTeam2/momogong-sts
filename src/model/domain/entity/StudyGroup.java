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
        name="STUDYGROUP_SEQ_GEN",
        sequenceName="STUDYGROUP_SEQ",
        initialValue=1,
        allocationSize=1
        )
public class StudyGroup { 
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long memNo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studylists_no")
	private StudyLists studyLists;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studymembers_no")
	private StudyMembers studyMembers;
	
	
	@CreationTimestamp
	private Date regdate;

}
