package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Repository;

import model.domain.NoticeDTO;
import model.domain.entity.Notice;
import util.DBUtil;
import util.DBUtil2;

@Repository
public class NoticeDAO {
	
	// 글 추가
	// 일단 글 추가만 (관리자 설정은 나중에)
	public void insertNotice(NoticeDTO dto)  {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			Notice notice = new Notice(dto.getNoticeTitle(), dto.getNoticeContent(), dto.getViewCount());
			em.persist(notice);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
	}

	// 글 수정
	// 내용, 제목만 변경 
	public boolean updateNotice(NoticeDTO dto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil2.getConnection();

			pstmt = con.prepareStatement("UPDATE notice SET noticeContent = ? , noticeTitle = ?");			
			pstmt.setString(1, dto.getNoticeContent());
			pstmt.setString(2, dto.getNoticeTitle());
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
	
		// 글 삭제
		//Long noticeNo
		public void deleteNotice(Long noticeNo) {
			EntityManager em = DBUtil.getEntityManager();
			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				Notice notice = em.find(Notice.class, noticeNo);
				if (notice != null) {
					em.remove(notice);
				}else {
					System.out.println("이미 삭제 된 게시글입니다.");
				}
				tx.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				em.close();
			}
		}
	
	// 상세조회
		public NoticeDTO view(Long noticeNo, boolean flag) throws SQLException{
			Connection con = null;	
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			NoticeDTO dto = null;
			try {
				con = DBUtil2.getConnection();
				
				if(flag) {
					pstmt = con.prepareStatement("update notice set ViewCount = ViewCount + 1 where noticeNo = ?");
					pstmt.setLong(1, noticeNo);
					if(pstmt.executeUpdate() == 0) {
						pstmt.close();
						pstmt = null;
						return dto; //여기서 메소드 종료
					}
				}
				pstmt = con.prepareStatement("select * from notice where noticeNo = ?");
				pstmt.setLong(1, noticeNo);
				rset = pstmt.executeQuery();
				if(rset.next()) {
					dto = new NoticeDTO(noticeNo, rset.getString("noticeContent"), rset.getDate("noticeRegdate"), rset.getString("noticeTitle"), rset.getInt("viewCount"));
				}
			}catch(SQLException s) {
				s.printStackTrace();
				throw s;
			}finally {
				DBUtil2.close(con, pstmt, rset);
			}
			return dto;
			
		}

	// 전체 글 조회
		public ArrayList<NoticeDTO> list() throws SQLException{
			Connection con = null;	
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<NoticeDTO> list = null;
			try {
				con = DBUtil2.getConnection();
				pstmt = con.prepareStatement("select * from notice");
				rset = pstmt.executeQuery();
				
				list = new ArrayList<NoticeDTO>();
				
				while(rset.next()) {
					list.add(new NoticeDTO(rset.getLong("noticeNo"), rset.getString("noticeContent"), rset.getDate("noticeRegdate"), rset.getString("noticeTitle"), rset.getInt("viewCount") 
							 ));
				}
			}catch(SQLException s) {
				s.printStackTrace();
				throw s;
			}finally {
				DBUtil2.close(con, pstmt, rset);
			}
			return list;
		}
		
	
}
