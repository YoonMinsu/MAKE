package com.board.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

public class DBConnManager {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup( "java:comp/env/jdbc/OracleDB" );
			conn = ds.getConnection();
		} catch( Exception e ) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close( Connection conn, PreparedStatement pstmt, ResultSet rs ) {
		try {
			if( rs != null ) rs.close();
			if( pstmt != null ) pstmt.close();
			if( conn != null ) conn.close();
		} catch( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	public static void close( Connection conn, PreparedStatement pstmt ) {
		try {
			if( pstmt != null ) pstmt.close();
			if( conn != null ) conn.close();
		} catch( SQLException e ) {
			e.printStackTrace();
		}
	}
}
