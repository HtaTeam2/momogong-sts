package model.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
public class StudyLists {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roomno;
	
<<<<<<< main
	@Column(length = 20)
=======
	@NonNull
	@Column(length = 20, nullable = false)
>>>>>>> local
	private String roomTitle;
	
	@Column(length = 6)
	private String roomPw;
	
	private String roomDesc;
	
	@OneToOne(fetch = FetchType.LAZY) //사용 시점에서 부모테이블이 조회가 되도록 설정
	@JoinColumn(name = "memberid")
	private StudyMembers studyMembers;
	
<<<<<<< main
	private Integer memNum;
	private Integer maxMem;
	
	@Column(length = 10)
=======
	@NonNull
	@Column(nullable = false)
	private Integer memNum; //생성 시점에서 멤버수는 1명
	
	@NonNull
	@Column(nullable = false)
	private Integer maxMem;
	
	@NonNull
	@Column(length = 10, nullable = false)
>>>>>>> local
	private String category;
	
	@OneToMany(mappedBy = "studyLists") //StudyLists에 매핑되어 있는 변수이름으로 지정해야함
	private List<StudyGroup> studygroup = new ArrayList<StudyGroup>();
}