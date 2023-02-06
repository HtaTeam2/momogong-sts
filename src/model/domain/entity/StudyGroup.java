package model.domain.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class StudyGroup { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
