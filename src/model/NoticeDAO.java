package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import model.domain.NoticeDTO;
import util.DBUtil;
import util.DBUtil2;

@Repository
public class NoticeDAO {
	
<<<<<<< main
	private static NoticeDAO instance = new NoticeDAO();
	
	public static NoticeDAO getInstance() {
		return instance;
	}
=======
	// 글 추가
	public void insert(NoticeDTO ndto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement("INSERT INTO notice VALUES(?,?,?,?,?)");
			
			pstmt.setLong(1, ndto.getNoticeNo());
			pstmt.setString(2, ndto.getNoticeContent());
			pstmt.setDate(3, ndto.getNoticeRegdate());
			pstmt.setString(4, ndto.getNoticeTitle());
			pstmt.setInt(5, ndto.getViewCount());
			
			pstmt.executeUpdate();
			
		} catch (SQLException s) {
//			s.printStackTrace();
			throw s;
		} finally {
			DBUtil2.close(con, pstmt);
		}
	}
	
	
	// 글 수정
	
	public void update(NoticeDTO ndto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil2.getConnection();

			pstmt = con.prepareStatement("UPDATE notice SET noticeContent = ? , noticeTitle = ?");
		//	pstmt.setLong(1, ndto.getNoticeNo());
			pstmt.setString(1, ndto.getNoticeContent());
		//	pstmt.setDate(3, ndto.getNoticeRegdate());
			pstmt.setString(2, ndto.getNoticeTitle());

			pstmt.executeUpdate(); //insert/update/delete
>>>>>>> local

		} catch (SQLException s) {
//			s.printStackTrace();
			throw s;
		} finally {
			DBUtil2.close(con, pstmt);
		}
	}
	
	// 글 삭제
	public boolean delete(Long noticeNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement("delete from notice where noticeNo=?");
			pstmt.setLong(1, noticeNo);

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
	
	// 조회
	public ArrayList<NoticeDTO> getNotice() throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<NoticeDTO> allList = null;
		
		try {
			conn = DBUtil2.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM notice");
			rset = pstmt.executeQuery();
			
			allList = new ArrayList<NoticeDTO>();
			
			while (rset.next()) {
				allList.add(new NoticeDTO(rset.getLong(1), rset.getString(2), rset.getDate(3), rset.getString(4), rset.getInt(5)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} finally {
			DBUtil2.close(conn, pstmt, rset);
		}
		
		return allList;
	}
	
}
