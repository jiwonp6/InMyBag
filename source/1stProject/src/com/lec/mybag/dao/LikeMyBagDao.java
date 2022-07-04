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
import com.lec.mybag.dto.LikemyBagDto;
import com.lec.mybag.dto.MyBagBoardDto;
import com.lec.mybag.dto.QnaBoardDto;

public class LikeMyBagDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static LikeMyBagDao instance = new LikeMyBagDao();

	public static LikeMyBagDao getInstance() {
		return instance;
	}

	private LikeMyBagDao() {
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

	// (1) 좋아요 누르기
	public int writeLikemybag(String mId, int bId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO LIKEmyBAG " + " (lID, mID, bId)" + "		VALUES (LIKE_SEQ.NEXTVAL, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, bId);
			result = pstmt.executeUpdate();
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

	// (2) 좋아요 취소하기
	public int deleteLikemybag(String mId, int bId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM LIKEmyBAG WHERE mID=? AND bID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, bId);
			result = pstmt.executeUpdate();
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

	// 내 글목록(startRow부터 endRow까지) - 글번호, 작성자, ...
	public ArrayList<MyBagBoardDto> getLikeMyBag(String member_mId, int startRow, int endRow) {
		ArrayList<MyBagBoardDto> lDtos = new ArrayList<MyBagBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT B.* FROM LIKEmyBAG L, myBAGBOARD B "
				+ "    WHERE L.mID=? AND L.BID=B.BID ORDER BY lRDATE DESC) A) WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_mId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String mId = rs.getString("mId");
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bContent = rs.getString("bContent");
				String bFilename = rs.getString("bFilename");
				int bHit = rs.getInt("bHit");
				Timestamp bRdate = rs.getTimestamp("bRdate");
				String bIp = rs.getString("bIp");
				lDtos.add(new MyBagBoardDto(bId, mId, bName, bContent, bFilename, bHit, bRdate, bIp));
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
		return lDtos;
	}
	// 내글갯수
	public int getLikeMyBagTotCnt(String mId) {
		int lCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) lCNT FROM LIKEmyBAG WHERE mID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			rs.next();
			lCnt = rs.getInt(1);
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
		return lCnt;
	}

	//
	public int countLikeMyBag(String mId, int bId) {
		int like = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM LIKEmyBAG WHERE mID=? AND bID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, bId);
			rs = pstmt.executeQuery();
			rs.next();
			like = rs.getInt(1);
			System.out.println(like);
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
		return like;
	}

	// (3)탈퇴처리위함
	public void AllDeleteLikeMyBag(String mId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM LikemyBAG WHERE MID = ?";
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

	// (4) 회원이 좋아요 누른 글만 가져오기
	public ArrayList<MyBagBoardDto> likeListBoard(String mId, int startRow, int endRow) {
		ArrayList<MyBagBoardDto> lDtos = new ArrayList<MyBagBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT B.* FROM myBAGBOARD B, LIKEmyBAG L"
				+ "                                                    WHERE B.BID=L.LID AND L.MID=? ORDER BY lRDATE DESC) A)"
				+ "        WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bContent = rs.getString("bContent");
				String bFilename = rs.getString("qFilename");
				int bHit = rs.getInt("bHit");
				Timestamp bRdate = rs.getTimestamp("bRDate");
				String bIp = rs.getString("bIp");
				lDtos.add(new MyBagBoardDto(bId, mId, bName, bContent, bFilename, bHit, bRdate, bIp));
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
		return lDtos;
	}
}
