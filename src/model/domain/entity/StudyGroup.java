package model.domain.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

//수정해서 사용 => 2개 이상의 조건을 주고 검색
@NamedQuery(name = "Group.findByJoinId", 
			query = "select g from StudyGroup g where g.studyMembers.id = :name and g.studyLists.roomNo = :roomNo")
//SELECT DISTINCT * FROM studygroup g INNER JOIN studymembers m WHERE g.studymembers_id = m.id AND g.studylists_no = 1;
// 결과는 나오나 jsp el태그로 안뽑힘
//@NamedQuery(name = "GroupMembers.findByRoomNo", 
//			query = "SELECT m.nickname AS nick, l.roomTitle AS title "
//					+ "FROM StudyGroup g JOIN g.studyLists l JOIN g.studyMembers m "
//					+ "WHERE g.studyLists.roomNo = :roomNo")
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