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


public class ItemBoardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static ItemBoardDao instance = new ItemBoardDao();

	public static ItemBoardDao getInstance() {
		return instance;
	}

	private ItemBoardDao() {
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

	// (1) 글목록(startRow부터 endRow까지) - 글번호, 작성자, ...
	public ArrayList<ItemBoardDto> iListBoard(int startRow, int endRow) {
		ArrayList<ItemBoardDto> iDtos = new ArrayList<ItemBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + " (SELECT ROWNUM RN, A.* FROM " + " (SELECT I.* FROM ITEMBOARD I "
				+ " ORDER BY iGROUP DESC, iSTEP) A)" + " WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int iId = rs.getInt("iId");
				String mId = rs.getString("mId");
				String iTitle = rs.getString("iTitle");
				String iContent = rs.getString("iContent");
				String iFilename = rs.getString("iFilename");
				int iHit = rs.getInt("iHit");
				Timestamp iRdate = rs.getTimestamp("iRDate");
				int iGroup = rs.getInt("iGroup");
				int iStep = rs.getInt("iStep");
				int iIndent = rs.getInt("iIndent");
				String iIp = rs.getString("iIp");
				iDtos.add(new ItemBoardDto(iId, mId, iTitle, iContent, iFilename, iHit, iRdate, iGroup, iStep, iIndent, iIp));
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
		return iDtos;
	}

	// (2) 글갯수
	public int getItemBoardTotCnt() {
		int iCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) iCNT FROM ITEMBOARD";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			iCnt = rs.getInt(1);
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
		return iCnt;
	}

	// (3) 글쓰기(원글)
	public int writeItemBoard(String mId, String iTitle, String iContent, String iFilename, String iIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ITEMBOARD " + " (iID, mID, iTITLE, iCONTENT, iFILENAME, iGROUP, iSTEP, iINDENT, iIP) " 
					+ "		VALUES (ITEM_SEQ.NEXTVAL, ?, ?, ? , ?, "
					+ "		ITEM_SEQ.CURRVAL, 0, 0, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, iTitle);
			pstmt.setString(3, iContent);
			pstmt.setString(4, iFilename);
			pstmt.setString(5, iIp);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "ITEM게시판 글쓰기성공" : "ITEM게시판 글쓰기실패");
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

	// (4) iHit 하나 올리기(1번글 조회수 하나 올리기)
	private void iHitUp(int iId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ITEMBOARD SET iHIT = iHIT +1 WHERE iID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iId);
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

	// (5) iId로 글 dto보기 : 글 상세보기(조회수 up + iId로 dto리턴)
	public ItemBoardDto iContentView(int iId) {
		iHitUp(iId);
		ItemBoardDto iDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * "
				+ " FROM ITEMBOARD " + " WHERE iID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mId = rs.getString("mId");
				String iTitle = rs.getString("iTitle");
				String iContent = rs.getString("iContent");
				String iFilename = rs.getString("iFilename");
				int iHit = rs.getInt("iHit");
				Timestamp iRdate = rs.getTimestamp("iRdate");
				int iGroup = rs.getInt("iGroup");
				int iStep = rs.getInt("iStep");
				int iIndent = rs.getInt("iIndent");
				String iIp = rs.getString("iIp");
				iDto = new ItemBoardDto(iId, mId, iTitle, iContent, iFilename, iHit, iRdate, iGroup, iStep, iIndent, iIp);
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
		return iDto;
	}

	// (5) iId로 글 dto보기 : 답변글 view + 수정 view (iId로 dto리턴)
	public ItemBoardDto modifyView_replyView(int iId) {
		ItemBoardDto iDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ITEMBOARD " + " WHERE iID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mId = rs.getString("mId");
				String iTitle = rs.getString("iTitle");
				String iContent = rs.getString("iContent");
				String iFilename = rs.getString("iFilename");
				int iHit = rs.getInt("iHit");
				Timestamp iRdate = rs.getTimestamp("iRdate");
				int iGroup = rs.getInt("iGroup");
				int iStep = rs.getInt("iStep");
				int iIndent = rs.getInt("iIndent");
				String iIp = rs.getString("iIp");
				iDto = new ItemBoardDto(iId, mId, iTitle, iContent, iFilename, iHit, iRdate, iGroup, iStep, iIndent, iIp);
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
		return iDto;
	}

	// (6) 글 수정하기(iId, iTitle, iContent, iFILENAME, iIp, iRDATE)
	public int modifyItemBoard(int iId, String iTitle, String iContent, String iFilename, String iIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ITEMBOARD SET iTITLE = ?, " 
				+ "                    iCONTENT = ?, "
				+ "                    iFILENAME = ?, " 
				+ "                    iRDATE = SYSDATE, "
				+ "                    iIP = ? " 
				+ "            WHERE iID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iTitle);
			pstmt.setString(2, iContent);
			pstmt.setString(3, iFilename);
			pstmt.setString(4, iIp);
			pstmt.setInt(5, iId);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "item글수정성공" : "item글수정실패");
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

	// (7) 글 삭제하기(iId로 삭제하기)
	public int deleteItemBoard(int iId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM ITEMBOARD WHERE iID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iId);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "ITEM게시판 글삭제성공" : "ITEM게시판 글삭제실패");
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

	// (8) 답변글 추가전 STEP a 수행
	private void ipreReplyStepA(int iGroup, int iStep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ITEMBOARD SET iSTEP = iSTEP+1  " 
					+ "    WHERE iGROUP = ? AND iSTEP > ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iGroup);
			pstmt.setInt(2, iStep);
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

	// (9) 답변글 쓰기
	public int replyItemBoard(String mId, String iTitle, String iContent, String iFilename, String iIp, int iGroup,
			int iStep, int iIndent) {
		ipreReplyStepA(iGroup, iStep); // 답변글 저장전 step A 먼저 실행
		// igroup, istep, iindent 원글정보
		// mid, ititle, icontent, iip 답변글 정보
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ITEMBOARD " + "(iID, mID, iTITLE, iCONTENT, iFILENAME, iGROUP, iSTEP, iINDENT, iIP) "
				+ " VALUES (ITEM_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, iTitle);
			pstmt.setString(3, iContent);
			pstmt.setString(4, iFilename);
			pstmt.setInt(5, iGroup);
			pstmt.setInt(6, iStep + 1);
			pstmt.setInt(7, iIndent + 1);
			pstmt.setString(8, iIp);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "ITEM게시판 답변쓰기성공" : "ITEM게시판 답변쓰기실패");
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
	public void AllDeleteItemBoard(String mId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM ITEMBOARD WHERE MID = ?";
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
