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

import com.lec.mybag.dto.QnaBoardDto;

public class QnaBoardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static QnaBoardDao instance = new QnaBoardDao();

	public static QnaBoardDao getInstance() {
		return instance;
	}

	private QnaBoardDao() {
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
	public ArrayList<QnaBoardDto> qListBoard(int startRow, int endRow) {
		ArrayList<QnaBoardDto> qDtos = new ArrayList<QnaBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + " (SELECT ROWNUM RN, A.* FROM " + " (SELECT * FROM QNABOARD "
				+ " ORDER BY qGROUP DESC, qSTEP) A)" + " WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int qId = rs.getInt("qId");
				String aId = rs.getString("aId");
				String mId = rs.getString("mId");
				String qTitle = rs.getString("qTitle");
				String qContent = rs.getString("qContent");
				String qFilename = rs.getString("qFilename");
				int qHit = rs.getInt("qHit");
				Timestamp qRdate = rs.getTimestamp("qRDate");
				int qGroup = rs.getInt("qGroup");
				int qStep = rs.getInt("qStep");
				int qIndent = rs.getInt("qIndent");
				String qIp = rs.getString("qIp");
				qDtos.add(new QnaBoardDto(qId, aId, mId, qTitle, qContent, qFilename, qHit, qRdate, qGroup, qStep,
						qIndent, qIp));
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
		return qDtos;
	}

	// (2) 글갯수
	public int getQnaBoardTotCnt() {
		int qCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) qCNT FROM QNABOARD";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			qCnt = rs.getInt(1);
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
		return qCnt;
	}
	// 내글목록(startRow부터 endRow까지) - 글번호, 작성자이름, ...
		public ArrayList<QnaBoardDto> qMyListBoard(String mId, int startRow, int endRow) {
			ArrayList<QnaBoardDto> qDtos = new ArrayList<QnaBoardDto>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM " + " (SELECT ROWNUM RN, A.* FROM " + " (SELECT * FROM QNABOARD "
					+ " WHERE mID=? ORDER BY qGROUP DESC, qSTEP) A)" + " WHERE RN BETWEEN ? AND ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mId);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int qId = rs.getInt("qId");
					String aId = rs.getString("aId");
					String qTitle = rs.getString("qTitle");
					String qContent = rs.getString("qContent");
					String qFilename = rs.getString("qFilename");
					int qHit = rs.getInt("qHit");
					Timestamp qRdate = rs.getTimestamp("qRDate");
					int qGroup = rs.getInt("qGroup");
					int qStep = rs.getInt("qStep");
					int qIndent = rs.getInt("qIndent");
					String qIp = rs.getString("qIp");
					qDtos.add(new QnaBoardDto(qId, aId, mId, qTitle, qContent, qFilename, qHit, qRdate, qGroup, qStep,
							qIndent, qIp));
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
			return qDtos;
		}

		// 내글갯수
		public int getMyqListTotCnt(String mId) {
			int qCnt = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT COUNT(*) qCNT FROM QNABOARD WHERE mID=?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mId);
				rs = pstmt.executeQuery();
				rs.next();
				qCnt = rs.getInt(1);
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
			return qCnt;
		}
	// (3) 글쓰기(원글)
	public int writeQnaBoard(String mId, String qTitle, String qContent, String qFilename, String qIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO QNABOARD " + " (qID, mID, qTITLE, qCONTENT, qFILENAME, qGROUP, qSTEP, qINDENT, qIP) " 
					+ "		VALUES (QNA_SEQ.NEXTVAL, ?, ?, ? , ?, "
					+ "		QNA_SEQ.CURRVAL, 0, 0, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, qTitle);
			pstmt.setString(3, qContent);
			pstmt.setString(4, qFilename);
			pstmt.setString(5, qIp);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "QNA게시판 글쓰기성공" : "QNA게시판 글쓰기실패");
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

	// (4) qHit 하나 올리기(1번글 조회수 하나 올리기)
	private void qHitUp(int qId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNABOARD SET qHIT = qHIT +1 WHERE qID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qId);
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

	// (5) qId로 글 dto보기 : 글 상세보기(조회수 up + qid로 dto리턴)
	public QnaBoardDto qContentView(int qId) {
		qHitUp(qId);
		QnaBoardDto qDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * "
				+ " FROM QNABOARD " + " WHERE qID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aId = rs.getString("aId");
				String mId = rs.getString("mId");
				String qTitle = rs.getString("qTitle");
				String qContent = rs.getString("qContent");
				String qFilename = rs.getString("qFilename");
				int qHit = rs.getInt("qHit");
				Timestamp qRdate = rs.getTimestamp("qRdate");
				int qGroup = rs.getInt("qGroup");
				int qStep = rs.getInt("qStep");
				int qIndent = rs.getInt("qIndent");
				String qIp = rs.getString("qIp");
				qDto = new QnaBoardDto(qId, aId, mId, qTitle, qContent, qFilename, qHit, qRdate, qGroup, qStep, qIndent,
						qIp);
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
		return qDto;
	}

	// (5) qId로 글 dto보기 : 답변글 view + 수정 view (qid로 dto리턴)
	public QnaBoardDto modifyView_replyView(int qId) {
		QnaBoardDto qDto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT *  "
				+ " FROM QNABOARD " + " WHERE qID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aId = rs.getString("aId");
				String mId = rs.getString("mId");
				String qTitle = rs.getString("qTitle");
				String qContent = rs.getString("qContent");
				String qFilename = rs.getString("qFilename");
				int qHit = rs.getInt("qHit");
				Timestamp qRdate = rs.getTimestamp("qRdate");
				int qGroup = rs.getInt("qGroup");
				int qStep = rs.getInt("qStep");
				int qIndent = rs.getInt("qIndent");
				String qIp = rs.getString("qIp");
				qDto = new QnaBoardDto(qId, aId, mId, qTitle, qContent, qFilename, qHit, qRdate, qGroup, qStep, qIndent,
						qIp);
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
		return qDto;
	}

	// (6) 글 수정하기(qId, qTitle, qContent, qFILENAME, qIp, qRDATE)
	public int modifyQnaBoard(int qId, String qTitle, String qContent, String qFilename, String qIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNABOARD SET qTITLE = ?, " 
				+ "                    qCONTENT = ?, "
				+ "                    qFILENAME = ?, " 
				+ "                    qRDATE = SYSDATE, "
				+ "                    qIP = ? " 
				+ "            WHERE qID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qTitle);
			pstmt.setString(2, qContent);
			pstmt.setString(3, qFilename);
			pstmt.setString(4, qIp);
			pstmt.setInt(5, qId);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "qna글수정성공" : "qna글수정실패");
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
	public int deleteQnaBoard(int qId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM QNABOARD WHERE qID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qId);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "Qna게시판 글삭제성공" : "Qna게시판 글삭제실패");
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
	private void qpreReplyStepA(int qGroup, int qStep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNABOARD SET qSTEP = qSTEP+1  " + "    WHERE qGROUP = ? AND qSTEP > ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qGroup);
			pstmt.setInt(2, qStep);
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
	public int replyQnaBoard(String aId, String qTitle, String qContent, String qFilename, String qIp, int qGroup,
			int qStep, int qIndent) {
		qpreReplyStepA(qGroup, qStep); // 답변글 저장전 step A 먼저 실행
		// qgroup, qstep, qindent 원글정보
		// aid, qtitle, qcontent, qip 답변글 정보
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO QNABOARD " + "(qID, aID, qTITLE, qCONTENT, qFILENAME, qGROUP, qSTEP, qINDENT, qIP) "
				+ " VALUES (QNA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, qTitle);
			pstmt.setString(3, qContent);
			pstmt.setString(4, qFilename);
			pstmt.setInt(5, qGroup);
			pstmt.setInt(6, qStep + 1);
			pstmt.setInt(7, qIndent + 1);
			pstmt.setString(8, qIp);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "Qna게시판 답변쓰기성공" : "Qna게시판 답변쓰기실패");
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
	public void AllDeleteQnaBoard(String mId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM QNABOARD WHERE MID = ?";
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
	//관리자 탈퇴전
	public void AllDeleteQnaBoard2(String aId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM QNABOARD WHERE aID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
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
