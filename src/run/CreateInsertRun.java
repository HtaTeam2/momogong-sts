package run;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.domain.entity.Community;
import model.domain.entity.Notice;
import model.domain.entity.StudyGroup;
import model.domain.entity.StudyLists;
import model.domain.entity.StudyMembers;
import util.DBUtil;

//테이블 생성 및 데이터 추가
//작성전 persistence.xml 확인 <property name="hibernate.hbm2ddl.auto" value="create" />
//작성 후엔 까먹지말고 none으로 꼭 돌리기!!!!!!
public class CreateInsertRun {
	public static void main(String[] args) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		StudyMembers m1 = new StudyMembers("test1", "testpw", "테스트1", "test1@gmail.com", "FREE");
		StudyMembers m2 = new StudyMembers("test2", "testpw", "테스트2", "test2@gmail.com", "FREE");
		StudyMembers m3 = new StudyMembers("admin", "admin", "관리자", "admin@gmail.com", "ADMIN");

		em.persist(m1);
		em.persist(m2);
		em.persist(m3);

		Community c = new Community("제목", "기타", "글 내용입니다.", "1234", 0, 0);
		c.setStudymembers(m1);
		em.persist(c);

		Notice n = new Notice("제목", "공지입니다.", 0);
		em.persist(n);

		// 비밀번호 없는 경우
		StudyLists l1 = new StudyLists("테스트방1", 1, 4, "자격증");
		l1.setStudyMembers(m1);

		// 비밀번호 있는 경우
		StudyLists l2 = new StudyLists("테스트방2", 1, 4, "기타");
		l2.setRoomPw("0000");
		l2.setStudyMembers(m2);

		em.persist(l1);
		em.persist(l2);

		// l1방의 관리자 m1
		StudyGroup g1 = new StudyGroup();
		g1.setStudyLists(l1);
		g1.setStudyMembers(m1);

		// l2방의 관리자 m2
		StudyGroup g2 = new StudyGroup();
		g2.setStudyLists(l2);
		g2.setStudyMembers(m2);

		// l2방의 멤버 m1
		StudyGroup g3 = new StudyGroup();
		g3.setStudyLists(l2);
		g3.setStudyMembers(m1);

		em.persist(g1);
		em.persist(g2);
		em.persist(g3);

		tx.commit();
		em.close();

	}
}
