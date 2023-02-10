package model.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class NoticeDTO {
	
	private long noticeNo;
	private String noticeContent;
	private Date noticeRegdate;
	private String noticeTitle;
	private int viewCount;
	
}