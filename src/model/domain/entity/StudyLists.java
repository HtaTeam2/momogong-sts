package model.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class StudyLists {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roomno;
	
	@Column(length = 20)
	private String roomTitle;
	
	@Column(length = 6)
	private String roomPw;
	
	private String roomDesc;
	
	@OneToOne
	@JoinColumn(name = "memberid")
	private String roomAdmin;
	
	private Integer memNum;
	private Integer maxMem;
	
	@Column(length = 10)
	private String category;
	
	@OneToMany(mappedBy = "studylists_no")
	private List<StudyGroup> studygroup = new ArrayList<StudyGroup>();
}