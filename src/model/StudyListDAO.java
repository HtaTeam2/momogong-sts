package model;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import model.domain.StudyListsDTO;
import model.domain.entity.StudyLists;
import model.domain.entity.StudyMembers;
import util.DBUtil;

@Repository
public class StudyListDAO {

	// 방만들기
	public StudyLists insertList(String roomTitle, String hostId, String category, String roomDesc, int maxMem,
			String roomPw) throws Exception {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		StudyMembers smem = null;
		StudyLists lists = null;

		try {
			tx.begin();

			smem = em.find(StudyMembers.class, hostId);

			lists = new StudyLists();
			lists.setRoomTitle(roomTitle);
			lists.setMaxMem(maxMem);
			lists.setCategory(category);
			lists.setMemNum(1);
			if (roomPw != null) {
				lists.setRoomPw(roomPw);
			}
			if (roomDesc != null) {
				lists.setRoomDesc(roomDesc);
			}
			lists.setStudyMembers(smem);

			em.persist(lists);

			System.out.println(lists.getRoomNo());

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();

		} finally {
			em.close();
		}
		return lists;
	}

	// 방삭제
	public void deleteList(Long roomNo) throws Exception {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			// 방번호로
			StudyLists lists = em.find(StudyLists.class, roomNo);

			if (lists != null) {
				em.remove(lists);
			} else {
				System.out.println("이미 처리되었습니다.");
			}
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			em.close();
		}
	}

	// 방 하나 보기
	public StudyLists oneRoom(long roomNo) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		StudyLists lists = null;

		try {
			tx.begin();
			lists = em.find(StudyLists.class, roomNo);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
		return lists;
	}

	// 방장으로 등록이 되어있는지 확인
	public String hostCheck(String id) {
		EntityManager em = DBUtil.getEntityManager();
		String hostId = null;
		try {
			hostId = (String) em.createNamedQuery("StudyLists.findByHostId").setParameter("hostId", id)
					.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			hostId = null;
			return hostId;
		}
		return hostId;
	}

	// 방장인지 확인 후 수정
	public StudyLists updateCheck(long roomNo, String id) throws SQLException, NoResultException {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		StudyLists lists = null;

		try {
			tx.begin();
			String hostId = (String) em.createNamedQuery("StudyLists.findById").setParameter("roomNo", roomNo)
					.getSingleResult();
			System.out.println(hostId);
			// 방의 호스트 id와 일치하면 수정 가능(true)
			if (hostId.equals(id)) {
				lists = em.find(StudyLists.class, roomNo);
				tx.commit();
			}
		} catch (NoResultException e) {
			tx.rollback();
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
		return lists;
	}

	// 방수정, 스터디이름, 스터디상세설명, 스터디 카테고리만 수정.
	public boolean updateList(long roomNo, String roomTitle, String roomDesc, String roomPw, String category)
			throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		StudyLists lists = null;

		try {
			tx.begin();
			System.out.println(roomDesc + ", " + category);
			lists = em.find(StudyLists.class, roomNo);
			// 리스트가 존재한다면 null이 아님
			if (lists != null) {
				// DAO로 넘어왔는지 Test
				System.out.println("업데이트 전 : " + lists);
				lists.setRoomTitle(roomTitle);
				lists.setRoomDesc(roomDesc);
				lists.setCategory(category);
				em.persist(lists);
				tx.commit();
				return true;
			} else {
				System.out.println("다시 시도해 주세요.");
			}
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();

		} finally {
			em.close();
		}
		return false;
	}

	// 방 전체조회
	public List<StudyLists> allList() throws Exception {
		EntityManager em = DBUtil.getEntityManager();
		List<StudyLists> list = null;

		try {
			String sql = "select l from StudyLists l";
			list = em.createQuery(sql).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return list;
	}

	// 방 이름으로 검색(부분검색). static이 있어야 함..
	public static List<StudyLists> serchRoom(String title) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		System.out.println("serchRoom 방찾기: " + title);
		List<StudyLists> all = em.createNamedQuery("StudyLists.findByLikeLists")
				.setParameter("title", "%" + title + "%").getResultList();
		em.close();

		return all;
	}
}
