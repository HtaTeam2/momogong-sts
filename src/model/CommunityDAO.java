package model;

import org.springframework.stereotype.Repository;

@Repository
public class CommunityDAO {
	
	private static CommunityDAO instance = new CommunityDAO();
	
	public static CommunityDAO getInstance() {
		return instance;
	}

}
