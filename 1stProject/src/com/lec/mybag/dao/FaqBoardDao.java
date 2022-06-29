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

import com.lec.mybag.dto.FaqBoardDto;

public class FaqBoardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static FaqBoardDao instance = new FaqBoardDao();

	public static FaqBoardDao getInstance() {
		return instance;
	}
	private FaqBoardDao() {
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
	public ArrayList<FaqBoardDto> fList(){
		ArrayList<FaqBoardDto> fDtos = new ArrayList<FaqBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + " (SELECT ROWNUM RN, A.* FROM " + " (SELECT F.* FROM FAQBOARD F"
				+ " ORDER BY fID DESC) A)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int fId = rs.getInt("fId");
				String aId = rs.getString("aId");
				String fTitle = rs.getString("fTitle");
				String fContent = rs.getString("fContent");
				String fFilename = rs.getString("fFilename");
				int fHit = rs.getInt("fHit");
				Timestamp fRdate = rs.getTimestamp("fRdate");
				String fIp = rs.getString("fIp");
				fDtos.add(new FaqBoardDto(fId, aId, fTitle, fContent, fFilename, fHit, fRdate, fIp));
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
		return fDtos;
	}
	//
	public ArrayList<FaqBoardDto> fListBoard(int startRow, int endRow) {
		ArrayList<FaqBoardDto> fDtos = new ArrayList<FaqBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + " (SELECT ROWNUM RN, A.* FROM " + " (SELECT F.* FROM FAQBOARD F"
				+ " ORDER BY fID DESC) A)" + " WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int fId = rs.getInt("fId");
				String aId = rs.getString("aId");
				String fTitle = rs.getString("fTitle");
				String fContent = rs.getString("fContent");
				String fFilename = rs.getString("fFilename");
				int fHit = rs.getInt("fHit");
				Timestamp fRdate = rs.getTimestamp("fRdate");
				String fIp = rs.getString("fIp");
				fDtos.add(new FaqBoardDto(fId, aId, fTitle, fContent, fFilename, fHit, fRdate, fIp));
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
		return fDtos;
	}

	// (2) 글갯수
	public int getFaqBoardTotCnt() {
		int nCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) fCNT FROM FAQBOARD";
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
	public int writeFaqBoard(String aId, String fTitle, String fContent, String fFilename, String fIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FAQBOARD " + " (fID, aID, fTITLE, fCONTENT, fFILENAME, fIP) "
				+ " 	VALUES (FAQ_SEQ.NEXTVAL, ?, ?, ? , ?, " + "		FAQ_SEQ.CURRVAL, 0, 0, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, fTitle);
			pstmt.setString(3, fContent);
			pstmt.setString(4, fFilename);
			pstmt.setString(5, fIp);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "FAQ게시판 글쓰기성공" : "FAQ게시판 글쓰기실패");
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

	// (4) fHit 하나 올리기(1번글 조회수 하나 올리기)
	private void fHitUp(int fId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FAQBOARD SET fHIT = fHIT +1 WHERE fID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fId);
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

	// (5) fId로 글 dto보기 : 글 상세보기(조회수 up + fid로 dto리턴)
	public FaqBoardDto fContentView(int fId) {
		fHitUp(fId);
		FaqBoardDto fDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * " + " FROM FAQBOARD "
				+ " WHERE fID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aId = rs.getString("aId");
				String fTitle = rs.getString("fTitle");
				String fContent = rs.getString("fContent");
				String fFilename = rs.getString("fFilename");
				int fHit = rs.getInt("fHit");
				Timestamp fRdate = rs.getTimestamp("fRdate");
				String fIp = rs.getString("fIp");
				fDto = new FaqBoardDto(fId, aId, fTitle, fContent, fFilename, fHit, fRdate, fIp);
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
		return fDto;
	}

	// (5) nId로 글 dto보기 : 답변글 view + 수정 view (nId로 dto리턴)
	public FaqBoardDto modifyView_replyView(int fId) {
		FaqBoardDto nDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT fID, aID, fTITLE, fCONTENT, fFILENAME, fHIT, fRDATE, fIP " + " FROM FAQBOARD "
				+ " WHERE fID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aId = rs.getString("aId");
				String fTitle = rs.getString("fTitle");
				String fContent = rs.getString("fContent");
				String fFilename = rs.getString("fFilename");
				int fHit = rs.getInt("fHit");
				Timestamp fRdate = rs.getTimestamp("fRdate");
				String fIp = rs.getString("fIp");
				nDto = new FaqBoardDto(fId, aId, fTitle, fContent, fFilename, fHit, fRdate, fIp);
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

	// (6) 글 수정하기(fId, fTitle, fContent, fFILENAME, fIp, fRDATE)
	public int modifyFaqBoard(int fId, String fTitle, String fContent, String fFilename, String fIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FAQBOARD SET fTITLE = ?, " 
				+ "                    fCONTENT = ?, "
				+ "                    fFILENAME = ?, " 
				+ "                    fRDATE = SYSDATE, "
				+ "                    fIP = ? " 
				+ "            WHERE fID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fTitle);
			pstmt.setString(2, fContent);
			pstmt.setString(3, fFilename);
			pstmt.setString(4, fIp);
			pstmt.setInt(5, fId);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "Faq글 수정 성공" : "Faq글 수정 실패");
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
	public int deleteFaqBoard(int fId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM FAQBOARD WHERE fID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fId);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "Faq게시판 글삭제성공" : "Faq게시판 글삭제실패");
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
