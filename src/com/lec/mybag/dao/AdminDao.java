package com.lec.mybag.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lec.mybag.dto.AdminDto;
import com.lec.mybag.dto.MemberDto;

public class AdminDao {
	public static final int EXISTENT = 0;
	public static final int NONEXISTENT = 1;
	public static final int LOGIN_FAIL = 0;
	public static final int LOGIN_SUCCESS = 1;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public static final int NOT_KING = 0; 
	public static final int KING = 1; 
	private static AdminDao instance = new AdminDao();

	public static AdminDao getInstance() {
		return instance;
	}

	private AdminDao() {
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	// (1) 로그인 체크
	public int aloginCheck(String aId, String aPw) {
		int result = LOGIN_FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ADMIN WHERE aID=? and aPW=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, aPw);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = LOGIN_SUCCESS;
			} else {
				result = LOGIN_FAIL;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// (2) aid로 dto가져오기(로그인 성공시 session에 넣기 위함)
	public AdminDto getAdmin(String aId) {
		AdminDto admin = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ADMIN WHERE aId=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aPw = rs.getString("aPw");
				String aName = rs.getString("aName");
				int aKing = rs.getInt("aKing");
				admin = new AdminDto(aId, aPw, aName, aKing);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return admin;
	}

	// (3) 직원id 중복체크
	public int aIdConfirm(String aId) {
		int result = EXISTENT;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ADMIN WHERE aId=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = EXISTENT;
			} else {
				result = NONEXISTENT;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// (4) 회원가입
	public int joinAdmin(AdminDto admin) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ADMIN " + "(aID, aPw, aName) " + "VALUES (?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin.getaId());
			pstmt.setString(2, admin.getaPw());
			pstmt.setString(3, admin.getaName());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// (5) 직원정보 수정
	public int modifyAdmin(AdminDto admin) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ADMIN SET aPw = ?," 
				+ "                    aName = ?" 
				+ "        WHERE aId=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin.getaPw());
			pstmt.setString(2, admin.getaName());
			pstmt.setString(3, admin.getaId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage()+"수정이 안되네");
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage()+"수정X");
			}
		}
		return result;
	}

	// (6) 직원탈퇴
	public int withdrawal(String aId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM ADMIN WHERE aID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// (7) 최고관리자 확인
	public int aKingChk(String aId) {
		int result = EXISTENT;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ADMIN WHERE aId=? AND aKING=1";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = KING;
			} else {
				result = NOT_KING;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	//관리자검색
		public ArrayList<AdminDto> searchAdmin(String search_aId, int startRow, int endRow){
			ArrayList<AdminDto> sch_admin = new ArrayList<AdminDto>();
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM " 
					 +     "         (SELECT * FROM ADMIN WHERE aID LIKE '%'||?||'%' ORDER BY aKING DESC, aID) A)" 
					 +     "  WHERE RN BETWEEN ? AND ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, search_aId);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					String aId = rs.getString("aId");
					String aPw = rs.getString("aPw");
					String aName = rs.getString("aName");
					int aKing = rs.getInt("aKing");
					sch_admin.add(new AdminDto(aId, aPw, aName, aKing));
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(rs    != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn  != null) conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return sch_admin;
		}
		//검색된 관리자수
		public int getSearchAdminTotCnt(String search_aId) {
			int totCnt = 0;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT COUNT(*) FROM ADMIN where aId like '%'||?||'%'";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, search_aId);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					totCnt = rs.getInt(1);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(rs    != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn  != null) conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return totCnt;
		}
		// (6) 관리자리스트(top-N구문)
		public ArrayList<AdminDto> allAdmin(int startRow, int endRow){
			ArrayList<AdminDto> admins = new ArrayList<AdminDto>();
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM " 
					 +     "         (SELECT * FROM ADMIN ORDER BY aKING DESC, aID) A)" 
					 +     "  WHERE RN BETWEEN ? AND ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					String aId = rs.getString("aId");
					String aPw = rs.getString("aPw");
					String aName = rs.getString("aName");
					int aKing = rs.getInt("aKing");
					admins.add(new AdminDto(aId, aPw, aName, aKing));
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(rs    != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn  != null) conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return admins;
		}
		// (7) 관리자수
		public int getAdminTotCnt() {
			int totCnt = 0;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT COUNT(*) FROM ADMIN";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					totCnt = rs.getInt(1);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(rs    != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn  != null) conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return totCnt;
		}
	

}
