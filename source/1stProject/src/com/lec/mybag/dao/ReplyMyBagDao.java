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

import com.lec.mybag.dto.ItemBoardDto;
import com.lec.mybag.dto.QnaBoardDto;
import com.lec.mybag.dto.ReplyMyBagDto;

public class ReplyMyBagDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static ReplyMyBagDao instance = new ReplyMyBagDao();

	public static ReplyMyBagDao getInstance() {
		return instance;
	}

	private ReplyMyBagDao() {
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

	// (1) 글목록(bId로) - 글번호, 작성자이름, ...
	public ArrayList<ReplyMyBagDto> rListBoard(int bId, int startRow, int endRow) {
		ArrayList<ReplyMyBagDto> rDtos = new ArrayList<ReplyMyBagDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + " (SELECT ROWNUM RN, A.* FROM " + " (SELECT * FROM REPLYmyBAG "
				+ " WHERE bID=? ORDER BY rGROUP DESC, rSTEP) A)" + " WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rId = rs.getInt("rId");
				String mId = rs.getString("mId");
				String rContent = rs.getString("rContent");
				Timestamp rRdate = rs.getTimestamp("rRDate");
				int rGroup = rs.getInt("rGroup");
				int rStep = rs.getInt("rStep");
				int rIndent = rs.getInt("rIndent");
				String rIp = rs.getString("rIp");
				rDtos.add(new ReplyMyBagDto(rId, mId, bId, rContent, rRdate, rGroup, rStep, rIndent, rIp));
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
		return rDtos;
	}
	
	
	// (2) 글갯수
	public int getReplyMyBagTotCnt(int bId) {
		int rCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) rCNT FROM REPLYmyBAG WHERE bID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			rs = pstmt.executeQuery();
			rs.next();
			rCnt = rs.getInt(1);
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
		return rCnt;
	}

	// (3) 글쓰기(댓글)
	public int writeReplyMyBag(String mId, int bId, String rContent, String rIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REPLYmyBAG " + " (rID, mID, bID, rCONTENT, rGROUP, rSTEP, rINDENT, rIP) " 
					+ "		VALUES (REPLY_SEQ.NEXTVAL, ?, ?, ? , "
					+ "		REPLY_SEQ.CURRVAL, 0, 0, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, bId);
			pstmt.setString(3, rContent);
			pstmt.setString(4, rIp);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "댓글 글쓰기성공" : "댓글 글쓰기실패");
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
	//글수정
	public ReplyMyBagDto modifyView_replyView(int rId) {
		ReplyMyBagDto rDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM REPLYmyBAG " + " WHERE rID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mId = rs.getString("mId");
				int bId = rs.getInt("bId");
				String rContent = rs.getString("rContent");
				Timestamp rRdate = rs.getTimestamp("rRdate");
				int rGroup = rs.getInt("rGroup");
				int rStep = rs.getInt("rStep");
				int rIndent = rs.getInt("rIndent");
				String rIp = rs.getString("rIp");
				rDto = new ReplyMyBagDto(rId, mId, bId, rContent, rRdate, rGroup, rStep, rIndent, rIp);
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
		return rDto;
	}

	// (5) 글 수정하기(rId, rContent, rRdate, rGroup, rStep, rIndent, rIp)
	public int modifyReplyMyBag(int rId, String rContent, String rIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REPLYmyBAG SET rCONTENT = ?, "
					+ "                    rRDATE = SYSDATE, "
					+ "                    rIP = ? " 
					+ "            WHERE rID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rContent);
			pstmt.setString(2, rIp);
			pstmt.setInt(3, rId);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "댓글수정성공" : "댓글수정실패");
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

	// (7) 글 삭제하기(qId로 삭제하기)
	public int deleteReplyMyBag(int rId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM REPLYmyBAG WHERE rID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rId);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "댓글삭제성공" : "댓글삭제실패");
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

	// (8) 댓글의 댓글 추가전 STEP a 수행
	private void rpreReplyStepA(int rGroup, int rStep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REPLYmyBAG SET rSTEP = rSTEP+1  " 
				+ "    WHERE rGROUP = ? AND rSTEP > ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rGroup);
			pstmt.setInt(2, rStep);
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

	// (9) 댓글의 댓글 쓰기
	public int replyReplyMyBag(String mId, String rContent, int rGroup, int rStep, int rIndent, String rIp) {
		rpreReplyStepA(rGroup, rStep); // 답변글 저장전 step A 먼저 실행
		// qgroup, qstep, qindent 원글정보
		// aid, qtitle, qcontent, qip 답변글 정보
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REPLYmyBAG " + "(rID, mID, rCONTENT, rGROUP, rSTEP, rINDENT, rIP) "
				+ " VALUES (REPLY_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, rContent);
			pstmt.setInt(3, rGroup);
			pstmt.setInt(4, rStep + 1);
			pstmt.setInt(5, rIndent + 1);
			pstmt.setString(6, rIp);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "댓글의 댓글쓰기성공" : "댓글의 댓글쓰기실패");
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

	// (10) 회원탈퇴 하기 전 회원이 쓴 글 모두 삭제 후 탈퇴
	public void AllDeleteReplyMyBag(String mId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM REPLYmyBAG WHERE MID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.executeUpdate();
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
	}

}
