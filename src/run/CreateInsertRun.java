package run;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import util.DBUtil;
//테이블 생성 및 데이터 추가
//작성전 persistence.xml 확인 <property name="hibernate.hbm2ddl.auto" value="create" />
//작성 후엔 까먹지말고 none으로 꼭 돌리기!!!!!!
public class CreateInsertRun {
	public static void main(String[] args) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		tx.commit();
		em.close();
		
	}
	
	

}
