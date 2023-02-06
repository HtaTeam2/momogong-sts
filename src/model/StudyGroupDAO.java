package model;

import org.springframework.stereotype.Repository;

@Repository
public class StudyGroupDAO {
	
	private static StudyGroupDAO instance = new StudyGroupDAO();
	
	public static StudyGroupDAO getInstance() {
		return instance;
	}

}
