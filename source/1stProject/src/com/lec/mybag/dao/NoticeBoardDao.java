package com.lec.mybag.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lec.mybag.dto.NoticeBoardDto;

public class NoticeBoardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static NoticeBoardDao instance = new NoticeBoardDao();

	public static NoticeBoardDao getInstance() {
		return instance;
	}

	private NoticeBoardDao() {
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

	// (1) 글목록(startRow부터 endRow까지) - 글번호, 작성자이름, ...
	public ArrayList<NoticeBoardDto> nListBoard(int startRow, int endRow) {
		ArrayList<NoticeBoardDto> nDtos = new ArrayList<NoticeBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + " (SELECT ROWNUM RN, A.* FROM " + " (SELECT N.* FROM NOTICEBOARD N"
				+ " ORDER BY nID DESC) A)" + " WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int nId = rs.getInt("nId");
				String aId = rs.getString("aId");
				String nTitle = rs.getString("nTitle");
				String nContent = rs.getString("nContent");
				String nFilename = rs.getString("nFilename");
				int nHit = rs.getInt("nHit");
				Timestamp nRdate = rs.getTimestamp("nRDate");
				String nIp = rs.getString("nIp");
				nDtos.add(new NoticeBoardDto(nId, aId, nTitle, nContent, nFilename, nHit, nRdate, nIp));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return nDtos;
	}

	// (2) 글갯수
	public int getNoticeBoardTotCnt() {
		int nCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) nCNT FROM NOTICEBOARD";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			nCnt = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return nCnt;
	}

	// (3) 글쓰기(원글)
	public int writeNoticeBoard(String aId, String nTitle, String nContent, String nFilename, String nIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO NOTICEBOARD " + " (nID, aID, nTITLE, nCONTENT, nFILENAME, nIP) "
				+ " 	VALUES (NOTICE_SEQ.NEXTVAL, ?, ?, ? , ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, nTitle);
			pstmt.setString(3, nContent);
			pstmt.setString(4, nFilename);
			pstmt.setString(5, nIp);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "NOTICE게시판 글쓰기성공" : "NOTICE게시판 글쓰기실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// (4) nHit 하나 올리기(1번글 조회수 하나 올리기)
	private void nHitUp(int nId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICEBOARD SET nHIT = nHIT +1 WHERE nID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// (5) nId로 글 dto보기 : 글 상세보기(조회수 up + nid로 dto리턴)
	public NoticeBoardDto nContentView(int nId) {
		nHitUp(nId);
		NoticeBoardDto nDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * " + " FROM NOTICEBOARD "
				+ " WHERE nID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aId = rs.getString("aId");
				String nTitle = rs.getString("nTitle");
				String nContent = rs.getString("nContent");
				String nFilename = rs.getString("nFilename");
				int nHit = rs.getInt("nHit");
				Timestamp nRdate = rs.getTimestamp("nRdate");
				String nIp = rs.getString("nIp");
				nDto = new NoticeBoardDto(nId, aId, nTitle, nContent, nFilename, nHit, nRdate, nIp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return nDto;
	}

	// (5) nId로 글 dto보기 : 답변글 view + 수정 view (nId로 dto리턴)
	public NoticeBoardDto modifyView_replyView(int nId) {
		NoticeBoardDto nDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT nID, aID, nTITLE, nCONTENT, nFILENAME, nHIT, nRDATE, nIP " + " FROM NOTICEBOARD "
				+ " WHERE nID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aId = rs.getString("aId");
				String nTitle = rs.getString("nTitle");
				String nContent = rs.getString("nContent");
				String nFilename = rs.getString("nFilename");
				int nHit = rs.getInt("nHit");
				Timestamp nRdate = rs.getTimestamp("nRdate");
				String nIp = rs.getString("nIp");
				nDto = new NoticeBoardDto(nId, aId, nTitle, nContent, nFilename, nHit, nRdate, nIp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return nDto;
	}

	// (6) 글 수정하기(nId, nTitle, nContent, nFILENAME, nIp, nRDATE)
	public int modifyNoticeBoard(int nId, String nTitle, String nContent, String nFilename, String nIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICEBOARD SET nTITLE = ?, " 
				+ "                    nCONTENT = ?, "
				+ "                    nFILENAME = ?, " 
				+ "                    nRDATE = SYSDATE, "
				+ "                    nIP = ? " 
				+ "            WHERE nID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nTitle);
			pstmt.setString(2, nContent);
			pstmt.setString(3, nFilename);
			pstmt.setString(4, nIp);
			pstmt.setInt(5, nId);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "NOTICE글 수정 성공" : "NOTICE글 수정 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// (7) 글 삭제하기(nId로 삭제하기)
	public int deleteNoticeBoard(int nId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM NOITCEBOARD WHERE nID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nId);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "NOTICE게시판 글삭제성공" : "NOTICE게시판 글삭제실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
}
