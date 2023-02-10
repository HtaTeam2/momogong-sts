package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import model.domain.CommunityDTO;
import util.DBUtil2;

@Repository
public class CommunityDAO {

	public void write(CommunityDTO dto) throws SQLException{
		Connection con = null;	
		PreparedStatement pstmt = null;
		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement("insert into community values(COMMUNITY_SEQ.nextval, ?, ?, sysdate, ?, 0, ?, ?)");
			pstmt.setString(1, dto.getComContent());
			pstmt.setString(2, dto.getComPw());
			pstmt.setString(3, dto.getComTitle());
			pstmt.setString(4, dto.getSubject());
			pstmt.setString(5, dto.getMemberId());
			pstmt.executeUpdate();
		}catch(SQLException s) {
			s.printStackTrace();
			throw s;
		}finally {
			DBUtil2.close(con, pstmt);
		}
		
	}

	public CommunityDTO view(long comNo, boolean flag) throws SQLException{
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
				dto = new CommunityDTO(comNo, rset.getString("comTitle"), rset.getString("subject"), rset.getString("comContent"), rset.getString("comPw"), 
						rset.getDate("comRegdate"), rset.getInt("comViewCount"), rset.getString("memberId"));
			}
		}catch(SQLException s) {
			s.printStackTrace();
			throw s;
		}finally {
			DBUtil2.close(con, pstmt, rset);
		}
		return dto;
		
	}

	public ArrayList<CommunityDTO> list() throws SQLException{
		Connection con = null;	
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<CommunityDTO> list = null;
		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement("select * from community");
			rset = pstmt.executeQuery();
			
			list = new ArrayList<CommunityDTO>();
			
			while(rset.next()) {
				list.add(new CommunityDTO(rset.getLong("comNo"), rset.getString("comTitle"), rset.getString("subject"), rset.getString("comContent"), rset.getString("comPw"), 
						rset.getDate("comRegdate"), rset.getInt("comViewCount"), rset.getString("memberId")));
			}
		}catch(SQLException s) {
			s.printStackTrace();
			throw s;
		}finally {
			DBUtil2.close(con, pstmt, rset);
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
	

}
