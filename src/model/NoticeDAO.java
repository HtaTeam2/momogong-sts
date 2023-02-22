package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	public static Notice insertNotice(Notice ndto) throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Notice notice = null;
		try {
			tx.begin();
			notice = new Notice();
			notice.setViewCount(0);
			notice.setNoticeTitle(ndto.getNoticeTitle());
			notice.setNoticeContent(ndto.getNoticeContent());
			em.persist(notice);
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();

		} finally {
			em.close();
		}

		return notice;
	}

	// 글 수정
	public void updateNotice(Notice no) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		Notice notice = null;

		try {
			tx.begin();
			notice = em.find(Notice.class, no.getNoticeNo());
			// 공지사항이 존재하면 null 아님, 내용 변경
			if (notice != null) {
				System.out.println("update 전 : " + notice);
				notice.setNoticeContent(no.getNoticeContent()); // 게시물
				notice.setNoticeTitle(no.getNoticeTitle()); // 제목
			} else {
				System.out.println("업데이트 하려는 게시물을 찾지 못했습니다");
			}
			em.persist(notice);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	// 글 삭제
	public void deleteNotice(Long noticeNo) {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Notice notice = em.find(Notice.class, noticeNo);
			if (notice != null) {
				em.remove(notice);
			} else {
				System.out.println("이미 삭제 된 게시글입니다.");
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	// 상세조회
	public NoticeDTO view(Long noticeNo, boolean flag) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		NoticeDTO dto = null;
		try {
			con = DBUtil2.getConnection();

			if (flag) {
				pstmt = con.prepareStatement("update notice set ViewCount = ViewCount + 1 where noticeNo = ?");
				pstmt.setLong(1, noticeNo);
				if (pstmt.executeUpdate() == 0) {
					pstmt.close();
					pstmt = null;
				}
			}
			pstmt = con.prepareStatement("select * from notice where noticeNo = ?");
			pstmt.setLong(1, noticeNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				dto = new NoticeDTO(noticeNo, rset.getString("noticeContent"), rset.getDate("noticeRegdate"),
						rset.getString("noticeTitle"), rset.getInt("viewCount"));
			}
		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil2.close(con, pstmt, rset);
		}
		return dto;

	}

//전체 게시글 조회
	public List<Notice> list() throws Exception {
		EntityManager em = DBUtil.getEntityManager();

		List<Notice> list = null;
		try {
			String sql = "select n from Notice n";
			list = em.createQuery(sql).getResultList();

			list.forEach(v -> System.out.println(v));

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
		return list;
	}

}