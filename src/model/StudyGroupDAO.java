package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import model.domain.MyStudyDTO;
import model.domain.StudyGroupMembersDTO;
import util.DBUtil2;

@Repository
public class StudyGroupDAO {
	
	//가입. 리스트 update -> 인원수 증가시켜야 함. 그럼 초기값을 0으로 줘야겠다
	public boolean joinGroup(String joinId, long roomNo) throws SQLException, Exception {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		
		try {
			con = DBUtil2.getConnection();
			con.setAutoCommit(false);
			pstmt1 = con.prepareStatement("INSERT INTO studygroup(memNo, studylists_no, studymembers_id) VALUES(STUDYGROUP_SEQ.NEXTVAL, ? , ?)");
			//조건을 아래와 같이 걸어준 뒤 update되지 않으면 rollback. 문제 -> SQL 오류가 나는게 아니라 업데이트만 안됨
			pstmt2 = con.prepareStatement("UPDATE studylists SET memNum = (memNum + 1) WHERE roomNo = ? and memNum < maxMem");
			pstmt1.setLong(1, roomNo);
			pstmt1.setString(2, joinId);
			pstmt2.setLong(1, roomNo);
			pstmt1.executeUpdate();
			int result = pstmt2.executeUpdate();
			//실행된 sql문이 0일때 예외 발생. 알림창에 뜨도록?
			if(result == 0) {
				con.rollback();
				throw new Exception("인원을 초과하여 가입하실 수 없습니다.");
			}else {
				con.commit();
			}
		} catch (SQLException s) {
			s.printStackTrace();
			con.rollback();
			throw new SQLException("내부적인 오류로 가입에 실패하였습니다. 잠시 후 재시도 해주십시오. ");
		} finally {
			DBUtil2.close(con, pstmt1, pstmt2);
		}
		return false;
	}
	
	//그룹원 조회. join문을 써서 닉네임 뽑음
	public ArrayList<StudyGroupMembersDTO> getCustomers(long roomNum) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<StudyGroupMembersDTO> allList = null;
		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement("SELECT g.studylists_no, m.id, m.nickname, m.goal FROM studygroup g, studymembers m WHERE g.studymembers_id = m.id AND g.studylists_no = ?;");
			pstmt.setLong(1, roomNum);
			rset = pstmt.executeQuery();
			
			allList = new ArrayList<>();
			
			while (rset.next()) {
				//allList.add(new StudyGroupMembersDTO(roomNum, rset.getString(2), rset.getString(3), rset.getString(4)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} finally {
			DBUtil2.close(con, pstmt, rset);
		}
		return allList;
	}
	
	//방 탈퇴: 호스트면 방 전체 삭제, 멤버면 그룹만 탈퇴, 가입한 방이 여러개인 것을 고려해서 방번호도 가져옴
	//만약에 방 전체가 삭제 되면 List도 삭제해야 하니까 URL 보냄
	public int delete(String id, long roomNum) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rset = null;
		int result = 0;
		try {
			con = DBUtil2.getConnection();
			pstmt1 = con.prepareStatement("SELECT memberid FROM studylists where roomNo = ?"); //방번호 찾기
			pstmt2 = con.prepareStatement("DELETE FROM studygroup WHERE studylists_no = ?"); //해당 방 전체 탈퇴
			pstmt3 = con.prepareStatement("DELETE FROM studygroup WHERE studymembers_id = ? AND studylists_no = ? "); //해당 방의 해당 아이디만 탈퇴
			pstmt4 = con.prepareStatement("UPDATE studylists SET memNum = (memNum - 1) WHERE roomNo = ?"); //후 list테이블 멤버수 -1
			
			pstmt1.setLong(1, roomNum);
			rset = pstmt1.executeQuery();	//id 추출
			//id 비교를 위한 조건문
			if(rset.next()) {
				if((rset.getString(1)).equals(id)){
					//일치하면 방 관리자 = 해당 방번호 전체 삭제
					pstmt2.setLong(1, roomNum);
					pstmt2.executeUpdate();
					result = 1;
				}else {
					//일치하지 않으면 방관리자X update후  바로 main페이지로 이동
					pstmt3.setString(1, id);
					pstmt3.setLong(2, roomNum);
					pstmt3.executeUpdate();
					pstmt4.setLong(1, roomNum);
					pstmt4.executeUpdate();
					result = 2;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
			throw se;
		} finally {
			DBUtil2.close(con, pstmt1);
		}
		return result;
	}
	
	//mystudy
	public ArrayList<MyStudyDTO> getMyStudy(String id) throws SQLException{
		System.out.println("DAO getMyStudy " + id);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MyStudyDTO> allList = null;
		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement("SELECT g.studyListNo, l.roomTitle, m.id, m.nickname, m.goal FROM studylists l, studygroup g, studymembers m WHERE g.studymembers_id = m.id AND g.studyListNo = l.roomNo AND m.id = ?;");
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			
			allList = new ArrayList<MyStudyDTO>();
			while (rset.next()) {
				//allList.add(new MyStudyDTO(rset.getLong(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
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
