package model;
//스터디 구성원 회원가입(insert), 로그인/로그아웃, 수정(update), 탈퇴(delete) 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import model.domain.StudyMembersDTO;
import util.DBUtil2;

@Repository
public class StudyMembersDAO {
	
	//가입
	public StudyMembersDTO insertMember(StudyMembersDTO dto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement("INSERT INTO customer VALUES(?, ?, ?, ?)");
//			pstmt.setString(1, dto.getId());
//			pstmt.setString(2, dto.getPassword());
//			pstmt.setString(3, dto.getName());
//			pstmt.setString(4, dto.getEmail());
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
