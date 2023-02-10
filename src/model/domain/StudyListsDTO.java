package model.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class StudyListsDTO {


	private long roomNo; //방번호
	private String category; //카테고리
	private int maxMem; //최대인원수 4
	private int memNum; //현재 가입된 인원수
	private String roomDesc; //방 설명
	private String roomPw; //방 비밀번호
	private String roomTitle; //방 제목
	private String hostId; //방관리자 id
}
