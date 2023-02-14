package model;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Repository;

import model.domain.StudyListsDTO;
import model.domain.entity.StudyLists;
import util.DBUtil;

@Repository
public class StudyListDAO {
	
	//방만들기 
	public void insertList(StudyListsDTO sdto) throws Exception {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			StudyLists lists = new StudyLists(sdto.getRoomTitle(), sdto.getMemNum(), sdto.getMaxMem(), sdto.getCategory());
			em.persist(lists);
			tx.commit();
	
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			
		}finally {
			em.close();
		}
	}
	
	// 방삭제
	public void deleteList(Long roomNo) throws Exception {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			//방번호로
			StudyLists lists = em.find(StudyLists.class, roomNo);
			
			if (lists != null) {
				em.remove(lists);
			}else {
				System.out.println("이미 처리되었습니다.");
			}
			tx.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			em.close();
		}	
	}
	
	
	//방수정, 스터디이름, 스터디상세설명, 스터디 카테고리만 수정.
	public boolean updateList(StudyListsDTO sdto) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		StudyLists lists = null;
		
		try {
			tx.begin();
			
			lists = em.find(StudyLists.class, sdto.getRoomNo());
			//리스트가 존재한다면 null이 아님
			if (lists != null) {
				// DAO로 넘어왔는지 Test
				System.out.println("업데이트 전 : " + lists);
				lists.setRoomTitle(sdto.getRoomTitle());
				lists.setRoomDesc(sdto.getRoomDesc());
				lists.setCategory(sdto.getCategory());
			}else {
				System.out.println("다시 시도해 주세요.");
			}
			em.persist(lists);
			tx.commit();
			
		}catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			
		}finally {
			em.close();
		}
		return false;
	}
	
	//방 전체조회
	public List<StudyLists> allList() throws Exception {
		EntityManager em = DBUtil.getEntityManager();
		List<StudyLists> list = null;
		
		try {
			String sql = "select l from StudyLists l";
			list = em.createQuery(sql).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		return list;
	}
	
	//방 이름으로 검색(부분검색). static이 있어야 함..
	public static List<StudyLists> serchRoom(String title) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		System.out.println("serchRoom 방찾기: " + title);
		List<StudyLists> all = em.createNamedQuery("StudyLists.findByLikeLists").setParameter("title", "%" + title + "%").getResultList();
		em.close();
		
		return all;
	}
}
