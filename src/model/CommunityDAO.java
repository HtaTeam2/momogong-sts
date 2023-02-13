package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Test;
import org.springframework.stereotype.Repository;

import model.domain.CommunityDTO;
import model.domain.entity.Community;
import model.domain.entity.StudyMembers;
import util.DBUtil;
import util.DBUtil2;

@Repository
public class CommunityDAO {

	public CommunityDTO write(CommunityDTO dto) throws Exception{
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			
			StudyMembers member = em.find(StudyMembers.class, dto.getMemberid());
			Community community = new Community(dto.getComTitle(), dto.getSubject(), dto.getComContent(), dto.getComPw(), 0);
			community.setStudymembers(member);
			em.persist(community);
			
			tx.commit();
			return CommunityDTO.fromEntity(community);
			
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw e;
		}finally {
			em.close();
		}
		
	}

	public CommunityDTO view(long comNo, boolean flag) throws SQLException{
//		EntityManager em = DBUtil.getEntityManager();
//		EntityTransaction tx = em.getTransaction();
		
		Connection con = null;	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CommunityDTO dto = null;
		try {
			con = DBUtil2.getConnection();
			
			if(flag) {
				pstmt = con.prepareStatement("update community set comViewCount = comViewCount + 1 where comNo = ?");
				pstmt.setLong(1, comNo);
				if(pstmt.executeUpdate() == 0) {
					pstmt.close();
					pstmt = null;
					return dto; //여기서 메소드 종료
				}
			}
			pstmt = con.prepareStatement("select * from community where comNo = ?");
			pstmt.setLong(1, comNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				dto = new CommunityDTO(comNo, rset.getString("comTitle"), rset.getString("memberid"), rset.getString("subject"), rset.getString("comPw"), 
						rset.getDate("comRegdate"), rset.getString("comContent"), rset.getInt("comViewCount"));
			}
		}catch(SQLException s) {
			s.printStackTrace();
			throw s;
		}finally {
			DBUtil2.close(con, pstmt, rset);
		}
		return dto;
		
	}

	public List<Community> list() throws Exception{
		EntityManager em = DBUtil.getEntityManager();
		
		List<Community> list = null;
		try {
			String sql = "select c from Community c";
			list = em.createQuery(sql).getResultList();
			
			list.forEach(v->System.out.println(v));
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			em.close();
		}
		return list;
	}

	public boolean update(CommunityDTO dto) throws SQLException{
		Connection con = null;	
		PreparedStatement pstmt = null;
		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement("update community set comTitle = ?, subject = ?, comContent = ? where comNo = ? and comPw = ?");
			pstmt.setString(1, dto.getComTitle());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getComContent());
			pstmt.setLong(4, dto.getComNo());
			pstmt.setString(5, dto.getComPw());
			
			int result = pstmt.executeUpdate();
			if(result != 0) {
				return true;
			}
		}catch(SQLException s) {
			s.printStackTrace();
			throw s;
		}finally {
			DBUtil2.close(con, pstmt);
		}
		return false;
		
	}

	public boolean delete(long comNo, String comPw) {
		Connection con = null;	
		PreparedStatement pstmt = null;
		try{
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement("delete from community where comNo = ? and comPw = ?");
			pstmt.setLong(1, comNo);
			pstmt.setString(2, comPw);
			
			int result = pstmt.executeUpdate();
			if(result != 0) {
				return true;
			}
		}catch(SQLException s) {
			s.printStackTrace();
			DBUtil2.close(con, pstmt);
		}
		return false;
	}
	
	@Test
	public void dataTest() {
		try {
			write(new CommunityDTO(0, "제목", "아이디", "말머리", "비번", null, "내용..", 0));
			list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
