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
import javax.persistence.NamedQuery;

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


	// 회원가입 - jpa
	public static StudyMembers insertMember(StudyMembers member) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(member);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();

		} finally {
			em.close();
		}

		return member;
	}

	// 회원가입 - 아이디 중복 확인
	public static boolean duplecateID(String id) throws Exception {
		boolean result = true;

		try {
			Connection con = DBUtil2.getConnection();
			String sql = "select id from studymembers where id = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();

			// 존재하는 id라면 false로 바꾸기
			if (rs.next()) {
				result = false;
			}

		} catch (Exception e) {
			System.out.println("아이디 중복 확인 실패 : " + e);
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	// 멤버 본인 프로필 수정 - jpa
	public static boolean memUpdate(String id, String email, String goal, String nick, String pw) throws SQLException {
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
			em.persist(mem); // persist -> update
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

	// 본인 조회 - jpa
	public static StudyMembers getMember(String id) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		StudyMembers stdmember = null;

		try {
			tx.begin();
			stdmember = em.find(StudyMembers.class, id);

			System.out.println(stdmember);

			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			em.close();
		}

		return stdmember;
	}


	// delete
	public void deleteMember(String id) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			// find로 찾기
			StudyMembers members = em.find(StudyMembers.class, id);
			if (members != null) {
				em.remove(members);
			} else {
				System.out.println("이미 탈퇴처리가 완료된 회원입니다.");
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
			// 단순 조회 후
			members = em.find(StudyMembers.class, id);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		// 해당 객체 출력
		return members;
	}

	// jpa 로그인 : 네임드 쿼리 사용해서 id,pw로 1명의 회원정보 조회
	// @NamedQuery(name = "StudyMembers.findByLoginInfo", query = "select m from
	// StudyMembers m where m.id=:id and m.password=:password")
	// 로그인
	public boolean loginMember(String id, String password) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			StudyMembers loginData = (StudyMembers) em.createNamedQuery("StudyMembers.findByLoginInfo")
					.setParameter("id", id).setParameter("password", password).getSingleResult();

			em.persist(loginData);

			System.out.println(loginData);

			tx.commit();

			return true;

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return false;
	}

	// jpa - id찾기 (email 입력으로 찾기)
	// @NamedQuery(name = "StudyMembers.findByEmail", query = "select m from
	// StudyMembers m where m.email = :email")

	public StudyMembers findId(String email) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		StudyMembers members = null;
		try {

			tx.begin();

			members = (StudyMembers) em.createNamedQuery("StudyMembers.findByEmail").setParameter("email", email)
					.getSingleResult();

			System.out.println(members); // 테스트

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return members;
	}

	// jpa - pw찾기 (id,email 입력으로 찾기)
	// @NamedQuery(name = "StudyMembers.findPassword", query = "select m from
	// StudyMembers m where m.id=:id and m.email = :email")
	public StudyMembers findPwd(String id, String email) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		StudyMembers members = null;
		try {

			tx.begin();

			members = (StudyMembers) em.createNamedQuery("StudyMembers.findPassword").setParameter("id", id)
					.setParameter("email", email).getSingleResult();

			System.out.println(members); // 테스트

			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return members;
	}

	// 관리자
	// jpa - 모든 회원 검색
	public List<StudyMembers> getAllMembers() {
		EntityManager em = DBUtil.getEntityManager();

		// jpql
		String sql = "select m from StudyMembers m";
		// createQuery : 쿼리 데이터 조회
		// getResultList : 조회된 데이터 추출
		List<StudyMembers> all = em.createQuery(sql).getResultList();

		em.close();

		return all;
	}

	// 관리자 - jdbc
	/**
	 * 가입한 모든 회원 검색 (admin 제외/pw,goal 제외) sql = "SELECT id, nickname, email,
	 * regdate, grade FROM studymembers where grade in('free','premium')";
	 */
	public ArrayList<StudyMembersDTO> getMembers() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<StudyMembersDTO> allList = null;

		try {
			conn = DBUtil2.getConnection();
			pstmt = conn.prepareStatement(
					"SELECT id, nickname, email, regdate, grade  FROM studymembers where grade in('free','premium')");
			rset = pstmt.executeQuery();

			allList = new ArrayList<StudyMembersDTO>();

			while (rset.next()) {
				// allList.add(new StudyMembersDTO(rset.getString(1), rset.getString(2),
				// rset.getString(3), rset.getDate(4), rset.getString(5)));
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
	 * 회원 1명 삭제
	 */
	public boolean delete(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement("delete from studymembers where id=?");
			pstmt.setString(1, id);
			int result = pstmt.executeUpdate();
			if (result != 0) {
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
			// pstmt.setString(3, dto.getId());
			pstmt.executeUpdate(); // insert/update/delete
		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil2.close(con, pstmt);
		}
	}

}
