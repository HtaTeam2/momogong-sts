package model;

import org.springframework.stereotype.Repository;

@Repository
public class NoticeDAO {
	
	private static NoticeDAO instance = new NoticeDAO();
	
	public static NoticeDAO getInstance() {
		return instance;
	}

}
