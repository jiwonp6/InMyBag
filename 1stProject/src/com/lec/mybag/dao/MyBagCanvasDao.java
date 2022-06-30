package com.lec.mybag.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MyBagCanvasDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static MyBagCanvasDao instance = new MyBagCanvasDao();

	public static MyBagCanvasDao getInstance() {
		return instance;
	}

	private MyBagCanvasDao() {
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
	//(1)사진에 태그넣기
	private int CanvasInsert(int bId, int cX, int cY, String cContent) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO myBAGCANVAS " + " (cID, bID, cX, cY, cCONTENT)" 
				+ "		VALUES (LIKE_SEQ.NEXTVAL, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			pstmt.setInt(2, cX);
			pstmt.setInt(3, cY);
			pstmt.setString(4, cContent);
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
		return result;
	}
	
	//(2) 사진에 태그 삭제
	private int CanvasDelete(int bId, int cId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM myBAGCANVAS WHERE bID=? AND cID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bId);
			pstmt.setInt(2, cId);
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
		return result;
	}
	//(3) 태그 수정
	public int modifymyBagCanvas(int cId, int cX, int cY, String cContent) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE myBAGCANVAS SET cX = ?, " 
					+ "                    cY = ?, "
					+ "                    cContent = ? "
					+ "            WHERE cID = ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cX);
			pstmt.setInt(2, cY);
			pstmt.setString(3, cContent);
			pstmt.setInt(4, cId);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "canvas수정성공" : "canvas글수정실패");
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
