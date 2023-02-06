package model.domain.entity;
//스터디 회원 테이블

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


@Entity
public class StudyMembers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 20)
	private String id;
	
	@NonNull
	@Column(length = 16)
	private String password;
	
	@NonNull
	@Column(length = 30, unique = true, nullable = false)
	private String nickname;
	
	@NonNull
	@Column(length = 100)
	private String email;
	
	private Date regdate;
	
	private String goal;
	
	private String grade;
	
	//참조
	@OneToMany(mappedBy = "studymembers_id")
	private List<StudyGroup> studygroup = new ArrayList<StudyGroup>();
}
