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

import com.lec.mybag.dto.MyBagBoardDto;
import com.lec.mybag.dto.ReplyMyBagDto;


public class MyBagBoardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static MyBagBoardDao instance = new MyBagBoardDao();

	public static MyBagBoardDao getInstance() {
		return instance;
	}

	private MyBagBoardDao() {
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
	public ArrayList<MyBagBoardDto> bListBoard(int startRow, int endRow) {
		ArrayList<MyBagBoardDto> bDtos = new ArrayList<MyBagBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + " (SELECT ROWNUM RN, A.* FROM " + " (SELECT B.* FROM myBAGBOARD B "
				+ " ORDER BY bId DESC) A)" + " WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int bId = rs.getInt("bId");
				String mId = rs.getString("mId");
				String bName = rs.getString("bName");
				String bContent = rs.getString("bContent");
				String bFilename = rs.getString("bFilename");
				int bHit = rs.getInt("bHit");
				Timestamp bRdate = rs.getTimestamp("bRDate");
				String bIp = rs.getString("bIp");
				bDtos.add(new MyBagBoardDto(bId, mId, bName, bContent, bFilename, bHit, bRdate, bIp));
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
		return bDtos;
	}
	// (2) 글갯수
		public int getMyBagBoardTotCnt() {
			int bCnt = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT COUNT(*) bCNT FROM myBAGBOARD";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				rs.next();
				bCnt = rs.getInt(1);
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
			return bCnt;
		}
	//
	public ArrayList<MyBagBoardDto> bMyListBoard(String mId, int bstartRow, int bendRow) {
	ArrayList<MyBagBoardDto> bDtos = new ArrayList<MyBagBoardDto>();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "SELECT * FROM " + " (SELECT ROWNUM RN, A.* FROM " + " (SELECT B.* FROM myBAGBOARD B "
			+ " WHERE mID= ? ORDER BY bId DESC) A)" + " WHERE RN BETWEEN ? AND ?";
	try {
		conn = getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mId);
		pstmt.setInt(2, bstartRow);
		pstmt.setInt(3, bendRow);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			int bId = rs.getInt("bId");
			String bName = rs.getString("bName");
			String bContent = rs.getString("bContent");
			String bFilename = rs.getString("bFilename");
			int bHit = rs.getInt("bHit");
			Timestamp bRdate = rs.getTimestamp("bRDate");
			String bIp = rs.getString("bIp");
			bDtos.add(new MyBagBoardDto(bId, mId, bName, bContent, bFilename, bHit, bRdate, bIp));
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
	return bDtos;
}
	// 내글갯수
		public int getMybListTotCnt(String mId) {
			int bCnt = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT COUNT(*) bCNT FROM myBAGBOARD WHERE mID=?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mId);
				rs = pstmt.executeQuery();
				rs.next();
				bCnt = rs.getInt(1);
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
			return bCnt;
		}

	// (3) 글쓰기(원글)
	public int writeMyBagBoard(String mId, String bName, String bContent, String bFilename, String bIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO myBAGBOARD " + " (bID, mID, bNAME, bCONTENT, bFILENAME, bIP) " 
					+ "		VALUES (myBAG_SEQ.NEXTVAL, ?, ?, ? , ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, bName);
			pstmt.setString(3, bContent);
			pstmt.setString(4, bFilename);
			pstmt.setString(5, bIp);
			result = pstmt.executeUpdate();
			System.out.println("안녕");
			System.out.println(result == SUCCESS ? "myBAG게시판 글쓰기성공" : "myBAG게시판 글쓰기실패");
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

	// (4) bHit 하나 올리기(1번글 조회수 하나 올리기)
	private void bHitUp(int bId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE myBAGBOARD SET bHIT = bHIT +1 WHERE bID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
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

	// (5) bId로 글 dto보기 : 글 상세보기(조회수 up + bid로 dto리턴)
	public MyBagBoardDto bContentView(int bId) {
		bHitUp(bId);
		MyBagBoardDto bDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM myBAGBOARD WHERE bID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mId = rs.getString("mId");
				String bName = rs.getString("bName");
				String bContent = rs.getString("bContent");
				String bFilename = rs.getString("bFilename");
				int bHit = rs.getInt("bHit");
				Timestamp bRdate = rs.getTimestamp("bRdate");
				String bIp = rs.getString("bIp");
				bDto = new MyBagBoardDto(bId, mId, bName, bContent, bFilename,  bHit, bRdate, bIp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage()+"error1");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage()+"error2");
			}
		}
		return bDto;
	}

	// (5) bId로 글 dto보기 : 답변글 view + 수정 view (bid로 dto리턴)
	public MyBagBoardDto modifyView_replyView(int bId) {
		MyBagBoardDto bDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * "
				+ " FROM myBAGBOARD " + " WHERE bID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mId = rs.getString("mId");
				String bName = rs.getString("bName");
				String bContent = rs.getString("bContent");
				String bFilename = rs.getString("bFilename");
				int bHit = rs.getInt("bHit");
				Timestamp bRdate = rs.getTimestamp("bRdate");
				String bIp = rs.getString("bIp");
				bDto = new MyBagBoardDto(bId, mId, bName, bContent, bFilename, bHit, bRdate, bIp);
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
		return bDto;
	}
	// (6) 글 수정하기(bId, bName, bContent, bFILENAME, bIp, bRDATE)
	public int modifyMyBagBoard(int bId, String bName, String bContent, String bFilename, String bIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE myBAGBOARD SET bNAME = ?, " 
				+ "                    bCONTENT = ?, "
				+ "                    bFILENAME = ?, " 
				+ "                    bRDATE = SYSDATE, "
				+ "                    bIP = ? " 
				+ "            WHERE bID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bName);
			pstmt.setString(2, bContent);
			pstmt.setString(3, bFilename);
			pstmt.setString(4, bIp);
			pstmt.setInt(5, bId);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "myBAG글수정성공" : "myBAG글수정실패");
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

	// (7) 글 삭제하기(bId로 삭제하기)
	public int deleteMyBagBoard(int bId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM myBAGBOARD WHERE bID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "myBAG게시판 글삭제성공" : "myBAG게시판 글삭제실패");
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

	// (8) 회원탈퇴 하기 전 회원이 쓴 글 모두 삭제 후 탈퇴
	public void AllDeleteMyBagBoard(String mId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM myBAGBOARD WHERE MID = ?";
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
	// (9) 관리자 권한으로 회원글(글번호로) 삭제
	public void DeleteMyBagBoard(String bId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM myBAGBOARD WHERE bID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bId);
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
