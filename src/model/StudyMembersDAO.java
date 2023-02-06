package model;
//스터디 구성원 회원가입(insert), 로그인/로그아웃, 수정(update), 탈퇴(delete) 

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Repository;

import model.domain.entity.StudyMembers;
import util.DBUtil;

@Repository
public class StudyMembersDAO {
	
	private static StudyMembersDAO instance = new StudyMembersDAO();
	
	public static StudyMembersDAO getInstance() {
		return instance;
	}
	
	//가입
	public void insertMember() {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			StudyMembers studyMembers = new StudyMembers();
			em.persist(studyMembers);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}

}
