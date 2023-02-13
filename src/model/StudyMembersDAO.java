package model;
//스터디 구성원 회원가입(insert), 로그인/로그아웃, 수정(update), 탈퇴(delete) 


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Repository;

import model.domain.StudyMembersDTO;
import model.domain.entity.StudyMembers;
import util.DBUtil;
import util.DBUtil2;

@Repository
public class StudyMembersDAO {
	
	private static StudyMembersDAO instance = new StudyMembersDAO();
	
	public static StudyMembersDAO getInstance() {
		return instance;
	}
	
	//가입 insert 로직
	public void insertMember(StudyMembersDTO dto) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			//방법2 기본생성자 생성 후 setxXX()메소드로 인자 집어넣어줌. 단, not null인 컬럼들은 필수로 값 삽입
//			StudyMembers members = new StudyMembers();
//			members.setId(dto.getId());
//			members.setPassword(dto.getPassword());
//
//			em.persist(members);
			
			//엔티티의 @nonnull이 붙은 컬럼들을 파라미터로 받아서 생성자 생성 후 
			StudyMembers members = new StudyMembers(dto.getId(), dto.getPassword(), dto.getNickname(), dto.getEmail(), dto.getGrade());
			//persist(객체의 변수) insert 실행
			em.persist(members);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	//goal 하나 수정 파라미터 인자값 수정 고려, 여러개 update는 방법을 찾지 못함 -> Spring Data 프레임 워크 어쩌구..
	public void updateMember(String goal, String id) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		StudyMembers members = null;
		try {
			tx.begin();
			//pk를 기준으로 class로부터 데이터를 찾음
			members = em.find(StudyMembers.class, id);
			if (members != null) {
				// before update
				System.out.println("update 전 : " + members);
				members.setGoal(goal);
			}else {
				System.out.println("업데이트 하려는 사람의 정보를 찾지 못하였습니다");
			}
			//persist -> update
			em.persist(members);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	// delete
	public void deleteMember(String id) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			//find로 찾기 
			StudyMembers members = em.find(StudyMembers.class, id);
			if (members != null) {
				em.remove(members);
			}else {
				System.out.println("이미 탈퇴처리가 완료된 회원입니다.");
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
	}
	
	// select 한명 => 마이페이지
	public StudyMembers findOneMember(String id) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		StudyMembers members = null;
		try {
			tx.begin();
			//단순 조회 후 
			members = em.find(StudyMembers.class, id);
		
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		//해당 객체 출력
		return members;
	}
	
	//모든 회원 검색(Empcopy 배열 출력) 반환타입
	public List<StudyMembers> getAllMembers() {
		EntityManager em = DBUtil.getEntityManager();
		
		//jpql
		String sql = "select m from StudyMembers m";
		//createQuery : 쿼리 데이터 조회
		//getResultList : 조회된 데이터 추출
		List<StudyMembers> all = em.createQuery(sql).getResultList();

		em.close();
		
		return all;
	}
		
	
	//회원가입 
//	public int insertMember(StudyMembersDTO dto) throws SQLException {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			con = DBUtil2.getConnection();
//			pstmt = con.prepareStatement("INSERT INTO studentmembers VALUES (?, ?, ?, ?, ?, ?, now())");
//			pstmt.setString(1, dto.getId()); //중복 불가능
//			pstmt.setString(2, dto.getEmail());
//			pstmt.setString(3, dto.getGoal()); //null 가능
//			pstmt.setString(4, dto.getGrade());
//			pstmt.setString(5, dto.getNickname());//null 가능, 중복 불가능
//			pstmt.setString(6, dto.getPassword());
//			
//			
//			pstmt.executeUpdate();
//			
//		} catch (SQLException s) {
//			s.printStackTrace();
//			throw new SQLException("id가 중복되었습니다");
//
//		} finally {
//			DBUtil2.close(con, pstmt);
//		}
//		return -1;
//	}
	


	//멤버 본인 프로필 수정 - 닉네임/이메일/목표/비밀번호/(등급/프로필사진)
	public static boolean memUpdate (String id, String email, String goal, String nick, String pw) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		StudyMembers mem = null;
		
		try {
			tx.begin();
			mem = em.find(StudyMembers.class, id);
			if (mem != null) {
				// before update
				System.out.println("update 전 : " + mem);
				mem.setEmail(email);
				mem.setGoal(goal);
				mem.setNickname(nick);
				mem.setPassword(pw);
				
			} else {
				System.out.println("업데이트 하려는 사람의 정보를 찾지 못하였습니다");
			}
			em.persist(mem); //persist -> update
			tx.commit(); 
			// after update
			System.out.println("update 후 : " + mem);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}

		return false;
		
	}
	
	//본인조회
	public StudyMembersDTO getMember(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null; //db 자체적인 실행 속도 향상 (Satatement에 비해서)
		ResultSet rset = null;
		
		try {
			conn = DBUtil2.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM studymembers where id =?");
			pstmt.setString(1, id);
			
			rset = pstmt.executeQuery();
			
			
			if ( rset.next() ) {
				return new StudyMembersDTO (rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getDate(5), rset.getString(6),rset.getString(7));
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
			
		} finally {
			DBUtil2.close(conn, pstmt, rset);
		}
		return null;
	}
	
	
	//로그인 grade로 뽑아서 관리자,일반회원 구분?
		//sql = "select grade from studymembers where id=? and password=?";
		public boolean getMemberInfo(String id, String pw) throws SQLException {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			try {
				conn = DBUtil2.getConnection();
				pstmt = conn.prepareStatement("select grade from studymembers where id=? and password=?");
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				
				rset = pstmt.executeQuery();
				
				if (rset.next()) {
					return true;
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				throw sqle;
			} finally {
				DBUtil2.close(conn, pstmt, rset);
			}
			
			return false;
		}
		
		/**
		 * 가입한 모든 회원 검색 (admin 제외/pw,goal 제외)
		 * sql = "SELECT id, nickname, email, regdate, grade  FROM studymembers where grade in('free','premium')";
		 */
		public ArrayList<StudyMembersDTO> getMembers() throws SQLException {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			ArrayList<StudyMembersDTO> allList = null;
			
			try {
				conn = DBUtil2.getConnection();
				pstmt = conn.prepareStatement("SELECT id, nickname, email, regdate, grade  FROM studymembers where grade in('free','premium')");
				rset = pstmt.executeQuery();
				
				allList = new ArrayList<StudyMembersDTO>();
				
				while (rset.next()) {
					//allList.add(new StudyMembersDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getDate(4), rset.getString(5)));
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				throw sqle;
			} finally {
				DBUtil2.close(conn, pstmt, rset);
			}
			
			return allList;
		}
		
		/**
		 * 회원 삭제
		 */
		public boolean delete(String id) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = DBUtil2.getConnection();
				pstmt = con.prepareStatement("delete from studymembers where id=?");
				pstmt.setString(1, id);
				int result = pstmt.executeUpdate();
				if(result != 0) {
					return true;
				}
			} catch (SQLException s) {
				s.printStackTrace();
				throw s;
			} finally {
				DBUtil2.close(con, pstmt);
			}
			
			return false;
		}
		
		/**
		 * 관리자 비밀번호,이메일 수정
		 */
		
		public void update(StudyMembersDTO dto) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = DBUtil2.getConnection();
				pstmt = con.prepareStatement("UPDATE studymembers SET password = ?, email = ? WHERE id = admin");
				pstmt.setString(1, dto.getPassword());
				pstmt.setString(2, dto.getEmail());
				//pstmt.setString(3, dto.getId());
				pstmt.executeUpdate(); //insert/update/delete
			} catch (SQLException s) {
				s.printStackTrace();
				throw s;
			} finally {
				DBUtil2.close(con, pstmt);
			}
		}
}
