//DAO의 각 메소드들에게 중복되는 코드를 분리해서 개발하는 클래스

package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil2 {
	private static DataSource ds;

	static {
		try {
			Context envContext = (Context) (new InitialContext()).lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/mysql");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}

	}
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	public static void close(Connection con, Statement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}

	public static void close(Connection con, Statement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}

	public static void close(Connection con, Statement pstmt, Statement pstmt1, ResultSet rs) {
		try {
			if (pstmt1 != null) {
				pstmt1.close();
				pstmt1 = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}
}