package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Repository;

import model.domain.CommunityDTO;
import model.domain.entity.Community;
import model.domain.entity.StudyMembers;
import util.DBUtil;
import util.DBUtil2;

@Repository
public class CommunityDAO {

	// 입력
	public CommunityDTO write(CommunityDTO dto) throws Exception {
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			StudyMembers member = em.find(StudyMembers.class, dto.getMemberid());
			Community community = new Community(dto.getComTitle(), dto.getSubject(), dto.getComContent(),
					dto.getComPw(), 0, 0);
			community.setStudymembers(member);
			em.persist(community);// entity 영속성 저장

			CommunityDTO dto2 = CommunityDTO.fromEntity(community);// entity를 dto로 변환

			tx.commit();// db까지저장

			return dto2;// entity를 dto로 변환한 것 전송

		} catch (Exception e) {
			tx.rollback();
			throw e;
		} finally {
			em.close();
		}

	}

	// 읽기
	public CommunityDTO view(long comNo, boolean flag) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CommunityDTO dto = null;
		try {
			con = DBUtil2.getConnection();

			if (flag) {
				pstmt = con.prepareStatement("update community set comViewCount = comViewCount + 1 where comNo = ?");
				pstmt.setLong(1, comNo);
				if (pstmt.executeUpdate() == 0) {
					pstmt.close();
					pstmt = null;
					return dto; // 여기서 메소드 종료
				}
			}
			pstmt = con.prepareStatement("select * from community where comNo = ?");
			pstmt.setLong(1, comNo);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				dto = new CommunityDTO(comNo, rset.getString("comTitle"), rset.getString("memberid"),
						rset.getString("subject"), rset.getString("comPw"), rset.getDate("comRegdate"),
						rset.getString("comContent"), rset.getInt("comViewCount"), rset.getInt("recommCount"));
			}
		} catch (SQLException s) {
			s.printStackTrace();
			throw s;
		} finally {
			DBUtil2.close(con, pstmt, rset);
		}
		return dto;

	}

	// 목록
	public List<CommunityDTO> list() throws Exception {
		EntityManager em = DBUtil.getEntityManager();

		List<Community> list = null;
		List<CommunityDTO> list2 = new ArrayList<CommunityDTO>();
		try {
			String sql = "select c from Community c order by c.comNo desc";
			list = em.createQuery(sql).getResultList();
			System.out.println("list");
			// list.forEach(v->System.out.println(v));
			for (Community v : list) {
				list2.add(CommunityDTO.fromEntity(v));
				System.out.println(list);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			em.close();
		}
		return list2;
	}

	// 수정
	public boolean update(CommunityDTO dto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement(
					"update community set comTitle = ?, subject = ?, comContent = ? where comNo = ? and comPw = ?");
			pstmt.setString(1, dto.getComTitle());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getComContent());
			pstmt.setLong(4, dto.getComNo());
			pstmt.setString(5, dto.getComPw());

			int result = pstmt.executeUpdate();
			if (result != 0) {
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

	// 삭제
	public boolean delete(long comNo, String comPw) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil2.getConnection();
			pstmt = con.prepareStatement("delete from community where comNo = ? and comPw = ?");
			pstmt.setLong(1, comNo);
			pstmt.setString(2, comPw);

			int result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (SQLException s) {
			s.printStackTrace();
			DBUtil2.close(con, pstmt);
			throw new Exception("게시물이 존재하지 않거나 비밀번호가 틀렸습니다.");
		}
		return false;
	}

	// 검색
	public List<CommunityDTO> search(String searchType, String searchText) {
		EntityManager em = DBUtil.getEntityManager();
		List<Community> all = null;
		List<CommunityDTO> all2 = new ArrayList<CommunityDTO>();

		if ("title".equals(searchType)) {
			all = em.createNamedQuery("Community.findByComTitle").setParameter("comTitle", "%" + searchText + "%")
					.getResultList();
//			System.out.println(all);

		} else if ("content".equals(searchType)) {
			all = em.createNamedQuery("Community.findByComContent").setParameter("comContent", "%" + searchText + "%")
					.getResultList();
//			System.out.println(all);

		} else if ("writer".equals(searchType)) {
			all = em.createNamedQuery("Community.findByMemberid").setParameter("memberid", "%" + searchText + "%")
					.getResultList();
			System.out.println(all);
		}
		for (Community v : all) {
			all2.add(CommunityDTO.fromEntity(v));
			System.out.println(all2);
		}
		return all2;
	}

}
