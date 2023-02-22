package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import model.domain.MyStudyDTO;
import model.domain.StudyGroupMembersDTO;
import model.domain.entity.StudyGroup;
import model.domain.entity.StudyLists;
import model.domain.entity.StudyMembers;
import util.DBUtil;
import util.DBUtil2;

@Repository
public class StudyGroupDAO {

	// 스터디 생성 후 업데이트 group -> host닉으로 insert
	public void hostJoin(String joinId, long roomNo) throws SQLException, Exception {
		System.out.println("hostJoin DAO");
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		StudyGroup group = null;
		StudyLists lists = null;
		StudyMembers mem = null;

		try {
			tx.begin();

			lists = em.find(StudyLists.class, roomNo);
			mem = em.find(StudyMembers.class, joinId);

			if (joinId != null && roomNo != 0) {
				group = new StudyGroup();
				group.setStudyLists(lists);
				group.setStudyMembers(mem);

				em.persist(group);

				tx.commit();
			}
		} catch (Exception s) {
			s.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}

	}

	// 스터디 가입 또는 입장 후 목록 조회. 그룹 내에 해당 Id가 없으면 가입 발생(예외)
	public StudyGroup joinGroup(String joinId, long roomNo) throws NoResultException, SQLException, Exception {
		System.out.println("joinGroup DAO");
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		StudyGroup group = null;
		StudyLists lists = null;
		StudyMembers mem = null;
		try {
			tx.begin();
			lists = em.find(StudyLists.class, roomNo);
			mem = em.find(StudyMembers.class, joinId);
			// namedQuery 사용법 1. 그룹내에서 해당 id로 조회. 없으면 예외가 발생해버린다 NoResultException
			group = (StudyGroup) em.createNamedQuery("Group.findByJoinId").setParameter("roomNo", roomNo)
					.setParameter("name", joinId).getSingleResult();

		} catch (NoResultException e) {
			try {
				group = new StudyGroup();
				group.setStudyLists(lists);
				group.setStudyMembers(mem);
				em.persist(group);
				// 방 번호를 찾아서
				if (lists != null) {
					// before update
					System.out.println("update 전 : " + lists.getMemNum());
					if (lists.getMemNum() < lists.getMaxMem()) {
						// 1 추가. 다만 4명이 넘어야 한다는 조건은...
						lists.setMemNum(lists.getMemNum() + 1);
						em.persist(lists);
					} else {
						throw new Exception("인원을 초과하여 가입하실 수 없습니다.");
					}
				} else {
					throw new Exception("업데이트 하려는 사람의 정보를 찾지 못하였습니다");
				}
				tx.commit();
				return group;

			} catch (Exception s) {
				s.printStackTrace();
				tx.rollback();
				throw s;
			}
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw e;
		} finally {
			em.close();
		}
		return group;
	}

	// XXXXXXX그룹원 조회(JPA 사용X) namedQuery join문이 잘못된걸까
	public List getCustomers(long roomNo) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();

		List all = em.createNamedQuery("GroupMembers.findByRoomNo").setParameter("roomNo", roomNo).getResultList();
		System.out.println("DAO에서 출력" + all.get(0));
		em.close();

		return all;
	}

	// 그룹원 조회. join문을 써서 닉네임 뽑음
	public ArrayList<StudyGroupMembersDTO> getCustomers1(long roomNum) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<StudyGroupMembersDTO> allList = null;
		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement(
					"SELECT l.roomNo, l.roomTitle, m.id, m.nickname, m.goal FROM studygroup g, studymembers m, studylists l WHERE g.studymembers_id = m.id AND g.studylists_no = l.roomNo AND g.studylists_no = ?;");
			pstmt.setLong(1, roomNum);
			rset = pstmt.executeQuery();

			allList = new ArrayList<>();

			while (rset.next()) {
				allList.add(new StudyGroupMembersDTO(roomNum, rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} finally {
			DBUtil2.close(con, pstmt, rset);
		}
		return allList;
	}

	// 방 탈퇴: 호스트면 방 전체 삭제, 멤버면 그룹만 탈퇴, 가입한 방이 여러개인 것을 고려해서 방번호도 가져옴
	// 만약에 방 전체가 삭제 되면 List도 삭제해야 하니까 URL 보냄
	public int delete(String id, long roomNum) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = DBUtil2.getConnection();
			con.setAutoCommit(false);
			pstmt1 = con.prepareStatement("SELECT memberid FROM studylists where roomNo = ?"); // 관리자 찾기
			pstmt2 = con.prepareStatement("DELETE FROM studygroup WHERE studylists_no = ?"); // 해당 방 전체 탈퇴
			pstmt3 = con.prepareStatement("DELETE FROM studygroup WHERE studymembers_id = ? AND studylists_no = ? "); // 해당
																														// 방의
																														// 해당
																														// 아이디만
																														// 탈퇴
			pstmt4 = con.prepareStatement("UPDATE studylists SET memNum = (memNum - 1) WHERE roomNo = ?"); // 후 list테이블
																											// 멤버수 -1

			pstmt1.setLong(1, roomNum);
			rs = pstmt1.executeQuery(); // 관리자id 추출
			// id 비교를 위한 조건문
			if (rs.next()) {
				if ((rs.getString(1)).equals(id)) {
					// 일치하면 방 관리자 = 해당 방번호로 그룹원 전체 삭제
					pstmt2.setLong(1, roomNum);
					pstmt2.executeUpdate();
					con.commit();
					result = 1;
				} else {
					// 일치하지 않으면 방관리자X update후 바로 main페이지로 이동
					pstmt3.setString(1, id);
					pstmt3.setLong(2, roomNum);
					pstmt3.executeUpdate();
					pstmt4.setLong(1, roomNum);
					pstmt4.executeUpdate();
					con.commit();
					result = 2;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
			con.rollback();
			throw se;
		} finally {
			DBUtil2.close(con, pstmt1, pstmt2, pstmt3, pstmt4, rs);
		}
		return result;
	}

//	public ArrayList<MyStudyDTO> getMyStudy(String id) throws SQLException{
//		System.out.println("DAO getMyStudy " + id);
//		
//	}
	// mystudy 조회
	public ArrayList<MyStudyDTO> getMyStudy(String id) throws SQLException {
		System.out.println("DAO getMyStudy " + id);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MyStudyDTO> allList = null;
		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement(
					"SELECT g.studylists_no, l.roomTitle, m.id, m.nickname, m.goal FROM studylists l, studygroup g, studymembers m WHERE g.studymembers_id = m.id AND g.studylists_no = l.roomNo AND m.id = ?;");
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();

			allList = new ArrayList<MyStudyDTO>();
			while (rset.next()) {
				allList.add(new MyStudyDTO(rset.getLong(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} finally {
			DBUtil2.close(con, pstmt, rset);
		}
		return allList;
	}

}
