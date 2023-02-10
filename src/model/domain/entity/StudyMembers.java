package model.domain.entity;
//스터디 회원 테이블

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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


@Entity
public class StudyMembers {
	@Id
	@NonNull
	@Column(length = 20)
	private String id;
	
	@NonNull
	@Column(length = 16, nullable = false)
	private String password;
	
	@NonNull
	@Column(length = 30, unique = true)
	private String nickname;
	
	@NonNull
	@Column(length = 100, nullable = false)
	private String email;
	
	@CreationTimestamp
	private Date regdate;
	
	private String goal;
	
	@NonNull
	@Column(length = 10, nullable = false)
	private String grade; //default : FREE => insert문에서 주는걸로!
	
	//참조
	@OneToMany(mappedBy = "studyMembers") //StudyGroup에 매핑설정된 변수 이름으로 지정해야 함
	private List<StudyGroup> studygroup = new ArrayList<StudyGroup>();
}
