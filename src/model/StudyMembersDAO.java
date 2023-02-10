package model;
//스터디 구성원 회원가입(insert), 로그인/로그아웃, 수정(update), 탈퇴(delete) 
//관리자(admin)계정 정보수정, 회원삭제, 전체목록조회

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import model.domain.StudyMembersDTO;
import util.DBUtil2;

@Repository
public class StudyMembersDAO {
	
	//로그인 grade로 뽑아서 관리자,일반회원 구분?
	//sql = "select grade from studymembers where id=? and password=?";
	public boolean getStdMember(String id, String pw) throws SQLException {
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
	 * sql = "SELECT id,email,grade,nickname,regdate FROM studymembers where grade in('free', 'premium')";
	 */
	public ArrayList<StudyMembersDTO> getStdMembers() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<StudyMembersDTO> allList = null;
		
		try {
			conn = DBUtil2.getConnection();
			pstmt = conn.prepareStatement("SELECT id, email, grade, nickname, regdate FROM studymembers");
			rset = pstmt.executeQuery();
			
			allList = new ArrayList<StudyMembersDTO>();
			
			while (rset.next()) {
				allList.add(new StudyMembersDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getDate(5)));
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

			pstmt = con.prepareStatement("UPDATE studymembers SET password = ?, email = ? WHERE id = ?");
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getId());

			pstmt.executeUpdate(); //insert/update/delete

		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil2.close(con, pstmt);
		}
	}

	
	//가입
	public StudyMembersDTO insertMember(StudyMembersDTO dto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement("INSERT INTO customer VALUES(?, ?, ?)");
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			//pstmt.setString(3, dto.getNickname());
			pstmt.setString(3, dto.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException s) {
			s.printStackTrace();
			throw new SQLException("id가 중복되었습니다");
		} finally {
			DBUtil2.close(con, pstmt);
		}
		return dto;
	}
}
