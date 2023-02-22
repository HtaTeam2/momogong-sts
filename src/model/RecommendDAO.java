package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;
import org.springframework.stereotype.Repository;

import model.domain.RecommendDTO;
import model.domain.entity.Community;
import model.domain.entity.Recommend;
import model.domain.entity.StudyMembers;
import util.DBUtil;

@Repository
public class RecommendDAO {

	public int check(long commid, String memid) {
		EntityManager em = DBUtil.getEntityManager();

		int checkResult = 0;
		try {
			String sql = "select count(r) from Recommend r where r.community.comNo =:commid and r.studymembers.id =:memid";
			checkResult = Integer.parseInt(String.valueOf((em.createQuery(sql).setParameter("commid", commid)
					.setParameter("memid", memid).getSingleResult())));
			System.out.println(checkResult);

		} catch (Exception e) {
			throw e;
		} finally {
			em.close();
		}
		return checkResult;
	}

	//////////////// 추천기능
	public RecommendDTO thup(long commid, String memid) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Recommend recommend = null;

		try {
			tx.begin();
			System.out.println("thup()-------------");
			String sql = "update Community c set c.recommCount + 1 where c.comNo =:commid";
			em.createQuery(sql).setParameter("commid", commid).executeUpdate(); // 1. 커뮤니티 테이블에 추천수 +1

			System.out.println("thup2()-------------");
			em.find(Community.class, commid);
			em.find(StudyMembers.class, memid);
			recommend = new Recommend(1); // 2. 추천 테이블에 새로 추가
			em.persist(recommend);

			System.out.println("thup3()-------------");
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();

		} finally {
			em.close();
		}
		return RecommendDTO.fromEntity(recommend);
	}

	//////////////// 추천취소기능
	public RecommendDTO thdown(long commid, String memid) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Recommend recommend = null;
		try {
			tx.begin();

			String sql = "update Community c set c.recommCount - 1 where c.comNo =:commid";
			em.createQuery(sql).setParameter("commid", commid).executeUpdate(); // 1. 커뮤니티 테이블에 추천수 -1

			recommend = em.find(Recommend.class, commid);
			if (recommend != null) {
				em.remove(recommend); // 2. 추천 테이블 삭제
			} else {
				throw new Exception("이미 취소하셨습니다.");
			}
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();

		} finally {
			em.close();
		}
		return RecommendDTO.fromEntity(recommend);
	}

	@Test
	public void test() {
		try {
			check(1L, "test1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
