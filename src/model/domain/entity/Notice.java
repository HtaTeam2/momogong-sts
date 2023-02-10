package model.domain.entity;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.SequenceGenerators;

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

@Entity
@SequenceGenerator(
            name="NOTICE_SEQ_GEN", //시퀀스 제너레이터 이름
            sequenceName="NOTICE_SEQ", //시퀀스 이름
            initialValue=1, //시작값
            allocationSize=1 //메모리를 통해 할당할 범위 사이즈
            )
public class Notice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICE_SEQ_GEN")
	private Long noticeNo;
	
	@NonNull
<<<<<<< main
	@Column(length = 50)
	private String noticeTitle;
	
	@NonNull
=======
	@Column(length = 50, nullable = false)
	private String noticeTitle;
	
	@NonNull
	@Column(nullable = false)
>>>>>>> local
	private String noticeContent;
	
	@CreationTimestamp
	private Date noticeRegdate;
	
<<<<<<< main
=======
	@NonNull
	@Column(nullable = false)
>>>>>>> local
	private Integer viewCount;
}