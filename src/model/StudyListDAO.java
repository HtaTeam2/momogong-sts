package model;

import org.springframework.stereotype.Repository;

@Repository
public class StudyListDAO {
	
	private static StudyListDAO instance = new StudyListDAO();
	
	public static StudyListDAO getInstance() {
		return instance;
	}

}
