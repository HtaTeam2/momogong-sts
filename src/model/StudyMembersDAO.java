package model;
//스터디 구성원 회원가입(insert), 로그인/로그아웃, 수정(update), 탈퇴(delete) 


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import model.domain.StudyMembersDTO;
import util.DBUtil2;

@Repository
public class StudyMembersDAO {
	
	//id,pw 로그인 인증 메소드 - 시현님
		public boolean getstudymembers(String id, String pw) throws SQLException {
			Connection conn = null;
			PreparedStatement pstmt = null; 
			ResultSet rset = null;
			
			boolean result = false;
			
			try {
				conn = DBUtil2.getConnection();
				pstmt = conn.prepareStatement("SELECT name from studymembers where id=? and password=?");
				
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
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
		
	//회원가입 
	public int  insertMember(StudyMembersDTO dto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement("INSERT INTO studentmembers VALUES(?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, dto.getId()); //중복 불가능
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getGoal()); //null 가능
			pstmt.setString(4, dto.getGrade());
			pstmt.setString(5, dto.getNickname());//null 가능, 중복 불가능
			pstmt.setString(6, dto.getPassword());
			pstmt.setDate(7, dto.getRegdate()); //null 가능
			
			pstmt.executeUpdate();
			
		} catch (SQLException s) {
			s.printStackTrace();
			throw new SQLException("id가 중복되었습니다");

		} finally {
			DBUtil2.close(con, pstmt);
		}
		return -1;
	}
	
	//삭제
	public boolean delete(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement("delete from studymembers where id = ?");
			pstmt.setString(1, id);
			
			int result = pstmt.executeUpdate();
			if(result != 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		    DBUtil2.close(con, pstmt);
		}	
		return false;
	}

	//수정 - 닉네임/이메일/목표/비밀번호/(등급/프로필사진)
	public boolean update(StudyMembersDTO dto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil2.getConnection();

			pstmt = con.prepareStatement("UPDATE studymembers SET nickname =?, email =?, password = ? , goal = ? WHERE id = ?");
			
			pstmt.setString(1, dto.getNickname());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getPassword());
			pstmt.setString(4, dto.getGoal());
			pstmt.setString(5, dto.getId());
			
			int result = pstmt.executeUpdate();
			if(result == 1) {
				return true;
			}
			
		} catch (SQLException s) {
//			s.printStackTrace();
			throw s;
		} finally {
			DBUtil2.close(con, pstmt);
		}
		return false;
	}
	
	//조회
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
				return new StudyMembersDTO (rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6),rset.getDate(7));
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
			
		} finally {
			DBUtil2.close(conn, pstmt, rset);
		}
		return null;
	}
	
}
