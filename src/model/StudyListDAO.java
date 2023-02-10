package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import model.domain.StudyListsDTO;
import util.DBUtil2;

@Repository
public class StudyListDAO {
	
	//방만들기 - 방을 만들고 난 후 스터디 그룹원 가입으로 이동하는 방식으로 진행
		public void createStdList(StudyListsDTO list) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DBUtil2.getConnection();
				//방 최대 인원수는 4명, 최초 생성시 방 멤버수는 방장 1명
				pstmt = con.prepareStatement("INSERT INTO studylists(roomNo, roomTitle, category, roomPw, roomDesc, maxMem, memNum, memberid) "
												+ "VALUES(STUDYLISTS_SEQ.NEXTVAL, ?, ?, ?, ?, 4, 1, ?)");
				pstmt.setString(1, list.getRoomTitle());
				pstmt.setString(2, list.getCategory());
				pstmt.setString(3, list.getRoomPw());
				pstmt.setString(4, list.getRoomDesc());
				pstmt.setString(5, list.getHostId());
			} catch (SQLException s) {
				s.printStackTrace();
				throw new SQLException("가입에 실패했습니다. 잠시 후 재시도 해주세요.");
			} finally {
				DBUtil2.close(con, pstmt);
			}
		}
	
	//방삭제
	public boolean delete(long roomNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement("delete from studylists where roomNo=?");
			pstmt.setLong(1, roomNo);
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
	
	//방업데이트
	public void update(StudyListsDTO list) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement("UPDATE studylists SET category=?, roomDesc=?, roomTitle=? WHERE roomNo=?");
			pstmt.setString(1, list.getCategory());
			pstmt.setString(2, list.getRoomDesc());
			pstmt.setString(3, list.getRoomTitle());
			pstmt.setLong(4, list.getRoomNo());
			
			pstmt.executeUpdate();
		} catch (SQLException s) {
			throw s;
		} finally {
			DBUtil2.close(con, pstmt);
		}
	}
	
	//방 전체조회
	public ArrayList<StudyListsDTO> getStdList() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<StudyListsDTO> allList = null;
		
		try {
			conn = DBUtil2.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM studylists");
			rset = pstmt.executeQuery();
			
			allList = new ArrayList<StudyListsDTO>();
			
			while (rset.next()) {
				//allList.add(new StudyListsDTO(rset.getLong(1), rset.getString(2), rset.getInt(3), rset.getInt(4), rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} finally {
			DBUtil2.close(conn, pstmt, rset);
		}
		
		return allList;
	}
	
	//카테고리별 조회 *
	public boolean getctStdList(String category) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		boolean result = false;
		
		try {
			conn = DBUtil2.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM studylists WHRER category=?");

			pstmt.setString(1, category);
			
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
}
